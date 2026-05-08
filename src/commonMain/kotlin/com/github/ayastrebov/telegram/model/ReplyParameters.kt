package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Describes reply parameters for the message that is being sent.
 *
 * Use this instead of the legacy `replyToMessageId` parameter for richer reply semantics
 * including cross-chat replies, quotes, and poll option replies.
 */
@Serializable
public data class ReplyParameters(
    @SerialName("message_id")
    val messageId: Long,

    @SerialName("chat_id")
    val chatId: String? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    val quote: String? = null,

    @SerialName("quote_parse_mode")
    val quoteParseMode: String? = null,

    @SerialName("quote_entities")
    val quoteEntities: List<MessageEntity>? = null,

    @SerialName("quote_position")
    val quotePosition: Int? = null,

    @SerialName("poll_option_id")
    val pollOptionId: String? = null,
)
