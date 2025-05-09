package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.KeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendVideoRequest(
    @SerialName("chat_id")
    val chatId: String,

    val video: String,
    val caption: String? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_markup")
    /// InlineKeyboardMarkup or ReplyKeyboardMarkup or ReplyKeyboardRemove or ForceReply
    val replyMarkup: KeyboardMarkup? = null,
)
