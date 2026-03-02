package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.*
import kotlin.test.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class PhotoHandlerTest {

    @Test
    fun handlesPhotoMessage() = kotlinx.coroutines.test.runTest {
        var handled = false
        val handler = PhotoHandler { handled = true }

        val update = Update(
            updateId = 1,
            message = Message(
                messageId = 1,
                chat = Chat(id = 1, type = "private"),
                date = kotlin.time.Instant.fromEpochSeconds(1700000000),
                photo = listOf(PhotoSize(fileId = "p1", fileUniqueId = "u1", width = 100, height = 100)),
            ),
        )

        assertTrue(handler.handleUpdate(update))
        assertTrue(handled)
    }

    @Test
    fun ignoresNonPhotoMessage() = kotlinx.coroutines.test.runTest {
        var handled = false
        val handler = PhotoHandler { handled = true }

        val update = Update(
            updateId = 1,
            message = Message(
                messageId = 1,
                chat = Chat(id = 1, type = "private"),
                date = kotlin.time.Instant.fromEpochSeconds(1700000000),
                text = "no photo",
            ),
        )

        assertFalse(handler.handleUpdate(update))
        assertFalse(handled)
    }

    @Test
    fun noMessageReturnsFalse() = kotlinx.coroutines.test.runTest {
        val handler = PhotoHandler { }
        assertFalse(handler.handleUpdate(Update(updateId = 1)))
    }
}
