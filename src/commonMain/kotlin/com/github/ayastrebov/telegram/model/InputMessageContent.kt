package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class InputMessageContent {
    @Serializable
    class InputTextMessageContent(
        @SerialName("message_text")
        val messageText: String,

        @SerialName("parse_mode")
        val parseMode: ParseMode? = null,

        @SerialName("entities")
        val entities: List<MessageEntity>? = null,
    ): InputMessageContent()
}
