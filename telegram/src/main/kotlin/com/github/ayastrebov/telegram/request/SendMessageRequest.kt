package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.KeyboardMarkup
import com.github.ayastrebov.telegram.model.MessageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    val text: String,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    val entities: List<MessageEntity>? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_markup")
    /// InlineKeyboardMarkup or ReplyKeyboardMarkup or ReplyKeyboardRemove or ForceReply
    val replyMarkup: KeyboardMarkup? = null,
)
