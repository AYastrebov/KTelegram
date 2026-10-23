package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.InlineKeyboardMarkup
import com.github.ayastrebov.telegram.model.InputMedia
import com.github.ayastrebov.telegram.model.LinkPreviewOptions
import com.github.ayastrebov.telegram.model.MessageEntity
import com.github.ayastrebov.telegram.model.ParseMode
import com.github.ayastrebov.telegram.model.ReactionType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [editMessageText][com.github.ayastrebov.telegram.Bot.editMessageText].
 */
@Serializable
public data class EditMessageTextRequest(
    @SerialName("chat_id")
    val chatId: String? = null,

    @SerialName("message_id")
    val messageId: Long? = null,

    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    val text: String,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    val entities: List<MessageEntity>? = null,

    @Deprecated("Replaced by linkPreviewOptions in Bot API 7.0", ReplaceWith("linkPreviewOptions"))
    @SerialName("disable_web_page_preview")
    val disableWebPagePreview: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message to be edited was sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Link preview generation options for the message. */
    @SerialName("link_preview_options")
    val linkPreviewOptions: LinkPreviewOptions? = null,
)

/**
 * Parameters for [editMessageCaption][com.github.ayastrebov.telegram.Bot.editMessageCaption].
 */
@Serializable
public data class EditMessageCaptionRequest(
    @SerialName("chat_id")
    val chatId: String? = null,

    @SerialName("message_id")
    val messageId: Long? = null,

    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message to be edited was sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Pass True if the caption must be shown above the message media. */
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
)

/**
 * Parameters for [editMessageReplyMarkup][com.github.ayastrebov.telegram.Bot.editMessageReplyMarkup].
 */
@Serializable
public data class EditMessageReplyMarkupRequest(
    @SerialName("chat_id")
    val chatId: String? = null,

    @SerialName("message_id")
    val messageId: Long? = null,

    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message to be edited was sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,
)

/**
 * Parameters for [deleteMessage][com.github.ayastrebov.telegram.Bot.deleteMessage].
 */
@Serializable
public data class DeleteMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_id")
    val messageId: Long,
)

/**
 * Parameters for [editMessageMedia][com.github.ayastrebov.telegram.Bot.editMessageMedia].
 */
@Serializable
public data class EditMessageMediaRequest(
    /** A JSON-serialized object for the new media content of the message. */
    val media: InputMedia,

    /** Unique identifier of the business connection on behalf of which the message to be edited was sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Required if inline_message_id is not specified. */
    @SerialName("chat_id")
    val chatId: String? = null,

    /** Required if inline_message_id is not specified. */
    @SerialName("message_id")
    val messageId: Long? = null,

    /** Required if chat_id and message_id are not specified. */
    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    /** A JSON-serialized object for a new inline keyboard. */
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
)

/**
 * Parameters for [editMessageLiveLocation][com.github.ayastrebov.telegram.Bot.editMessageLiveLocation].
 */
@Serializable
public data class EditMessageLiveLocationRequest(
    /** Latitude of new location. */
    val latitude: Double,

    /** Longitude of new location. */
    val longitude: Double,

    /** Unique identifier of the business connection on behalf of which the message to be edited was sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Required if inline_message_id is not specified. */
    @SerialName("chat_id")
    val chatId: String? = null,

    /** Required if inline_message_id is not specified. */
    @SerialName("message_id")
    val messageId: Long? = null,

    /** Required if chat_id and message_id are not specified. */
    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    /** New period in seconds during which the location can be updated, starting from the message send date. */
    @SerialName("live_period")
    val livePeriod: Int? = null,

    /** The radius of uncertainty for the location, measured in meters; 0-1500. */
    @SerialName("horizontal_accuracy")
    val horizontalAccuracy: Double? = null,

    /** Direction in which the user is moving, in degrees. */
    val heading: Int? = null,

    /** The maximum distance for proximity alerts about approaching another chat member, in meters. */
    @SerialName("proximity_alert_radius")
    val proximityAlertRadius: Int? = null,

    /** A JSON-serialized object for a new inline keyboard. */
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
)

/**
 * Parameters for [stopMessageLiveLocation][com.github.ayastrebov.telegram.Bot.stopMessageLiveLocation].
 */
@Serializable
public data class StopMessageLiveLocationRequest(
    /** Unique identifier of the business connection on behalf of which the message to be edited was sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Required if inline_message_id is not specified. */
    @SerialName("chat_id")
    val chatId: String? = null,

    /** Required if inline_message_id is not specified. */
    @SerialName("message_id")
    val messageId: Long? = null,

    /** Required if chat_id and message_id are not specified. */
    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    /** A JSON-serialized object for a new inline keyboard. */
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
)

/**
 * Parameters for [setMessageReaction][com.github.ayastrebov.telegram.Bot.setMessageReaction].
 */
@Serializable
public data class SetMessageReactionRequest(
    /** Unique identifier for the target chat or username of the target bot, supergroup or channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Identifier of the target message. */
    @SerialName("message_id")
    val messageId: Long,

    /** A JSON-serialized list of reaction types to set on the message. */
    val reaction: List<ReactionType>? = null,

    /** Pass True to set the reaction with a big animation. */
    @SerialName("is_big")
    val isBig: Boolean? = null,
)

/**
 * Parameters for [stopPoll][com.github.ayastrebov.telegram.Bot.stopPoll].
 */
@Serializable
public data class StopPollRequest(
    /** Unique identifier for the target chat or username of the target bot, supergroup or channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Identifier of the original message with the poll. */
    @SerialName("message_id")
    val messageId: Long,

    /** Unique identifier of the business connection on behalf of which the message to be edited was sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object for a new message inline keyboard. */
    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
)
