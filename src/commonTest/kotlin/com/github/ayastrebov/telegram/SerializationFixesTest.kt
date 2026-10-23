package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.*
import com.github.ayastrebov.telegram.request.*
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.test.*

/**
 * Regression tests for wire-format bugs: discriminator collisions, omitted defaults and
 * leaked Kotlin class names in polymorphic fields.
 */
class SerializationFixesTest {

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    private fun bodyOf(request: HttpRequestData): String =
        (request.body as TextContent).text

    @Test
    fun sendMediaGroupSerializesInputMediaWithTypeDiscriminator() = runTest {
        var body = ""
        val bot = createTestBot(
            responseBody = """{"ok": true, "result": [$MESSAGE_JSON]}""",
            assertRequest = { body = bodyOf(it) },
        )
        bot.sendMediaGroup(
            SendMediaGroupRequest(
                chatId = "1",
                media = listOf(
                    InputMediaPhoto(media = "photo_id", caption = "c"),
                    InputMediaVideo(media = "video_id", supportsStreaming = true),
                    InputMediaAnimation(media = "anim_id"),
                ),
            ),
        ).getOrThrow()
        bot.close()

        val media = json.parseToJsonElement(body).jsonObject["media"]!!
        val types = media.toString()
        assertTrue(types.contains("\"type\":\"photo\""), body)
        assertTrue(types.contains("\"type\":\"video\""), body)
        assertTrue(types.contains("\"type\":\"animation\""), body)
        assertFalse(body.contains("com.github"), body)
    }

    @Test
    fun replyKeyboardRemoveAlwaysEncodesRemoveKeyboard() {
        val encoded = json.encodeToString(
            SendMessageRequest.serializer(),
            SendMessageRequest(chatId = "1", text = "hi", replyMarkup = ReplyKeyboardRemove()),
        )
        val markup = json.parseToJsonElement(encoded).jsonObject["reply_markup"]!!.jsonObject
        assertEquals("true", markup["remove_keyboard"]?.jsonPrimitive?.content)
        assertNull(markup["type"])
    }

    @Test
    fun keyboardMarkupHasNoClassDiscriminator() {
        val inline = InlineKeyboardMarkup(listOf(listOf(InlineKeyboardButton("b", callbackData = "x"))))
        val encoded = json.encodeToString(KeyboardMarkupSerializer, inline)
        assertEquals("""{"inline_keyboard":[[{"text":"b","callback_data":"x"}]]}""", encoded)

        val reply = ReplyKeyboardMarkup(listOf(listOf(KeyboardButton("a"))), isPersistent = true)
        assertFalse(json.encodeToString(KeyboardMarkupSerializer, reply).contains("\"type\""))
        assertFalse(json.encodeToString(KeyboardMarkupSerializer, ForceReply(forceReply = true)).contains("\"type\""))
    }

    @Test
    fun keyboardMarkupRoundTripsEveryVariant() {
        val variants: List<KeyboardMarkup> = listOf(
            InlineKeyboardMarkup(listOf(listOf(InlineKeyboardButton("b", url = "https://t.me")))),
            ReplyKeyboardMarkup(listOf(listOf(KeyboardButton("a")))),
            ReplyKeyboardRemove(),
            ForceReply(forceReply = true, selective = true),
        )
        for (v in variants) {
            val encoded = json.encodeToString(KeyboardMarkupSerializer, v)
            val decoded = json.decodeFromString(KeyboardMarkupSerializer, encoded)
            assertEquals(v, decoded)
        }
    }

    @Test
    fun inputMessageContentHasNoClassDiscriminator() {
        val request = AnswerInlineQueryRequest(
            inlineQueryId = "q",
            results = listOf(
                InlineQueryResult.InlineQueryResultArticle(
                    id = "1",
                    inputMessageContent = InputMessageContent.InputTextMessageContent("hello"),
                    title = "t",
                ),
            ),
        )
        val encoded = json.encodeToString(AnswerInlineQueryRequest.serializer(), request)
        assertTrue(encoded.contains("\"type\":\"article\""), encoded)
        assertTrue(encoded.contains("\"input_message_content\":{\"message_text\":\"hello\"}"), encoded)
        assertFalse(encoded.contains("com.github"), encoded)
    }

    @Test
    fun messageOriginDeserializesByType() {
        val input = """
        {
            "message_id": 1,
            "date": 1700000000,
            "chat": {"id": 1, "type": "private"},
            "forward_origin": {"type": "channel", "date": 1699999999, "chat": {"id": -100, "type": "channel", "title": "C"}, "message_id": 55, "author_signature": "sig"}
        }
        """.trimIndent()
        val msg = json.decodeFromString<Message>(input)
        val origin = assertIs<MessageOriginChannel>(msg.forwardOrigin)
        assertEquals(55L, origin.messageId)
        assertEquals("sig", origin.authorSignature)
    }

    @Test
    fun chatBoostSourceUsesSourceDiscriminator() {
        val input = """{"boost_id": "b", "add_date": 1, "expiration_date": 2, "source": {"source": "premium", "user": $USER_JSON}}"""
        val boost = json.decodeFromString<ChatBoost>(input)
        assertIs<ChatBoostSourcePremium>(boost.source)
    }

    @Test
    fun botCommandScopeEncodesTypeField() {
        val encoded = json.encodeToString(
            SetMyCommandsRequest.serializer(),
            SetMyCommandsRequest(commands = listOf(BotCommand("start", "Start")), scope = BotCommandScopeAllPrivateChats()),
        )
        assertTrue(encoded.contains("\"scope\":{\"type\":\"all_private_chats\"}"), encoded)
    }

    @Test
    fun reactionUpdateDeserializes() {
        val input = """
        {
            "update_id": 1,
            "message_reaction": {
                "chat": {"id": 1, "type": "private"},
                "message_id": 9,
                "date": 1700000000,
                "old_reaction": [],
                "new_reaction": [{"type": "emoji", "emoji": "👍"}, {"type": "paid"}]
            }
        }
        """.trimIndent()
        val update = json.decodeFromString<Update>(input)
        val reactions = update.messageReaction!!.newReaction
        assertEquals("👍", assertIs<ReactionTypeEmoji>(reactions[0]).emoji)
        assertIs<ReactionTypePaid>(reactions[1])
    }

    @Test
    fun pollCloseDateIsInstant() {
        val input = """
        {
            "id": "p", "question": "q", "options": [], "total_voter_count": 0, "is_closed": false,
            "is_anonymous": true, "type": "regular", "allows_multiple_answers": false,
            "allows_revoting": false, "close_date": 1700000000
        }
        """.trimIndent()
        val poll = json.decodeFromString<Poll>(input)
        assertEquals(1700000000L, poll.closeDate?.epochSeconds)
    }

    @Test
    fun newMethodsPostToTheirEndpoints() = runTest {
        val cases: List<Pair<String, suspend (Bot) -> Unit>> = listOf(
            "setChatPermissions" to { it.setChatPermissions(SetChatPermissionsRequest("1", ChatPermissions(canSendMessages = true))) },
            "setMessageReaction" to { it.setMessageReaction(SetMessageReactionRequest("1", 2, listOf(ReactionTypeEmoji("👍")))) },
            "editForumTopic" to { it.editForumTopic(EditForumTopicRequest("1", 3, name = "n")) },
            "setMyName" to { it.setMyName(SetMyNameRequest(name = "Bot")) },
            "answerPreCheckoutQuery" to { it.answerPreCheckoutQuery(AnswerPreCheckoutQueryRequest("q", ok = true)) },
            "unpinAllChatMessages" to { it.unpinAllChatMessages(UnpinAllChatMessagesRequest("1")) },
        )
        for ((endpoint, call) in cases) {
            var path = ""
            var method: HttpMethod? = null
            val bot = createTestBot(OK_TRUE_JSON, assertRequest = { path = it.url.encodedPath; method = it.method })
            call(bot)
            bot.close()
            assertEquals(HttpMethod.Post, method, endpoint)
            assertTrue(path.endsWith("/$endpoint"), "$endpoint -> $path")
        }
    }

    @Test
    fun getUpdatesRaisesRequestTimeoutForLongPolling() = runTest {
        var timeout: Long? = null
        val bot = createTestBot(
            responseBody = """{"ok": true, "result": []}""",
            assertRequest = { timeout = it.getCapabilityOrNull(io.ktor.client.plugins.HttpTimeoutCapability)?.requestTimeoutMillis },
        )
        bot.getUpdates(GetUpdatesRequest(timeout = 30))
        bot.close()
        assertEquals(40_000L, timeout)
    }
}
