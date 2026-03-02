package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.InlineQuery
import com.github.ayastrebov.telegram.model.Update

/**
 * Handler that triggers when the bot receives an inline query.
 *
 * @param action Suspend function invoked with the inline query.
 */
class InlineQueryHandler(
    private val action: suspend (inlineQuery: InlineQuery) -> Unit
) : UpdateHandler() {
    override suspend fun handleUpdate(update: Update): Boolean {
        update.inlineQuery?.let { inlineQuery ->
            action.invoke(inlineQuery)
            return true
        }

        return false
    }
}
