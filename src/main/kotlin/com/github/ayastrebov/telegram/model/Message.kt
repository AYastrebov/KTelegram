package com.github.ayastrebov.telegram.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("message_id")
    val messageId: Long,

    val from: User? = null,

    @SerialName("sender_chat")
    val senderChat: Chat? = null,

    val date: LocalDateTime,

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
    val forwardDate: LocalDateTime? = null,

    @SerialName("is_automatic_forward")
    val isAutomaticForward: Boolean? = null,

    @SerialName("reply_to_message")
    val replyToMessage: Message? = null,

    @SerialName("via_bot")
    val viaBot: User? = null,

    @SerialName("edit_date")
    val editDate: LocalDateTime? = null,

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
)

val Message.commandEntity
    get() = entities?.firstOrNull {
        it.type == "bot_command"
    }

val Message.commandText
    get() =
        commandEntity?.let {
            text?.subSequence(it.offset, it.offset + it.length)
                ?.removePrefix("/")
        }