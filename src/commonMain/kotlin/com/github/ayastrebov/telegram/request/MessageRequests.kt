package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.KeyboardMarkup
import com.github.ayastrebov.telegram.model.MessageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [sendMessage][com.github.ayastrebov.telegram.Bot.sendMessage].
 */
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
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendPhoto][com.github.ayastrebov.telegram.Bot.sendPhoto].
 */
@Serializable
data class SendPhotoRequest(
    @SerialName("chat_id")
    val chatId: String,

    val photo: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendAudio][com.github.ayastrebov.telegram.Bot.sendAudio].
 */
@Serializable
data class SendAudioRequest(
    @SerialName("chat_id")
    val chatId: String,

    val audio: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    val duration: Int? = null,
    val performer: String? = null,
    val title: String? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendDocument][com.github.ayastrebov.telegram.Bot.sendDocument].
 */
@Serializable
data class SendDocumentRequest(
    @SerialName("chat_id")
    val chatId: String,

    val document: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendVideo][com.github.ayastrebov.telegram.Bot.sendVideo].
 */
@Serializable
data class SendVideoRequest(
    @SerialName("chat_id")
    val chatId: String,

    val video: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    val duration: Int? = null,
    val width: Int? = null,
    val height: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendAnimation][com.github.ayastrebov.telegram.Bot.sendAnimation].
 */
@Serializable
data class SendAnimationRequest(
    @SerialName("chat_id")
    val chatId: String,

    val animation: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    val duration: Int? = null,
    val width: Int? = null,
    val height: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendVoice][com.github.ayastrebov.telegram.Bot.sendVoice].
 */
@Serializable
data class SendVoiceRequest(
    @SerialName("chat_id")
    val chatId: String,

    val voice: String,
    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    val duration: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendVideoNote][com.github.ayastrebov.telegram.Bot.sendVideoNote].
 */
@Serializable
data class SendVideoNoteRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("video_note")
    val videoNote: String,

    val duration: Int? = null,
    val length: Int? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendLocation][com.github.ayastrebov.telegram.Bot.sendLocation].
 */
@Serializable
data class SendLocationRequest(
    @SerialName("chat_id")
    val chatId: String,

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

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendContact][com.github.ayastrebov.telegram.Bot.sendContact].
 */
@Serializable
data class SendContactRequest(
    @SerialName("chat_id")
    val chatId: String,

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

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendDice][com.github.ayastrebov.telegram.Bot.sendDice].
 */
@Serializable
data class SendDiceRequest(
    @SerialName("chat_id")
    val chatId: String,

    val emoji: String? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendSticker][com.github.ayastrebov.telegram.Bot.sendSticker].
 */
@Serializable
data class SendStickerRequest(
    @SerialName("chat_id")
    val chatId: String,

    val sticker: String,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [forwardMessage][com.github.ayastrebov.telegram.Bot.forwardMessage].
 */
@Serializable
data class ForwardMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("from_chat_id")
    val fromChatId: String,

    @SerialName("message_id")
    val messageId: Long,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,
)

/**
 * Parameters for [copyMessage][com.github.ayastrebov.telegram.Bot.copyMessage].
 */
@Serializable
data class CopyMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("from_chat_id")
    val fromChatId: String,

    @SerialName("message_id")
    val messageId: Long,

    val caption: String? = null,

    @SerialName("parse_mode")
    val parseMode: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_to_message_id")
    val replyToMessageId: Long? = null,

    @SerialName("allow_sending_without_reply")
    val allowSendingWithoutReply: Boolean? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)

/**
 * Parameters for [sendChatAction][com.github.ayastrebov.telegram.Bot.sendChatAction].
 *
 * @property action Type of action: "typing", "upload_photo", "record_video", "upload_video",
 *   "record_voice", "upload_voice", "upload_document", "find_location", "record_video_note",
 *   "upload_video_note".
 */
@Serializable
data class SendChatActionRequest(
    @SerialName("chat_id")
    val chatId: String,

    val action: String,
)
