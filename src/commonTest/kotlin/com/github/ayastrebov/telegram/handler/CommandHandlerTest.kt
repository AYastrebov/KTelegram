package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.*
import kotlin.test.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class CommandHandlerTest {

    private fun makeUpdate(text: String, entities: List<MessageEntity>? = null): Update {
        val defaultEntities = entities ?: listOf(MessageEntity(offset = 0, length = text.indexOf(' ').let { if (it == -1) text.length else it }, type = "bot_command"))
        return Update(
            updateId = 1,
            message = Message(
                messageId = 1,
                chat = Chat(id = 1, type = "private"),
                date = kotlin.time.Instant.fromEpochSeconds(1700000000),
                text = text,
                entities = defaultEntities,
            ),
        )
    }

    @Test
    fun matchesCommand() = kotlinx.coroutines.test.runTest {
        var handled = false
        val handler = CommandHandler("testbot")
        handler.registerCommands {
            register("start") { handled = true }
        }

        assertTrue(handler.handleUpdate(makeUpdate("/start")))
        assertTrue(handled)
    }

    @Test
    fun matchesCaseInsensitive() = kotlinx.coroutines.test.runTest {
        var handled = false
        val handler = CommandHandler("testbot")
        handler.registerCommands {
            register("start") { handled = true }
        }

        assertTrue(handler.handleUpdate(makeUpdate("/Start")))
        assertTrue(handled)
    }

    @Test
    fun stripsBotNameSuffix() = kotlinx.coroutines.test.runTest {
        var handled = false
        val handler = CommandHandler("testbot")
        handler.registerCommands {
            register("help") { handled = true }
        }

        val update = makeUpdate(
            "/help@testbot",
            listOf(MessageEntity(offset = 0, length = 13, type = "bot_command")),
        )
        assertTrue(handler.handleUpdate(update))
        assertTrue(handled)
    }

    @Test
    fun noMatchReturnsFalse() = kotlinx.coroutines.test.runTest {
        val handler = CommandHandler("testbot")
        handler.registerCommands {
            register("start") { }
        }

        assertFalse(handler.handleUpdate(makeUpdate("/unknown")))
    }

    @Test
    fun noMessageReturnsFalse() = kotlinx.coroutines.test.runTest {
        val handler = CommandHandler("testbot")
        handler.registerCommands {
            register("start") { }
        }

        assertFalse(handler.handleUpdate(Update(updateId = 1)))
    }
}
