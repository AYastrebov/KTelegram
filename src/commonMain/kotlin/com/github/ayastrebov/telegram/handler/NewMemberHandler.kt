package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update

/**
 * Handler that triggers when a new (non-bot) member joins a chat.
 */
class NewMemberHandler : UpdateHandler() {

    private val actions = mutableListOf<MessageDescriptor>()

    fun registerActions(registration: MessageRegistration.() -> Unit) {
        registration.invoke(MessageRegistration(actions))
    }

    override suspend fun handleUpdate(update: Update): Boolean {
        val hasNonBotMembers = update.message?.newChatMembers?.any { member -> member.isBot.not() } ?: return false
        if (hasNonBotMembers.not()) return false

        for (descriptor in actions) {
            if (descriptor.action.invoke(update)) {
                return true
            }
        }

        return false
    }
}
