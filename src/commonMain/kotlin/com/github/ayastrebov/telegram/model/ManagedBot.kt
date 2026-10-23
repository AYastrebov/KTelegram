package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Service message: a managed bot has been created by the current bot.
 */
@Serializable
public data class ManagedBotCreated(
    val bot: User,
)

/**
 * Describes a managed bot that was updated.
 *
 * Returned as part of [Update.managedBot] when a managed bot's settings change.
 */
@Serializable
public data class ManagedBotUpdated(
    val user: User,
    val bot: User,
)

/**
 * Defines the criteria used to request a managed bot.
 */
@Serializable
public data class KeyboardButtonRequestManagedBot(
    @kotlinx.serialization.SerialName("request_id")
    val requestId: Int,

    /** Suggested name for the bot. */
    @SerialName("suggested_name")
    val suggestedName: String? = null,

    /** Suggested username for the bot. */
    @SerialName("suggested_username")
    val suggestedUsername: String? = null,
)

/**
 * Describes a prepared keyboard button that was saved by the bot.
 */
@Serializable
public data class PreparedKeyboardButton(
    val id: String,
)
