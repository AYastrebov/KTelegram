package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.CallbackQuery
import com.github.ayastrebov.telegram.model.Update

data class CallbackDescriptor(
    val matcher: (data: String) -> Boolean,
    val action: suspend (callbackQuery: CallbackQuery) -> Unit,
)

/**
 * Registration DSL for callback query handlers.
 */
class CallbackRegistration internal constructor(
    private val target: MutableList<CallbackDescriptor>,
) {
    /** Register a handler that matches when callback data equals [exact]. */
    fun onData(exact: String, action: suspend (callbackQuery: CallbackQuery) -> Unit) {
        target.add(CallbackDescriptor({ it == exact }, action))
    }

    /** Register a handler that matches when callback data starts with [prefix]. */
    fun onPrefix(prefix: String, action: suspend (callbackQuery: CallbackQuery) -> Unit) {
        target.add(CallbackDescriptor({ it.startsWith(prefix) }, action))
    }

    /** Register a handler with a custom [matcher]. */
    fun register(matcher: (data: String) -> Boolean, action: suspend (callbackQuery: CallbackQuery) -> Unit) {
        target.add(CallbackDescriptor(matcher, action))
    }
}

/**
 * Handler for callback queries from inline keyboards.
 *
 * ```kotlin
 * val handler = CallbackQueryHandler()
 * handler.registerCallbacks {
 *     onData("approve") { query -> bot.answerCallbackQuery(...) }
 *     onPrefix("page_") { query -> /* handle pagination */ }
 * }
 * ```
 */
class CallbackQueryHandler : UpdateHandler() {

    private val callbacks = mutableListOf<CallbackDescriptor>()

    fun registerCallbacks(registration: CallbackRegistration.() -> Unit) {
        registration.invoke(CallbackRegistration(callbacks))
    }

    override suspend fun handleUpdate(update: Update): Boolean {
        val callbackQuery = update.callbackQuery ?: return false
        val data = callbackQuery.data ?: return false

        for (descriptor in callbacks) {
            if (descriptor.matcher(data)) {
                descriptor.action.invoke(callbackQuery)
                return true
            }
        }

        return false
    }
}
