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
        val file = bot.getFile(GetFileRequest(fileId = "abc")).getOrThrow()
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
    fun forwardMessagesUsesPost() = runTestBot(
        response = """{"ok": true, "result": [{"message_id": 1001}, {"message_id": 1002}]}""",
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("forwardMessages"))
        },
    ) { bot ->
        val ids = bot.forwardMessages(
            ForwardMessagesRequest(chatId = "1", fromChatId = "2", messageIds = listOf(10, 11))
        ).getOrThrow()
        assertEquals(2, ids.size)
        assertEquals(1001L, ids.first().messageId)
    }

    @Test
    fun copyMessagesUsesPost() = runTestBot(
        response = """{"ok": true, "result": [{"message_id": 2001}, {"message_id": 2002}]}""",
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("copyMessages"))
        },
    ) { bot ->
        val ids = bot.copyMessages(
            CopyMessagesRequest(chatId = "1", fromChatId = "2", messageIds = listOf(20, 21))
        ).getOrThrow()
        assertEquals(2, ids.size)
        assertEquals(2001L, ids.first().messageId)
    }

    @Test
    fun deleteMessagesUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("deleteMessages"))
        },
    ) { bot ->
        val result = bot.deleteMessages(DeleteMessagesRequest(chatId = "1", messageIds = listOf(1, 2))).getOrThrow()
        assertTrue(result)
    }

    @Test
    fun getChatAdministratorsUsesPost() = runTestBot(
        response = """{"ok": true, "result": [{"user": {"id": 1, "is_bot": false, "first_name": "Admin"}, "status": "administrator"}]}""",
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("getChatAdministrators"))
        },
    ) { bot ->
        val admins = bot.getChatAdministrators(GetChatAdministratorsRequest(chatId = "1")).getOrThrow()
        assertEquals(1, admins.size)
        assertEquals("administrator", admins.first().status)
    }

    @Test
    fun setMyCommandsUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = { assertEquals(HttpMethod.Post, it.method) },
    ) { bot ->
        bot.setMyCommands(SetMyCommandsRequest(commands = emptyList())).getOrThrow()
    }

    @Test
    fun logOutUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("logOut"))
        },
    ) { bot ->
        assertTrue(bot.logOut().getOrThrow())
    }

    @Test
    fun closeBotUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("close"))
        },
    ) { bot ->
        assertTrue(bot.closeBot().getOrThrow())
    }

    @Test
    fun setChatMemberTagUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("setChatMemberTag"))
        },
    ) { bot ->
        val result = bot.setChatMemberTag(
            SetChatMemberTagRequest(chatId = "123", userId = 456, tag = "VIP")
        ).getOrThrow()
        assertTrue(result)
    }

    @Test
    fun sendMessageDraftUsesPost() = runTestBot(
        response = OK_TRUE_JSON,
        assertRequest = {
            assertEquals(HttpMethod.Post, it.method)
            assertTrue(it.url.encodedPath.endsWith("sendMessageDraft"))
        },
    ) { bot ->
        val result = bot.sendMessageDraft(
            SendMessageDraftRequest(chatId = "123", draftId = 1, text = "partial...")
        ).getOrThrow()
        assertTrue(result)
    }

    @Test
    fun sendMessageDraftErrorResponse() = runTestBot(
        response = OK_FALSE_RESULT_JSON,
    ) { bot ->
        val response = bot.sendMessageDraft(
            SendMessageDraftRequest(chatId = "999", draftId = 1, text = "fail")
        )
        assertTrue(response.isError)
        assertEquals(400, response.errorCode)
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
