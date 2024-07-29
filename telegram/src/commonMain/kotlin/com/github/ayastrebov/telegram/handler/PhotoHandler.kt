package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update

class PhotoHandler(
    private val action: (update: Update) -> Unit
) : UpdateHandler() {

    override suspend fun handleUpdate(update: Update): Boolean {
        if (update.message?.photo == null) {
            return false
        }

        logger.info("Handle photo")
        action.invoke(update)
        return true
    }
}