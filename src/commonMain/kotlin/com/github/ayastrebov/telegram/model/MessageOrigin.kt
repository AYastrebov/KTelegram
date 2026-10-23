package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/** This object describes the origin of a message. It can be one of */
@Serializable
public sealed class MessageOrigin

/** The message was originally sent by a known user. */
@OptIn(ExperimentalTime::class)
@Serializable
@SerialName("user")
public data class MessageOriginUser(
    /** Date the message was sent originally in Unix time. */
    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    /** User that sent the message originally. */
    @SerialName("sender_user")
    val senderUser: User,
) : MessageOrigin()

/** The message was originally sent by an unknown user. */
@OptIn(ExperimentalTime::class)
@Serializable
@SerialName("hidden_user")
public data class MessageOriginHiddenUser(
    /** Date the message was sent originally in Unix time. */
    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    /** Name of the user that sent the message originally. */
    @SerialName("sender_user_name")
    val senderUserName: String,
) : MessageOrigin()

/** The message was originally sent on behalf of a chat to a group chat. */
@OptIn(ExperimentalTime::class)
@Serializable
@SerialName("chat")
public data class MessageOriginChat(
    /** Date the message was sent originally in Unix time. */
    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    /** Chat that sent the message originally. */
    @SerialName("sender_chat")
    val senderChat: Chat,

    /** For messages originally sent by an anonymous chat administrator, original message author signature. */
    @SerialName("author_signature")
    val authorSignature: String? = null,
) : MessageOrigin()

/** The message was originally sent to a channel chat. */
@OptIn(ExperimentalTime::class)
@Serializable
@SerialName("channel")
public data class MessageOriginChannel(
    /** Date the message was sent originally in Unix time. */
    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    /** Channel chat to which the message was originally sent. */
    val chat: Chat,

    /** Unique message identifier inside the chat. */
    @SerialName("message_id")
    val messageId: Long,

    /** Signature of the original post author. */
    @SerialName("author_signature")
    val authorSignature: String? = null,
) : MessageOrigin()
