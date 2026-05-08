package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents the content of a message to be sent as a result of an inline query. */
@Serializable
public sealed class InputMessageContent {
    /** Represents the content of a text message to be sent as a result of an inline query. */
    @Serializable
    public class InputTextMessageContent(
        @SerialName("message_text")
        public val messageText: String,

        @SerialName("parse_mode")
        public val parseMode: ParseMode? = null,

        @SerialName("entities")
        public val entities: List<MessageEntity>? = null,
    ) : InputMessageContent()
}
