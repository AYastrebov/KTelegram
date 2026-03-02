package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update
import com.github.ayastrebov.telegram.model.commandText

interface CommandRegistration {
    data class Descriptor(
        val command: String,
        val action: suspend (update: Update) -> Unit
    )

    fun register(command: String, action: suspend (update: Update) -> Unit)

    val descriptors: List<Descriptor>

    companion object {
        fun create(): CommandRegistration = CommandRegistrationImp()
    }
}

private class CommandRegistrationImp : CommandRegistration {
    override val descriptors = mutableListOf<CommandRegistration.Descriptor>()

    override fun register(command: String, action: suspend (update: Update) -> Unit) {
        descriptors.add(CommandRegistration.Descriptor(command, action))
    }
}

/**
 * Handler for slash commands (e.g., `/start`, `/help`).
 *
 * Strips the `@botName` suffix and matches commands case-insensitively.
 *
 * @param botName The bot's username, used to strip `@botName` suffixes from commands.
 */
class CommandHandler(private val botName: String) : UpdateHandler() {

    private val commands = CommandRegistration.create()

    fun registerCommands(registration: CommandRegistration.() -> Unit) = registration.invoke(commands)

    override suspend fun handleUpdate(update: Update): Boolean {
        update.message?.commandText?.let {
            for (descriptor in commands.descriptors) {
                if (it.removeSuffix("@$botName").contentEquals(descriptor.command, true)) {
                    descriptor.action.invoke(update)
                    return true
                }
            }
        }

        return false
    }
}
