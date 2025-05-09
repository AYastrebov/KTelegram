package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update

interface MessageRegistration {
    data class Descriptor(
        val filter: (message: String) -> Boolean,
        val action: suspend (update: Update) -> Boolean
    )

    fun register(action: suspend (update: Update) -> Boolean, filter: (message: String) -> Boolean = { it.isNotBlank() })

    val descriptors: List<Descriptor>

    companion object {
        fun create(): MessageRegistration = MessageRegistrationImp()
    }
}

private class MessageRegistrationImp : MessageRegistration {
    override val descriptors = mutableListOf<MessageRegistration.Descriptor>()

    override fun register(action: suspend (update: Update) -> Boolean, filter: (message: String) -> Boolean) {
        descriptors.add(MessageRegistration.Descriptor(filter, action))
    }
}

class MessageHandler : UpdateHandler() {

    private val actions = MessageRegistration.create()

    fun registerActions(registration: MessageRegistration.() -> Unit) = registration.invoke(actions)

    override suspend fun handleUpdate(update: Update): Boolean {
        val messageText = update.message?.text ?: return false

        for (descriptor in actions.descriptors) {
            if (descriptor.filter(messageText)) {
                if (descriptor.action.invoke(update)) {
                    logger.info("Handle message: $messageText")
                    return true
                }
            }
        }

        return false
    }
}