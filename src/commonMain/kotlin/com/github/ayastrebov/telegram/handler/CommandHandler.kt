package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update
import com.github.ayastrebov.telegram.model.commandText

data class CommandDescriptor(
    val command: String,
    val action: suspend (update: Update) -> Unit
)

class CommandRegistration internal constructor(
    private val target: MutableList<CommandDescriptor>,
) {
    fun register(command: String, action: suspend (update: Update) -> Unit) {
        target.add(CommandDescriptor(command, action))
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

    private val commands = mutableListOf<CommandDescriptor>()

    fun registerCommands(registration: CommandRegistration.() -> Unit) {
        registration.invoke(CommandRegistration(commands))
    }

    override suspend fun handleUpdate(update: Update): Boolean {
        val rawCommand = update.message?.commandText?.toString() ?: return false
        val commandBotName = rawCommand.substringAfter('@', missingDelimiterValue = "")
        if (commandBotName.isNotEmpty() && commandBotName.equals(botName, ignoreCase = true).not()) {
            return false
        }

        val command = rawCommand.substringBefore('@')
        for (descriptor in commands) {
            if (command.contentEquals(descriptor.command, true)) {
                descriptor.action.invoke(update)
                return true
            }
        }

        return false
    }
}
