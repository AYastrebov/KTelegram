package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the unique identifier of a message, returned by [copyMessage][com.github.ayastrebov.telegram.Bot.copyMessage].
 */
@Serializable
data class MessageId(
    @SerialName("message_id")
    val messageId: Long,
)
