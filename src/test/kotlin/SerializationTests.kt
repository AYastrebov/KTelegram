package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.time.Instant

class SerializationTests {

    private val json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    @OptIn(kotlin.time.ExperimentalTime::class)
    @Test
    fun testUpdateSerialization() {

        val jsonContent = """{
            "update_id": 369518029,
            "message": {
                "message_id": 602514,
                "from": {
                    "id": 5247805674,
                    "is_bot": false,
                    "first_name": "Андрей",
                    "username": "Khz228",
                    "language_code": "ru"
                },
                "chat": {
                    "id": -1001772241729,
                    "title": "C4_VTR - чат о Citroen C4",
                    "username": "C4_VTR",
                    "is_forum": true,
                    "type": "supergroup"
                },
                "date": 1758837119,
                "media_group_id": "14070696956143906",
                "photo": [
                    {
                        "file_id": "AgACAgIAAx0CaaJDQQABCTGSaNW5f1cYuZJsKiinXPHArdI_PtIAAvL9MRsRxrBKyyVn62wugcgBAAMCAANzAAM2BA",
                        "file_unique_id": "AQAD8v0xGxHGsEp4",
                        "file_size": 1754,
                        "width": 67,
                        "height": 90
                    },
                    {
                        "file_id": "AgACAgIAAx0CaaJDQQABCTGSaNW5f1cYuZJsKiinXPHArdI_PtIAAvL9MRsRxrBKyyVn62wugcgBAAMCAANtAAM2BA",
                        "file_unique_id": "AQAD8v0xGxHGsEpy",
                        "file_size": 22898,
                        "width": 240,
                        "height": 320
                    },
                    {
                        "file_id": "AgACAgIAAx0CaaJDQQABCTGSaNW5f1cYuZJsKiinXPHArdI_PtIAAvL9MRsRxrBKyyVn62wugcgBAAMCAAN4AAM2BA",
                        "file_unique_id": "AQAD8v0xGxHGsEp9",
                        "file_size": 91302,
                        "width": 600,
                        "height": 800
                    },
                    {
                        "file_id": "AgACAgIAAx0CaaJDQQABCTGSaNW5f1cYuZJsKiinXPHArdI_PtIAAvL9MRsRxrBKyyVn62wugcgBAAMCAAN5AAM2BA",
                        "file_unique_id": "AQAD8v0xGxHGsEp-",
                        "file_size": 166702,
                        "width": 960,
                        "height": 1280
                    }
                ],
                "caption": "Второй раз пришлось лезть 😂 мелкий треск при повороте налево был, хз или проводка задевала на моторчик или неправильно поставил) Проводку подмотал, моторчик еще раз переставил) и проверил фух треск пропал 🙈"
            }
        }""".trimIndent()

        // Deserialize JSON to Update object
        val update = json.decodeFromString<Update>(jsonContent)

        // Verify the deserialization
        assertEquals(369518029L, update.updateId)
        assertNotNull(update.message)

        val message = update.message
        assertEquals(602514, message.messageId)
        
        // Convert Unix timestamp to LocalDateTime using Europe/Moscow timezone (same as serializer)
        val expectedDate = Instant.fromEpochSeconds(1758837119).toLocalDateTime(TimeZone.of("Europe/Moscow"))
        assertEquals(expectedDate, message.date)
        assertEquals("14070696956143906", message.mediaGroupId)
        assertEquals("Второй раз пришлось лезть 😂 мелкий треск при повороте налево был, хз или проводка задевала на моторчик или неправильно поставил) Проводку подмотал, моторчик еще раз переставил) и проверил фух треск пропал 🙈", message.caption)

        // Verify user information
        assertNotNull(message.from)
        val user = message.from
        assertEquals(5247805674L, user.id)
        assertEquals(false, user.isBot)
        assertEquals("Андрей", user.firstName)
        assertEquals("Khz228", user.username)
        assertEquals("ru", user.languageCode)

        // Verify chat information
        assertNotNull(message.chat)
        val chat = message.chat
        assertEquals(-1001772241729L, chat.id)
        assertEquals("C4_VTR - чат о Citroen C4", chat.title)
        assertEquals("C4_VTR", chat.username)
        assertEquals("supergroup", chat.type)

        // Verify photo array
        assertNotNull(message.photo)
        val photos = message.photo
        assertEquals(4, photos.size)

        // Check first photo size
        val firstPhoto = photos[0]
        assertEquals("AgACAgIAAx0CaaJDQQABCTGSaNW5f1cYuZJsKiinXPHArdI_PtIAAvL9MRsRxrBKyyVn62wugcgBAAMCAANzAAM2BA", firstPhoto.fileId)
        assertEquals("AQAD8v0xGxHGsEp4", firstPhoto.fileUniqueId)
        assertEquals(1754, firstPhoto.fileSize)
        assertEquals(67, firstPhoto.width)
        assertEquals(90, firstPhoto.height)

        // Check largest photo size
        val largestPhoto = photos[3]
        assertEquals(960, largestPhoto.width)
        assertEquals(1280, largestPhoto.height)
        assertEquals(166702, largestPhoto.fileSize)

        println("✅ Update deserialization test passed successfully!")
        println("Update ID: ${update.updateId}")
        println("Message from: ${message.from.firstName} (@${message.from.username})")
        println("Chat: ${chat.title}")
        println("Photo sizes: ${photos.map { "${it.width}x${it.height}" }}")
    }

    @OptIn(kotlin.time.ExperimentalTime::class)
    @Test
    fun testUpdateObjectToJsonSerialization() {
        // Create a LocalDateTime and convert to expected Unix timestamp
        val testDate = Instant.fromEpochSeconds(1758837119).toLocalDateTime(TimeZone.of("Europe/Moscow"))
        
        // Create test objects
        val user = User(
            id = 5247805674L,
            isBot = false,
            firstName = "Андрей",
            username = "Khz228",
            languageCode = "ru"
        )
        
        val chat = Chat(
            id = -1001772241729L,
            type = "supergroup",
            title = "C4_VTR - чат о Citroen C4",
            username = "C4_VTR"
        )
        
        val photoSize = PhotoSize(
            fileId = "AgACAgIAAx0CaaJDQQABCTGSaNW5f1cYuZJsKiinXPHArdI_PtIAAvL9MRsRxrBKyyVn62wugcgBAAMCAANzAAM2BA",
            fileUniqueId = "AQAD8v0xGxHGsEp4",
            width = 67,
            height = 90,
            fileSize = 1754
        )
        
        val message = Message(
            messageId = 602514L,
            from = user,
            date = testDate,
            chat = chat,
            mediaGroupId = "14070696956143906",
            photo = listOf(photoSize),
            caption = "Test caption"
        )
        
        val update = Update(
            updateId = 369518029L,
            message = message
        )
        
        // Serialize Update object to JSON
        val serializedJson = json.encodeToString(Update.serializer(), update)
        
        // Parse the JSON to verify structure
        val jsonElement = json.parseToJsonElement(serializedJson)
        val jsonObject = jsonElement.jsonObject
        
        // Verify top-level Update fields
        assertEquals(369518029L, jsonObject["update_id"]?.jsonPrimitive?.long)
        
        // Verify message exists and has correct structure
        val messageObject = jsonObject["message"]?.jsonObject
        assertNotNull(messageObject)
        assertEquals(602514L, messageObject["message_id"]?.jsonPrimitive?.long)
        
        // Most importantly: verify that the date field is serialized as Unix timestamp
        assertEquals(1758837119L, messageObject["date"]?.jsonPrimitive?.long)
        
        // Verify other message fields
        assertEquals("14070696956143906", messageObject["media_group_id"]?.jsonPrimitive?.content)
        assertEquals("Test caption", messageObject["caption"]?.jsonPrimitive?.content)
        
        // Verify user information in from field
        val fromObject = messageObject["from"]?.jsonObject
        assertNotNull(fromObject)
        assertEquals(5247805674L, fromObject["id"]?.jsonPrimitive?.long)
        assertEquals("Андрей", fromObject["first_name"]?.jsonPrimitive?.content)
        assertEquals("Khz228", fromObject["username"]?.jsonPrimitive?.content)
        
        // Verify chat information
        val chatObject = messageObject["chat"]?.jsonObject
        assertNotNull(chatObject)
        assertEquals(-1001772241729L, chatObject["id"]?.jsonPrimitive?.long)
        assertEquals("supergroup", chatObject["type"]?.jsonPrimitive?.content)
        assertEquals("C4_VTR - чат о Citroen C4", chatObject["title"]?.jsonPrimitive?.content)
        
        println("✅ Update serialization test passed successfully!")
        println("Serialized JSON contains correct Unix timestamp: 1758837119")
        println("Date field properly converted from LocalDateTime to Unix timestamp")
    }
}