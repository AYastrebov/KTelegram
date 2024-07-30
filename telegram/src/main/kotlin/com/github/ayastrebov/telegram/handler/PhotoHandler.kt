package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update

class PhotoHandler(
    private val action: (update: Update) -> Unit
) : UpdateHandler() {

    override suspend fun handleUpdate(update: Update): Boolean {
        update.message?.photo?.let {
            logger.info("Handle photo")
            action.invoke(update)
            return true
        }

        return false
    }
}