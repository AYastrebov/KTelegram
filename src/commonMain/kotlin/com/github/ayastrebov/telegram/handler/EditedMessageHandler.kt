package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Message
import com.github.ayastrebov.telegram.model.Update

/**
 * Handler that triggers when a message is edited.
 *
 * @param action Suspend function invoked with the edited message.
 */
class EditedMessageHandler(
    private val action: suspend (message: Message) -> Unit
) : UpdateHandler() {

    override suspend fun handleUpdate(update: Update): Boolean {
        val edited = update.editedMessage ?: return false
        action.invoke(edited)
        return true
    }
}
