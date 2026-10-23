package com.github.ayastrebov.telegram.handler

import com.github.ayastrebov.telegram.model.Update
import com.github.ayastrebov.telegram.model.commandEntity
import com.github.ayastrebov.telegram.model.commandText

public data class CommandDescriptor(
    val command: String,
    val action: suspend (update: Update) -> Unit
)

public class CommandRegistration internal constructor(
    private val target: MutableList<CommandDescriptor>,
) {
    public fun register(command: String, action: suspend (update: Update) -> Unit) {
        target.add(CommandDescriptor(command, action))
    }
}

/**
 * Handler for slash commands (e.g., `/start`, `/help`).
 *
 * Only reacts to a command that starts the message text (entity offset 0), strips the `@botName`
 * suffix and matches commands case-insensitively.
 *
 * @param botName The bot's username, used to strip `@botName` suffixes from commands.
 */
public class CommandHandler(private val botName: String) : UpdateHandler() {

    private val commands = mutableListOf<CommandDescriptor>()

    public fun registerCommands(registration: CommandRegistration.() -> Unit) {
        registration.invoke(CommandRegistration(commands))
    }

    override suspend fun handleUpdate(update: Update): Boolean {
        val message = update.message ?: return false
        if (message.commandEntity?.offset != 0) return false
        val rawCommand = message.commandText?.toString() ?: return false
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
