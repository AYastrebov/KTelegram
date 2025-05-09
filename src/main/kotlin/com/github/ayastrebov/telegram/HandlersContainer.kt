package com.github.ayastrebov.telegram

import com.github.ayastrebov.telegram.model.Update

interface Handler {
    suspend fun handleUpdate(update: Update): Boolean
}

internal class RegistrationImp : HandlerRegistration {
    private val handlers = mutableListOf<Handler>()

    override fun register(handler: Handler) {
        handlers.add(handler)
    }

    override suspend fun processUpdate(update: Update) {

        if (update.message == null &&
            update.inlineQuery == null &&
            update.editedMessage == null &&
            update.channelPost == null &&
            update.editedChannelPost == null &&
            update.callbackQuery == null &&
            update.chosenInlineResult == null
        ) {
            // Stop bot, do nothing
            return
        }

        for (handler in handlers) {
            if (handler.handleUpdate(update)) {
                return
            }
        }
    }
}

interface HandlerRegistration {
    companion object {
        fun create(): HandlerRegistration = RegistrationImp()
    }

    fun register(handler: Handler)
    suspend fun processUpdate(update: Update)
}

class HandlersContainer {
    private val handlers = HandlerRegistration.create()

    fun registerHandlers(registration: HandlerRegistration.() -> Unit) = registration.invoke(handlers)
    suspend fun processUpdate(update: Update) = handlers.processUpdate(update)
}

