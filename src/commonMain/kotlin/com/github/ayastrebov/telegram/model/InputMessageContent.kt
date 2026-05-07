package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed class InputMessageContent {
    @Serializable
    public class InputTextMessageContent(
        @SerialName("message_text")
        public val messageText: String,

        @SerialName("parse_mode")
        public val parseMode: ParseMode? = null,

        @SerialName("entities")
        public val entities: List<MessageEntity>? = null,
    ): InputMessageContent()
}
