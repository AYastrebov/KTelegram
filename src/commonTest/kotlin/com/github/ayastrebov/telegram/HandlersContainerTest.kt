package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.*
import kotlin.test.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class HandlersContainerTest {

    private val testUpdate = Update(
        updateId = 1,
        message = Message(
            messageId = 1,
            chat = Chat(id = 1, type = "private"),
            date = kotlin.time.Instant.fromEpochSeconds(1700000000),
            text = "test",
        ),
    )

    @Test
    fun firstMatchStopsChain() = kotlinx.coroutines.test.runTest {
        var firstCalled = false
        var secondCalled = false
        val container = HandlersContainer()
        container.registerHandlers {
            register(object : Handler {
                override suspend fun handleUpdate(update: Update): Boolean {
                    firstCalled = true; return true
                }
            })
            register(object : Handler {
                override suspend fun handleUpdate(update: Update): Boolean {
                    secondCalled = true; return true
                }
            })
        }

        container.processUpdate(testUpdate)
        assertTrue(firstCalled)
        assertFalse(secondCalled)
    }

    @Test
    fun fallsThroughWhenNotHandled() = kotlinx.coroutines.test.runTest {
        var secondCalled = false
        val container = HandlersContainer()
        container.registerHandlers {
            register(object : Handler {
                override suspend fun handleUpdate(update: Update) = false
            })
            register(object : Handler {
                override suspend fun handleUpdate(update: Update): Boolean {
                    secondCalled = true; return true
                }
            })
        }

        container.processUpdate(testUpdate)
        assertTrue(secondCalled)
    }

    @Test
    fun noMatchDoesNotThrow() = kotlinx.coroutines.test.runTest {
        val container = HandlersContainer()
        container.registerHandlers {
            register(object : Handler {
                override suspend fun handleUpdate(update: Update) = false
            })
        }

        // should not throw
        container.processUpdate(testUpdate)
    }
}
