package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Represents a message in Telegram.
 *
 * This is the central type in the Telegram Bot API — most updates contain a message.
 */
@OptIn(ExperimentalTime::class)
@Serializable
data class Message(
    @SerialName("message_id")
    val messageId: Long,

    val from: User? = null,

    @SerialName("sender_chat")
    val senderChat: Chat? = null,

    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    val chat: Chat,

    @SerialName("forward_from")
    val forwardFrom: User? = null,

    @SerialName("forward_from_chat")
    val forwardFromChat: Chat? = null,

    @SerialName("forward_from_message_id")
    val forwardFromMessageId: Long? = null,

    @SerialName("forward_signature")
    val forwardSignature: String? = null,

    @SerialName("forward_sender_name")
    val forwardSenderName: String? = null,

    @SerialName("forward_date")
    @Serializable(with = InstantUnixSerializer::class)
    val forwardDate: kotlin.time.Instant? = null,

    @SerialName("is_automatic_forward")
    val isAutomaticForward: Boolean? = null,

    @SerialName("reply_to_message")
    val replyToMessage: Message? = null,

    @SerialName("via_bot")
    val viaBot: User? = null,

    @SerialName("edit_date")
    @Serializable(with = InstantUnixSerializer::class)
    val editDate: kotlin.time.Instant? = null,

    @SerialName("has_protected_content")
    val hasProtectedContent: Boolean? = null,

    @SerialName("media_group_id")
    val mediaGroupId: String? = null,

    @SerialName("author_signature")
    val authorSignature: String? = null,

    val text: String? = null,
    val entities: List<MessageEntity>? = null,
    val audio: Audio? = null,
    val document: Document? = null,
    val photo: List<PhotoSize>? = null,
    val sticker: Sticker? = null,
    val voice: Voice? = null,
    val video: Video? = null,
    val animation: Animation? = null,
    val location: Location? = null,
    val contact: Contact? = null,
    val dice: Dice? = null,

    @SerialName("video_note")
    val videoNote: VideoNote? = null,

    val caption: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @SerialName("new_chat_members")
    val newChatMembers: List<User>? = null,

    @SerialName("left_chat_member")
    val leftChatMember: User? = null,

    @SerialName("pinned_message")
    val pinnedMessage: Message? = null,

    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,
)

/** The first bot_command entity in this message, if any. */
val Message.commandEntity
    get() = entities?.firstOrNull {
        it.type == "bot_command"
    }

/** The command text (without leading `/`), if this message contains a bot command. */
val Message.commandText
    get() =
        commandEntity?.let {
            text?.subSequence(it.offset, it.offset + it.length)
                ?.removePrefix("/")
        }
