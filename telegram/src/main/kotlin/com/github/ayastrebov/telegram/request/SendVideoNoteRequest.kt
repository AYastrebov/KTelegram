package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.KeyboardMarkup
import com.github.ayastrebov.telegram.model.PhotoSize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendVideoNoteRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("video_note")
    val videoNote: String,

    val duration: Int? = null,
    val length: Int? = null,
    val thumb: PhotoSize? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    /// InlineKeyboardMarkup or ReplyKeyboardMarkup or ReplyKeyboardRemove or ForceReply
    val replyMarkup: KeyboardMarkup? = null,
)
