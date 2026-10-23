package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.ChatAction
import com.github.ayastrebov.telegram.model.EphemeralMessageParameters
import com.github.ayastrebov.telegram.model.InputMedia
import com.github.ayastrebov.telegram.model.KeyboardMarkup
import com.github.ayastrebov.telegram.model.LinkPreviewOptions
import com.github.ayastrebov.telegram.model.MessageEntity
import com.github.ayastrebov.telegram.model.ParseMode
import com.github.ayastrebov.telegram.model.ReplyParameters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [sendMessage][com.github.ayastrebov.telegram.Bot.sendMessage].
 */
@Serializable
public data class SendMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val text: String,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    val entities: List<MessageEntity>? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Link preview generation options for the message. */
    @SerialName("link_preview_options")
    val linkPreviewOptions: LinkPreviewOptions? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendPhoto][com.github.ayastrebov.telegram.Bot.sendPhoto].
 */
@Serializable
public data class SendPhotoRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val photo: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Pass True if the caption must be shown above the message media. */
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,

    /** Pass True if the photo needs to be covered with a spoiler animation. */
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendAudio][com.github.ayastrebov.telegram.Bot.sendAudio].
 */
@Serializable
public data class SendAudioRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val audio: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    val duration: Int? = null,
    val performer: String? = null,
    val title: String? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. */
    val thumbnail: String? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendDocument][com.github.ayastrebov.telegram.Bot.sendDocument].
 */
@Serializable
public data class SendDocumentRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val document: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. */
    val thumbnail: String? = null,

    /** Disables automatic server-side content type detection for files uploaded using multipart/form-data. */
    @SerialName("disable_content_type_detection")
    val disableContentTypeDetection: Boolean? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendVideo][com.github.ayastrebov.telegram.Bot.sendVideo].
 */
@Serializable
public data class SendVideoRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val video: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    val duration: Int? = null,
    val width: Int? = null,
    val height: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. */
    val thumbnail: String? = null,

    /** Cover for the video in the message. */
    val cover: String? = null,

    /** Start timestamp for the video in the message. */
    @SerialName("start_timestamp")
    val startTimestamp: Int? = null,

    /** Pass True if the caption must be shown above the message media. */
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,

    /** Pass True if the video needs to be covered with a spoiler animation. */
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,

    /** Pass True if the uploaded video is suitable for streaming. */
    @SerialName("supports_streaming")
    val supportsStreaming: Boolean? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendAnimation][com.github.ayastrebov.telegram.Bot.sendAnimation].
 */
@Serializable
public data class SendAnimationRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val animation: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    val duration: Int? = null,
    val width: Int? = null,
    val height: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. */
    val thumbnail: String? = null,

    /** Pass True if the caption must be shown above the message media. */
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,

    /** Pass True if the animation needs to be covered with a spoiler animation. */
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendVoice][com.github.ayastrebov.telegram.Bot.sendVoice].
 */
@Serializable
public data class SendVoiceRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val voice: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    val duration: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendVideoNote][com.github.ayastrebov.telegram.Bot.sendVideoNote].
 */
@Serializable
public data class SendVideoNoteRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    @SerialName("video_note")
    val videoNote: String,

    val duration: Int? = null,
    val length: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. */
    val thumbnail: String? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendLocation][com.github.ayastrebov.telegram.Bot.sendLocation].
 */
@Serializable
public data class SendLocationRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val latitude: Double,
    val longitude: Double,

    @SerialName("horizontal_accuracy")
    val horizontalAccuracy: Double? = null,

    @SerialName("live_period")
    val livePeriod: Int? = null,

    val heading: Int? = null,

    @SerialName("proximity_alert_radius")
    val proximityAlertRadius: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendContact][com.github.ayastrebov.telegram.Bot.sendContact].
 */
@Serializable
public data class SendContactRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    @SerialName("phone_number")
    val phoneNumber: String,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String? = null,

    val vcard: String? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendDice][com.github.ayastrebov.telegram.Bot.sendDice].
 */
@Serializable
public data class SendDiceRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val emoji: String? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [sendSticker][com.github.ayastrebov.telegram.Bot.sendSticker].
 */
@Serializable
public data class SendStickerRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val sticker: String,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Emoji associated with the sticker; only for just uploaded stickers. */
    val emoji: String? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [forwardMessage][com.github.ayastrebov.telegram.Bot.forwardMessage].
 */
@Serializable
public data class ForwardMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    @SerialName("from_chat_id")
    val fromChatId: String,

    @SerialName("message_id")
    val messageId: Long,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("message_effect_id")
    val messageEffectId: String? = null,

    /** New start timestamp for the forwarded video in the message. */
    @SerialName("video_start_timestamp")
    val videoStartTimestamp: Int? = null,
)

/**
 * Parameters for [copyMessage][com.github.ayastrebov.telegram.Bot.copyMessage].
 */
@Serializable
public data class CopyMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    @SerialName("from_chat_id")
    val fromChatId: String,

    @SerialName("message_id")
    val messageId: Long,

    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @Deprecated("Replaced by replyParameters in Bot API 7.0", ReplaceWith("replyParameters"))
    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    @SerialName("message_effect_id")
    val messageEffectId: String? = null,

    /** New start timestamp for the copied video in the message. */
    @SerialName("video_start_timestamp")
    val videoStartTimestamp: Int? = null,

    /** Pass True if the caption must be shown above the message media. */
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Description of the message to reply to. */
    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,
)

/**
 * Parameters for [forwardMessages][com.github.ayastrebov.telegram.Bot.forwardMessages].
 */
@Serializable
public data class ForwardMessagesRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    @SerialName("from_chat_id")
    val fromChatId: String,

    @SerialName("message_ids")
    val messageIds: List<Long>,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,
)

/**
 * Parameters for [copyMessages][com.github.ayastrebov.telegram.Bot.copyMessages].
 */
@Serializable
public data class CopyMessagesRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    @SerialName("from_chat_id")
    val fromChatId: String,

    @SerialName("message_ids")
    val messageIds: List<Long>,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("remove_caption")
    val removeCaption: Boolean? = null,
)

/**
 * Parameters for [deleteMessages][com.github.ayastrebov.telegram.Bot.deleteMessages].
 */
@Serializable
public data class DeleteMessagesRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_ids")
    val messageIds: List<Long>,
)

/**
 * Parameters for [sendMediaGroup][com.github.ayastrebov.telegram.Bot.sendMediaGroup].
 */
@Serializable
public data class SendMediaGroupRequest(
    @SerialName("chat_id")
    val chatId: String,

    val media: List<InputMedia>,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)

/**
 * Parameters for [getFile][com.github.ayastrebov.telegram.Bot.getFile].
 */
@Serializable
public data class GetFileRequest(
    @SerialName("file_id")
    val fileId: String,
)

/**
 * Parameters for [sendChatAction][com.github.ayastrebov.telegram.Bot.sendChatAction].
 *
 * @property action Type of action: "typing", "upload_photo", "record_video", "upload_video",
 *   "record_voice", "upload_voice", "upload_document", "find_location", "record_video_note",
 *   "upload_video_note".
 */
@Serializable
public data class SendChatActionRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    val action: ChatAction,

    /** Unique identifier of the business connection on behalf of which the action will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,
)

/**
 * Parameters for [sendMessageDraft][com.github.ayastrebov.telegram.Bot.sendMessageDraft].
 */
@Serializable
public data class SendMessageDraftRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    @SerialName("draft_id")
    val draftId: Int,

    val text: String,

    @SerialName("parse_mode")
    val parseMode: ParseMode? = null,

    val entities: List<MessageEntity>? = null,

    /** Pass True to show the user a button to stop further drafts. */
    @SerialName("can_stop")
    val canStop: Boolean? = null,

    /** Pass True to keep the draft in the chat when the button is pressed. */
    @SerialName("keep_on_stop")
    val keepOnStop: Boolean? = null,
)

/**
 * Parameters for [sendVenue][com.github.ayastrebov.telegram.Bot.sendVenue].
 */
// Not yet modelled: suggested_post_parameters
@Serializable
public data class SendVenueRequest(
    /** Unique identifier for the target chat or username of the target bot, supergroup or channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Latitude of the venue. */
    val latitude: Double,

    /** Longitude of the venue. */
    val longitude: Double,

    /** Name of the venue. */
    val title: String,

    /** Address of the venue. */
    val address: String,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Unique identifier for the target message thread (topic) of a forum; for forum supergroups and private chats of bots with forum topic mode enabled only. */
    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    /** Identifier of the direct messages topic to which the message will be sent; required if the message is sent to a direct messages chat. */
    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    /** A JSON-serialized object containing the parameters of the ephemeral message to send. */
    @SerialName("ephemeral_message_parameters")
    val ephemeralMessageParameters: EphemeralMessageParameters? = null,

    /** Foursquare identifier of the venue. */
    @SerialName("foursquare_id")
    val foursquareId: String? = null,

    /** Foursquare type of the venue, if known. */
    @SerialName("foursquare_type")
    val foursquareType: String? = null,

    /** Google Places identifier of the venue. */
    @SerialName("google_place_id")
    val googlePlaceId: String? = null,

    /** Google Places type of the venue. */
    @SerialName("google_place_type")
    val googlePlaceType: String? = null,

    /** Sends the message silently. */
    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    /** Protects the contents of the sent message from forwarding and saving. */
    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,

    /** Description of the message to reply to. */
    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    /** Additional interface options. */
    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)
