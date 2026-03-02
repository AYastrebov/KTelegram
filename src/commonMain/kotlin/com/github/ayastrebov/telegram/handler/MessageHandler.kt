package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update

data class MessageDescriptor(
    val filter: (message: String) -> Boolean,
    val action: suspend (update: Update) -> Boolean
)

class MessageRegistration internal constructor(
    private val target: MutableList<MessageDescriptor>,
) {
    fun register(
        action: suspend (update: Update) -> Boolean,
        filter: (message: String) -> Boolean = { it.isNotBlank() },
    ) {
        target.add(MessageDescriptor(filter, action))
    }
}

/**
 * Handler for text messages with customizable filters.
 *
 * Each registered action has a filter predicate; the first matching action that returns `true` stops the chain.
 */
class MessageHandler : UpdateHandler() {

    private val actions = mutableListOf<MessageDescriptor>()

    fun registerActions(registration: MessageRegistration.() -> Unit) {
        registration.invoke(MessageRegistration(actions))
    }

    override suspend fun handleUpdate(update: Update): Boolean {
        val messageText = update.message?.text ?: return false

        for (descriptor in actions) {
            if (descriptor.filter(messageText)) {
                if (descriptor.action.invoke(update)) {
                    return true
                }
            }
        }

        return false
    }
}
