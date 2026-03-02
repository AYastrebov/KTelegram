package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.getOrThrow
import com.github.ayastrebov.telegram.model.isError
import com.github.ayastrebov.telegram.request.*
import io.ktor.http.*
import kotlin.test.*

class BotTest {

    @Test
    fun getMeUsesPostMethod() = runTestBot(
        response = """{"ok": true, "result": $USER_JSON}""",
        assertRequest = { assertEquals(HttpMethod.Post, it.method) },
    ) { bot ->
        val user = bot.getMe().getOrThrow()
        assertEquals("Test", user.firstName)
        assertEquals(123456L, user.id)
    }

    @Test
    fun getMeUrlPath() = runTestBot(
        response = """{"ok": true, "result": $USER_JSON}""",
        assertRequest = { assertTrue(it.url.encodedPath.endsWith("bottest-token/getMe")) },
    ) { bot ->
        bot.getMe()
    }

    @Test
    fun sendMessageUsesPost() = runTestBot(
        response = """{"ok": true, "result": $MESSAGE_JSON}""",
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("sendMessage"))
        },
    ) { bot ->
        val msg = bot.sendMessage(SendMessageRequest(chatId = "123", text = "hi")).getOrThrow()
        assertEquals(42L, msg.messageId)
    }

    @Test
    fun sendPhotoUsesPost() = runTestBot(
        response = """{"ok": true, "result": $MESSAGE_JSON}""",
        assertRequest = { assertEquals(HttpMethod.Post, it.method) },
    ) { bot ->
        bot.sendPhoto(SendPhotoRequest(chatId = "123", photo = "file_id"))
    }

    @Test
    fun getUpdatesUsesPost() = runTestBot(
        response = """{"ok": true, "result": []}""",
        assertRequest = { assertEquals(HttpMethod.Post, it.method) },
    ) { bot ->
        val updates = bot.getUpdates().getOrThrow()
        assertTrue(updates.isEmpty())
    }

    @Test
    fun errorResponseHandled() = runTestBot(
        response = OK_FALSE_RESULT_JSON,
    ) { bot ->
        val response = bot.sendMessage(SendMessageRequest(chatId = "999", text = "fail"))
        assertTrue(response.isError)
        assertEquals(400, response.errorCode)
    }

    @Test
    fun deleteMessageUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("deleteMessage"))
        },
    ) { bot ->
        val result = bot.deleteMessage(DeleteMessageRequest(chatId = "123", messageId = 1)).getOrThrow()
        assertTrue(result)
    }

    @Test
    fun editMessageTextUsesPost() = runTestBot(
        response = """{"ok": true, "result": $MESSAGE_JSON}""",
        assertRequest = { assertEquals(HttpMethod.Post, it.method) },
    ) { bot ->
        bot.editMessageText(EditMessageTextRequest(chatId = "123", messageId = 42, text = "edited"))
    }

    @Test
    fun answerCallbackQueryUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("answerCallbackQuery"))
        },
    ) { bot ->
        bot.answerCallbackQuery(AnswerCallbackQueryRequest(callbackQueryId = "abc"))
    }

    @Test
    fun getFileUsesPost() = runTestBot(
        response = """{"ok": true, "result": {"file_id": "abc", "file_unique_id": "xyz"}}""",
        assertRequest = { assertEquals(HttpMethod.Post, it.method) },
    ) { bot ->
        val file = bot.getFile("abc").getOrThrow()
        assertEquals("abc", file.fileId)
    }

    @Test
    fun setWebhookUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = { assertEquals(HttpMethod.Post, it.method) },
    ) { bot ->
        bot.setWebhook(SetWebhookRequest(url = "https://example.com/webhook")).getOrThrow()
    }

    @Test
    fun getWebhookInfoUsesPost() = runTestBot(
        response = """{"ok": true, "result": {"url": "", "has_custom_certificate": false, "pending_update_count": 0}}""",
        assertRequest = { assertEquals(HttpMethod.Post, it.method) },
    ) { bot ->
        val info = bot.getWebhookInfo().getOrThrow()
        assertEquals("", info.url)
    }

    @Test
    fun forwardMessageUsesPost() = runTestBot(
        response = """{"ok": true, "result": $MESSAGE_JSON}""",
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("forwardMessage"))
        },
    ) { bot ->
        bot.forwardMessage(ForwardMessageRequest(chatId = "1", fromChatId = "2", messageId = 42))
    }

    @Test
    fun setMyCommandsUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = { assertEquals(HttpMethod.Post, it.method) },
    ) { bot ->
        bot.setMyCommands(SetMyCommandsRequest(commands = emptyList())).getOrThrow()
    }

    @Test
    fun closeReleasesResources() {
        val bot = createTestBot(OK_TRUE_JSON)
        bot.close() // should not throw
    }

    // --- helper ---

    private fun runTestBot(
        response: String,
        assertRequest: (io.ktor.client.request.HttpRequestData) -> Unit = {},
        block: suspend (Bot) -> Unit,
    ) = kotlinx.coroutines.test.runTest {
        val bot = createTestBot(response, assertRequest = assertRequest)
        try {
            block(bot)
        } finally {
            bot.close()
        }
    }
}
