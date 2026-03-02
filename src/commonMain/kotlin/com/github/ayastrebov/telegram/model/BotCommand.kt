package com.github.ayastrebov.telegram.model

import kotlinx.serialization.Serializable

/**
 * Represents a bot command (e.g., `/start` — Start the bot).
 *
 * @property command Text of the command; 1-32 characters. Can contain only lowercase English letters, digits and underscores.
 * @property description Description of the command; 1-256 characters.
 */
@Serializable
data class BotCommand(
    val command: String,
    val description: String,
)
