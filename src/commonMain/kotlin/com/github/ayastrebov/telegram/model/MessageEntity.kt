package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents one special entity in a text message (e.g., hashtags, usernames, URLs, bot commands, etc.).
 */
@Serializable
data class MessageEntity(
    val offset: Int,
    val length: Int,
    val type: String,
    val url: String? = null,
    val language: String? = null,
    val user: User? = null,

    @SerialName("custom_emoji_id")
    val customEmojiId: String? = null,
)
