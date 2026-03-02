package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.*
import kotlin.test.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class CallbackQueryHandlerTest {

    private fun makeUpdate(data: String?): Update = Update(
        updateId = 1,
        callbackQuery = CallbackQuery(
            id = "cb1",
            from = User(id = 1, firstName = "Test"),
            data = data,
            chatInstance = "abc",
        ),
    )

    @Test
    fun onDataMatchesExact() = kotlinx.coroutines.test.runTest {
        var handled = false
        val handler = CallbackQueryHandler()
        handler.registerCallbacks {
            onData("approve") { handled = true }
        }

        assertTrue(handler.handleUpdate(makeUpdate("approve")))
        assertTrue(handled)
    }

    @Test
    fun onPrefixMatchesPrefix() = kotlinx.coroutines.test.runTest {
        var handled = false
        val handler = CallbackQueryHandler()
        handler.registerCallbacks {
            onPrefix("page_") { handled = true }
        }

        assertTrue(handler.handleUpdate(makeUpdate("page_5")))
        assertTrue(handled)
    }

    @Test
    fun noMatchReturnsFalse() = kotlinx.coroutines.test.runTest {
        val handler = CallbackQueryHandler()
        handler.registerCallbacks {
            onData("approve") { }
        }

        assertFalse(handler.handleUpdate(makeUpdate("reject")))
    }

    @Test
    fun nullDataReturnsFalse() = kotlinx.coroutines.test.runTest {
        val handler = CallbackQueryHandler()
        handler.registerCallbacks {
            onData("anything") { }
        }

        assertFalse(handler.handleUpdate(makeUpdate(null)))
    }
}
