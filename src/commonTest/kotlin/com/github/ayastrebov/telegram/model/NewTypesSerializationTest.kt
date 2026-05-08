package com.github.ayastrebov.telegram.model

import kotlinx.serialization.json.Json
import kotlin.test.*

class NewTypesSerializationTest {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    // --- Poll types ---

    @Test
    fun deserializePoll() {
        val input = """
        {
            "id": "poll1",
            "question": "Favorite color?",
            "options": [
                {"persistent_id": "opt1", "text": "Red", "voter_count": 5},
                {"persistent_id": "opt2", "text": "Blue", "voter_count": 3}
            ],
            "total_voter_count": 8,
            "is_closed": false,
            "is_anonymous": true,
            "type": "regular",
            "allows_multiple_answers": false,
            "allows_revoting": true,
            "description": "Pick one"
        }
        """.trimIndent()

        val poll = json.decodeFromString<Poll>(input)
        assertEquals("poll1", poll.id)
        assertEquals("Favorite color?", poll.question)
        assertEquals(2, poll.options.size)
        assertEquals("opt1", poll.options[0].persistentId)
        assertEquals(5, poll.options[0].voterCount)
        assertEquals(8, poll.totalVoterCount)
        assertTrue(poll.allowsRevoting)
        assertEquals("Pick one", poll.description)
    }

    @Test
    fun deserializePollWithQuizFields() {
        val input = """
        {
            "id": "quiz1",
            "question": "2+2?",
            "options": [
                {"persistent_id": "a", "text": "3", "voter_count": 1},
                {"persistent_id": "b", "text": "4", "voter_count": 9}
            ],
            "total_voter_count": 10,
            "is_closed": false,
            "is_anonymous": false,
            "type": "quiz",
            "allows_multiple_answers": false,
            "allows_revoting": false,
            "correct_option_ids": [1],
            "explanation": "Basic math"
        }
        """.trimIndent()

        val poll = json.decodeFromString<Poll>(input)
        assertEquals("quiz", poll.type)
        assertEquals(listOf(1), poll.correctOptionIds)
        assertEquals("Basic math", poll.explanation)
    }

    @Test
    fun deserializePollAnswer() {
        val input = """
        {
            "poll_id": "poll1",
            "user": {"id": 123, "is_bot": false, "first_name": "Test"},
            "option_ids": [0, 1],
            "option_persistent_ids": ["opt1", "opt2"]
        }
        """.trimIndent()

        val answer = json.decodeFromString<PollAnswer>(input)
        assertEquals("poll1", answer.pollId)
        assertEquals(123L, answer.user?.id)
        assertEquals(listOf(0, 1), answer.optionIds)
        assertEquals(listOf("opt1", "opt2"), answer.optionPersistentIds)
    }

    @Test
    fun deserializePollOptionAdded() {
        val input = """
        {
            "option_persistent_id": "new_opt",
            "option_text": "New option"
        }
        """.trimIndent()

        val added = json.decodeFromString<PollOptionAdded>(input)
        assertEquals("new_opt", added.optionPersistentId)
        assertEquals("New option", added.optionText)
    }

    // --- Managed Bot types ---

    @Test
    fun deserializeManagedBotCreated() {
        val input = """
        {
            "bot": {"id": 999, "is_bot": true, "first_name": "NewBot"}
        }
        """.trimIndent()

        val created = json.decodeFromString<ManagedBotCreated>(input)
        assertEquals(999L, created.bot.id)
        assertTrue(created.bot.isBot)
    }

    @Test
    fun deserializeManagedBotUpdated() {
        val input = """
        {
            "user": {"id": 1, "is_bot": false, "first_name": "Owner"},
            "bot": {"id": 999, "is_bot": true, "first_name": "ManagedBot"}
        }
        """.trimIndent()

        val updated = json.decodeFromString<ManagedBotUpdated>(input)
        assertEquals(1L, updated.user.id)
        assertEquals(999L, updated.bot.id)
    }

    @Test
    fun deserializePreparedKeyboardButton() {
        val input = """{"id": "btn_123"}"""
        val btn = json.decodeFromString<PreparedKeyboardButton>(input)
        assertEquals("btn_123", btn.id)
    }

    // --- ChatOwner types ---

    @Test
    fun deserializeChatOwnerLeft() {
        val input = "{}"
        json.decodeFromString<ChatOwnerLeft>(input)
    }

    @Test
    fun deserializeChatOwnerChanged() {
        val input = "{}"
        json.decodeFromString<ChatOwnerChanged>(input)
    }

    // --- ChatJoinRequest ---

    @Test
    fun deserializeChatJoinRequest() {
        val input = """
        {
            "chat": {"id": -100123, "type": "supergroup", "title": "Test"},
            "from": {"id": 456, "is_bot": false, "first_name": "Applicant"},
            "user_chat_id": 456,
            "date": 1700000000,
            "bio": "Hello there"
        }
        """.trimIndent()

        val request = json.decodeFromString<ChatJoinRequest>(input)
        assertEquals(-100123L, request.chat.id)
        assertEquals(456L, request.from.id)
        assertEquals("Hello there", request.bio)
    }

    // --- Update with new fields ---

    @Test
    fun deserializeUpdateWithPoll() {
        val input = """
        {
            "update_id": 500,
            "poll": {
                "id": "p1",
                "question": "Test?",
                "options": [{"persistent_id": "a", "text": "Yes", "voter_count": 0}],
                "total_voter_count": 0,
                "is_closed": false,
                "is_anonymous": true,
                "type": "regular",
                "allows_multiple_answers": false,
                "allows_revoting": false
            }
        }
        """.trimIndent()

        val update = json.decodeFromString<Update>(input)
        val poll = assertNotNull(update.poll)
        assertEquals("p1", poll.id)
    }

    @Test
    fun deserializeUpdateWithPollAnswer() {
        val input = """
        {
            "update_id": 501,
            "poll_answer": {
                "poll_id": "p1",
                "user": {"id": 1, "is_bot": false, "first_name": "Voter"},
                "option_ids": [0],
                "option_persistent_ids": ["a"]
            }
        }
        """.trimIndent()

        val update = json.decodeFromString<Update>(input)
        val answer = assertNotNull(update.pollAnswer)
        assertEquals("p1", answer.pollId)
    }

    @Test
    fun deserializeUpdateWithManagedBot() {
        val input = """
        {
            "update_id": 502,
            "managed_bot": {
                "user": {"id": 1, "is_bot": false, "first_name": "Admin"},
                "bot": {"id": 999, "is_bot": true, "first_name": "Bot"}
            }
        }
        """.trimIndent()

        val update = json.decodeFromString<Update>(input)
        val managed = assertNotNull(update.managedBot)
        assertEquals(999L, managed.bot.id)
    }

    @Test
    fun deserializeUpdateWithChatJoinRequest() {
        val input = """
        {
            "update_id": 503,
            "chat_join_request": {
                "chat": {"id": -100123, "type": "supergroup", "title": "Group"},
                "from": {"id": 456, "is_bot": false, "first_name": "User"},
                "user_chat_id": 456,
                "date": 1700000000
            }
        }
        """.trimIndent()

        val update = json.decodeFromString<Update>(input)
        val joinReq = assertNotNull(update.chatJoinRequest)
        assertEquals(456L, joinReq.from.id)
    }

    // --- Message with new fields ---

    @Test
    fun deserializeMessageWithManagedBotCreated() {
        val input = """
        {
            "message_id": 100,
            "chat": {"id": 1, "type": "private"},
            "date": 1700000000,
            "managed_bot_created": {"bot": {"id": 999, "is_bot": true, "first_name": "NewBot"}}
        }
        """.trimIndent()

        val msg = json.decodeFromString<Message>(input)
        val created = assertNotNull(msg.managedBotCreated)
        assertEquals(999L, created.bot.id)
    }

    @Test
    fun deserializeMessageWithPollOptionAdded() {
        val input = """
        {
            "message_id": 101,
            "chat": {"id": 1, "type": "private"},
            "date": 1700000000,
            "poll_option_added": {
                "option_persistent_id": "new",
                "option_text": "Added option"
            }
        }
        """.trimIndent()

        val msg = json.decodeFromString<Message>(input)
        val added = assertNotNull(msg.pollOptionAdded)
        assertEquals("new", added.optionPersistentId)
    }

    @Test
    fun deserializeMessageWithChatOwnerLeft() {
        val input = """
        {
            "message_id": 102,
            "chat": {"id": 1, "type": "supergroup", "title": "Group"},
            "date": 1700000000,
            "chat_owner_left": {}
        }
        """.trimIndent()

        val msg = json.decodeFromString<Message>(input)
        assertNotNull(msg.chatOwnerLeft)
    }

    // --- User new fields ---

    @Test
    fun deserializeUserWithCanManageBots() {
        val input = """
        {
            "id": 1,
            "is_bot": false,
            "first_name": "Admin",
            "can_manage_bots": true,
            "added_to_attachment_menu": true
        }
        """.trimIndent()

        val user = json.decodeFromString<User>(input)
        assertTrue(user.canManageBots == true)
        assertTrue(user.addedToAttachmentMenu == true)
    }

    // --- InputPollOption ---

    @Test
    fun deserializeInputPollOption() {
        val input = """{"text": "Option 1", "text_parse_mode": "HTML"}"""
        val option = json.decodeFromString<InputPollOption>(input)
        assertEquals("Option 1", option.text)
        assertEquals("HTML", option.textParseMode)
    }
}
