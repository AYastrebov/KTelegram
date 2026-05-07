package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.Update

/**
 * A handler that can process a Telegram [Update].
 *
 * Returns `true` if the update was handled (stops the chain), `false` to pass to the next handler.
 */
public interface Handler {
    public suspend fun handleUpdate(update: Update): Boolean
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
public interface HandlerRegistration {
    public companion object {
        public fun create(): HandlerRegistration = RegistrationImp()
    }

    public fun register(handler: Handler)
    public suspend fun processUpdate(update: Update)
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
public class HandlersContainer {
    private val handlers = HandlerRegistration.create()

    public fun registerHandlers(registration: HandlerRegistration.() -> Unit): Unit = registration.invoke(handlers)
    public suspend fun processUpdate(update: Update): Unit = handlers.processUpdate(update)
}
