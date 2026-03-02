package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update

/**
 * Handler that triggers when a message contains photos.
 *
 * @param action Suspend function invoked with the update when photos are detected.
 */
class PhotoHandler(
    private val action: suspend (update: Update) -> Unit
) : UpdateHandler() {

    override suspend fun handleUpdate(update: Update): Boolean {
        update.message?.photo?.let {
            action.invoke(update)
            return true
        }

        return false
    }
}
