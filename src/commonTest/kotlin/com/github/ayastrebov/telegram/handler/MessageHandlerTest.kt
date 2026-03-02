package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.*
import kotlin.test.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class MessageHandlerTest {

    private fun makeUpdate(text: String): Update = Update(
        updateId = 1,
        message = Message(
            messageId = 1,
            chat = Chat(id = 1, type = "private"),
            date = kotlin.time.Instant.fromEpochSeconds(1700000000),
            text = text,
        ),
    )

    @Test
    fun matchesWithDefaultFilter() = kotlinx.coroutines.test.runTest {
        var handled = false
        val handler = MessageHandler()
        handler.registerActions {
            register(action = { handled = true; true })
        }

        assertTrue(handler.handleUpdate(makeUpdate("hello")))
        assertTrue(handled)
    }

    @Test
    fun matchesWithCustomFilter() = kotlinx.coroutines.test.runTest {
        var handled = false
        val handler = MessageHandler()
        handler.registerActions {
            register(action = { handled = true; true }, filter = { it.contains("magic") })
        }

        assertFalse(handler.handleUpdate(makeUpdate("hello")))
        assertFalse(handled)

        assertTrue(handler.handleUpdate(makeUpdate("say magic word")))
        assertTrue(handled)
    }

    @Test
    fun chainStopsOnFirstMatch() = kotlinx.coroutines.test.runTest {
        var firstCalled = false
        var secondCalled = false
        val handler = MessageHandler()
        handler.registerActions {
            register(action = { firstCalled = true; true })
            register(action = { secondCalled = true; true })
        }

        assertTrue(handler.handleUpdate(makeUpdate("test")))
        assertTrue(firstCalled)
        assertFalse(secondCalled)
    }

    @Test
    fun noTextReturnsFalse() = kotlinx.coroutines.test.runTest {
        val handler = MessageHandler()
        handler.registerActions {
            register(action = { true })
        }

        assertFalse(handler.handleUpdate(Update(updateId = 1)))
    }
}
