package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.InputPollOption
import com.github.ayastrebov.telegram.model.getOrThrow
import com.github.ayastrebov.telegram.request.*
import io.ktor.http.*
import kotlin.test.*

class NewMethodsTest {

    @Test
    fun sendPollUsesPost() = runTestBot(
        response = """{"ok": true, "result": $MESSAGE_JSON}""",
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("sendPoll"))
        },
    ) { bot ->
        val msg = bot.sendPoll(
            SendPollRequest(
                chatId = "123",
                question = "Test?",
                options = listOf(InputPollOption(text = "Yes"), InputPollOption(text = "No"))
            )
        ).getOrThrow()
        assertEquals(42L, msg.messageId)
    }

    @Test
    fun promoteChatMemberUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("promoteChatMember"))
        },
    ) { bot ->
        val result = bot.promoteChatMember(
            PromoteChatMemberRequest(chatId = "123", userId = 456, canManageChat = true)
        ).getOrThrow()
        assertTrue(result)
    }

    @Test
    fun getManagedBotTokenUsesPost() = runTestBot(
        response = """{"ok": true, "result": "123456:ABC-DEF"}""",
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("getManagedBotToken"))
        },
    ) { bot ->
        val token = bot.getManagedBotToken(GetManagedBotTokenRequest(userId = 999)).getOrThrow()
        assertEquals("123456:ABC-DEF", token)
    }

    @Test
    fun replaceManagedBotTokenUsesPost() = runTestBot(
        response = """{"ok": true, "result": "123456:NEW-TOKEN"}""",
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("replaceManagedBotToken"))
        },
    ) { bot ->
        val token = bot.replaceManagedBotToken(ReplaceManagedBotTokenRequest(userId = 999)).getOrThrow()
        assertEquals("123456:NEW-TOKEN", token)
    }

    @Test
    fun setMyProfilePhotoUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("setMyProfilePhoto"))
        },
    ) { bot ->
        val result = bot.setMyProfilePhoto(SetMyProfilePhotoRequest(photo = "photo_data")).getOrThrow()
        assertTrue(result)
    }

    @Test
    fun removeMyProfilePhotoUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("removeMyProfilePhoto"))
        },
    ) { bot ->
        val result = bot.removeMyProfilePhoto().getOrThrow()
        assertTrue(result)
    }

    @Test
    fun savePreparedKeyboardButtonUsesPost() = runTestBot(
        response = """{"ok": true, "result": {"id": "btn_abc"}}""",
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("savePreparedKeyboardButton"))
        },
    ) { bot ->
        val btn = bot.savePreparedKeyboardButton(
            SavePreparedKeyboardButtonRequest(
                userId = 123,
                button = com.github.ayastrebov.telegram.model.KeyboardButton(text = "Request Bot")
            )
        ).getOrThrow()
        assertEquals("btn_abc", btn.id)
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
