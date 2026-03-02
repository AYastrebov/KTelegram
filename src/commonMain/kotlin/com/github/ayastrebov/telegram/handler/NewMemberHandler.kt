package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update

/**
 * Handler that triggers when a new (non-bot) member joins a chat.
 */
class NewMemberHandler : UpdateHandler() {

    private val actions = MessageRegistration.create()

    fun registerActions(registration: MessageRegistration.() -> Unit) = registration.invoke(actions)

    override suspend fun handleUpdate(update: Update): Boolean {
        update.message?.newChatMembers?.firstOrNull()?.let {
            if (it.isBot) {
                return false
            }

            for (descriptor in actions.descriptors) {
                if (descriptor.action.invoke(update)) {
                    return true
                }
            }
        }

        return false
    }
}
