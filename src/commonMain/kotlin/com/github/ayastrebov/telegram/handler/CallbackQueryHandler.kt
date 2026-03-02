package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.CallbackQuery
import com.github.ayastrebov.telegram.model.Update

/**
 * Registration DSL for callback query handlers.
 */
interface CallbackRegistration {
    data class Descriptor(
        val matcher: (data: String) -> Boolean,
        val action: suspend (callbackQuery: CallbackQuery) -> Unit,
    )

    /** Register a handler that matches when callback data equals [exact]. */
    fun onData(exact: String, action: suspend (callbackQuery: CallbackQuery) -> Unit)

    /** Register a handler that matches when callback data starts with [prefix]. */
    fun onPrefix(prefix: String, action: suspend (callbackQuery: CallbackQuery) -> Unit)

    /** Register a handler with a custom [matcher]. */
    fun register(matcher: (data: String) -> Boolean, action: suspend (callbackQuery: CallbackQuery) -> Unit)

    val descriptors: List<Descriptor>

    companion object {
        fun create(): CallbackRegistration = CallbackRegistrationImp()
    }
}

private class CallbackRegistrationImp : CallbackRegistration {
    override val descriptors = mutableListOf<CallbackRegistration.Descriptor>()

    override fun onData(exact: String, action: suspend (callbackQuery: CallbackQuery) -> Unit) {
        descriptors.add(CallbackRegistration.Descriptor({ it == exact }, action))
    }

    override fun onPrefix(prefix: String, action: suspend (callbackQuery: CallbackQuery) -> Unit) {
        descriptors.add(CallbackRegistration.Descriptor({ it.startsWith(prefix) }, action))
    }

    override fun register(matcher: (data: String) -> Boolean, action: suspend (callbackQuery: CallbackQuery) -> Unit) {
        descriptors.add(CallbackRegistration.Descriptor(matcher, action))
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

    private val callbacks = CallbackRegistration.create()

    fun registerCallbacks(registration: CallbackRegistration.() -> Unit) = registration.invoke(callbacks)

    override suspend fun handleUpdate(update: Update): Boolean {
        val callbackQuery = update.callbackQuery ?: return false
        val data = callbackQuery.data ?: return false

        for (descriptor in callbacks.descriptors) {
            if (descriptor.matcher(data)) {
                descriptor.action.invoke(callbackQuery)
                return true
            }
        }

        return false
    }
}
