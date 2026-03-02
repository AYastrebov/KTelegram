package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.Update

/**
 * A handler that can process a Telegram [Update].
 *
 * Returns `true` if the update was handled (stops the chain), `false` to pass to the next handler.
 */
interface Handler {
    suspend fun handleUpdate(update: Update): Boolean
}

internal class RegistrationImp : HandlerRegistration {
    private val handlers = mutableListOf<Handler>()

    override fun register(handler: Handler) {
        handlers.add(handler)
    }

    override suspend fun processUpdate(update: Update) {
        for (handler in handlers) {
            if (handler.handleUpdate(update)) {
                return
            }
        }
    }
}

/**
 * Registry for [Handler] instances with chain-of-responsibility processing.
 *
 * Handlers are evaluated in registration order; the first handler returning `true` stops the chain.
 */
interface HandlerRegistration {
    companion object {
        fun create(): HandlerRegistration = RegistrationImp()
    }

    fun register(handler: Handler)
    suspend fun processUpdate(update: Update)
}

/**
 * Container that manages a chain of [Handler]s and dispatches updates through them.
 *
 * ```kotlin
 * val container = HandlersContainer()
 * container.registerHandlers {
 *     register(commandHandler)
 *     register(messageHandler)
 * }
 * container.processUpdate(update)
 * ```
 */
class HandlersContainer {
    private val handlers = HandlerRegistration.create()

    fun registerHandlers(registration: HandlerRegistration.() -> Unit) = registration.invoke(handlers)
    suspend fun processUpdate(update: Update) = handlers.processUpdate(update)
}
