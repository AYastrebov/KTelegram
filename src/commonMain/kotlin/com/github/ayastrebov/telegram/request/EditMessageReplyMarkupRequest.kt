package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.InlineKeyboardMarkup
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditMessageReplyMarkupRequest(
    @SerialName("chat_id")
    val chatId: String? = null,

    @SerialName("message_id")
    val messageId: Long? = null,

    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
)
