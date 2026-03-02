package com.github.ayastrebov.telegram.model

import kotlinx.serialization.json.Json
import kotlin.test.*

class SerializationTest {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Test
    fun deserializeUpdate() {
        val input = """
        {
            "update_id": 100,
            "message": {
                "message_id": 1,
                "chat": {"id": 42, "type": "private"},
                "date": 1700000000,
                "text": "hello"
            }
        }
        """.trimIndent()

        val update = json.decodeFromString<Update>(input)
        assertEquals(100L, update.updateId)
        assertEquals("hello", update.message?.text)
        assertEquals(42L, update.message?.chat?.id)
    }

    @Test
    fun deserializeCallbackQuery() {
        val input = """
        {
            "update_id": 200,
            "callback_query": {
                "id": "cb1",
                "from": {"id": 1, "is_bot": false, "first_name": "Test"},
                "data": "button_clicked",
                "chat_instance": "abc"
            }
        }
        """.trimIndent()

        val update = json.decodeFromString<Update>(input)
        val callbackQuery = assertNotNull(update.callbackQuery)
        assertEquals("button_clicked", callbackQuery.data)
    }

    @Test
    fun deserializeMessageWithPhoto() {
        val input = """
        {
            "message_id": 5,
            "chat": {"id": 1, "type": "private"},
            "date": 1700000000,
            "photo": [
                {"file_id": "photo1", "file_unique_id": "u1", "width": 100, "height": 100},
                {"file_id": "photo2", "file_unique_id": "u2", "width": 800, "height": 600}
            ]
        }
        """.trimIndent()

        val message = json.decodeFromString<Message>(input)
        val photos = assertNotNull(message.photo)
        assertEquals(2, photos.size)
        assertEquals("photo2", photos[1].fileId)
    }

    @Test
    fun unknownFieldsAreIgnored() {
        val input = """
        {
            "update_id": 300,
            "some_future_field": "value",
            "another_new_thing": 42
        }
        """.trimIndent()

        val update = json.decodeFromString<Update>(input)
        assertEquals(300L, update.updateId)
        assertNull(update.message)
    }

    @Test
    fun deserializeUser() {
        val input = """
        {
            "id": 999,
            "is_bot": true,
            "first_name": "MyBot",
            "username": "my_bot",
            "is_premium": true
        }
        """.trimIndent()

        val user = json.decodeFromString<User>(input)
        assertEquals(999L, user.id)
        assertTrue(user.isBot)
        assertTrue(user.isPremium == true)
    }

    @Test
    fun deserializeMessageWithNewFields() {
        val input = """
        {
            "message_id": 10,
            "chat": {"id": 1, "type": "private"},
            "date": 1700000000,
            "location": {"longitude": 37.6176, "latitude": 55.7558},
            "dice": {"emoji": "🎲", "value": 3}
        }
        """.trimIndent()

        val message = json.decodeFromString<Message>(input)
        val location = assertNotNull(message.location)
        assertEquals(37.6176, location.longitude, 0.0001)
        val dice = assertNotNull(message.dice)
        assertEquals(3, dice.value)
    }

    @Test
    fun deserializeChatWithNewFields() {
        val input = """
        {
            "id": -100123,
            "type": "supergroup",
            "title": "Test",
            "is_forum": true,
            "slow_mode_delay": 30,
            "linked_chat_id": -100456
        }
        """.trimIndent()

        val chat = json.decodeFromString<Chat>(input)
        assertTrue(chat.isForum == true)
        assertEquals(30, chat.slowModeDelay)
        assertEquals(-100456L, chat.linkedChatId)
    }

    @Test
    fun deserializeUpdateWithMyChatMember() {
        val input = """
        {
            "update_id": 400,
            "my_chat_member": {
                "chat": {"id": 1, "type": "private"},
                "from": {"id": 2, "is_bot": false, "first_name": "Admin"},
                "date": 1700000000,
                "old_chat_member": {"user": {"id": 3, "is_bot": true, "first_name": "Bot"}, "status": "member"},
                "new_chat_member": {"user": {"id": 3, "is_bot": true, "first_name": "Bot"}, "status": "kicked"}
            }
        }
        """.trimIndent()

        val update = json.decodeFromString<Update>(input)
        val myChatMember = assertNotNull(update.myChatMember)
        assertEquals("kicked", myChatMember.newChatMember.status)
    }

    @Test
    fun deserializeResponseWithError() {
        val input = """{"ok": false, "error_code": 403, "description": "Forbidden"}"""
        val response = json.decodeFromString<Response<Boolean>>(input)
        assertFalse(response.ok)
        assertEquals(403, response.errorCode)
        assertEquals("Forbidden", response.description)
    }

    @Test
    fun deserializeSticker() {
        val input = """
        {
            "file_id": "stk1",
            "file_unique_id": "u_stk1",
            "width": 512,
            "height": 512,
            "is_animated": false,
            "is_video": true,
            "emoji": "😀",
            "thumbnail": {"file_id": "thumb1", "file_unique_id": "u_th1", "width": 128, "height": 128}
        }
        """.trimIndent()

        val sticker = json.decodeFromString<Sticker>(input)
        assertEquals("stk1", sticker.fileId)
        assertTrue(sticker.isVideo)
        val thumbnail = assertNotNull(sticker.thumbnail)
        assertEquals(128, thumbnail.width)
    }
}
