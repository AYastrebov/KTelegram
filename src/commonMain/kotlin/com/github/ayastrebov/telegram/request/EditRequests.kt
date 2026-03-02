package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.InlineKeyboardMarkup
import com.github.ayastrebov.telegram.model.MessageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [editMessageText][com.github.ayastrebov.telegram.Bot.editMessageText].
 */
@Serializable
data class EditMessageTextRequest(
    @SerialName("chat_id")
    val chatId: String? = null,

    @SerialName("message_id")
    val messageId: Long? = null,

    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    val text: String,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    val entities: List<MessageEntity>? = null,

    @SerialName("disable_web_page_preview")
    val disableWebPagePreview: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
)

/**
 * Parameters for [editMessageCaption][com.github.ayastrebov.telegram.Bot.editMessageCaption].
 */
@Serializable
data class EditMessageCaptionRequest(
    @SerialName("chat_id")
    val chatId: String? = null,

    @SerialName("message_id")
    val messageId: Long? = null,

    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
)

/**
 * Parameters for [editMessageReplyMarkup][com.github.ayastrebov.telegram.Bot.editMessageReplyMarkup].
 */
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

/**
 * Parameters for [deleteMessage][com.github.ayastrebov.telegram.Bot.deleteMessage].
 */
@Serializable
data class DeleteMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_id")
    val messageId: Long,
)
