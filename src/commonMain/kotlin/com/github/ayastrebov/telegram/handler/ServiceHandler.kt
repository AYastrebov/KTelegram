package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update
import com.github.ayastrebov.telegram.model.commandEntity
import com.github.ayastrebov.telegram.model.isGroup

/**
 * Handler for bot commands in group chats that are NOT in the restricted list.
 *
 * Useful for applying service-level logic (e.g., auto-responses) to groups the bot monitors.
 *
 * @param restrictedChatIds Chat IDs where this handler should NOT trigger.
 */
class ServiceHandler(
    private val restrictedChatIds: List<String>
) : UpdateHandler() {

    private val restrictedChatIdsSet = restrictedChatIds.toSet()
    private val actions = mutableListOf<MessageDescriptor>()

    fun registerActions(registration: MessageRegistration.() -> Unit) {
        registration.invoke(MessageRegistration(actions))
    }

    override suspend fun handleUpdate(update: Update): Boolean {
        val chat = update.message?.chat ?: return false
        val chatId = chat.id.toString()

        if (update.message.commandEntity != null && chat.isGroup && restrictedChatIdsSet.contains(chatId).not()) {
            for (descriptor in actions) {
                if (descriptor.action.invoke(update)) {
                    return true
                }
            }
        }

        return false
    }
}
