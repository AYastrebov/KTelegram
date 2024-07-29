package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.InlineQuery
import com.github.ayastrebov.telegram.model.Update

class InlineQueryHandler(
    private val action: suspend (inlineQuery: InlineQuery) -> Unit
) : UpdateHandler() {
    override suspend fun handleUpdate(update: Update): Boolean {
        update.inlineQuery?.let { inlineQuery ->
            logger.info("Handle inline query: $inlineQuery")
            action.invoke(inlineQuery)
            return true
        }

        return false
    }
}