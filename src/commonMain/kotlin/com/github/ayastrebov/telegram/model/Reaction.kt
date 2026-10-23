package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/** This object describes the type of a reaction. Currently, it can be one of */
@Serializable
public sealed class ReactionType

/** The reaction is based on an emoji. */
@Serializable
@SerialName("emoji")
public data class ReactionTypeEmoji(
    /** Reaction emoji. */
    val emoji: String,
) : ReactionType()

/** The reaction is based on a custom emoji. */
@Serializable
@SerialName("custom_emoji")
public data class ReactionTypeCustomEmoji(
    /** Custom emoji identifier. */
    @SerialName("custom_emoji_id")
    val customEmojiId: String,
) : ReactionType()

/** The reaction is paid. */
@Serializable
@SerialName("paid")
public class ReactionTypePaid : ReactionType()

/** Represents a reaction added to a message along with the number of times it was added. */
@Serializable
public data class ReactionCount(
    /** Type of the reaction. */
    val type: ReactionType,

    /** Number of times the reaction was added. */
    @SerialName("total_count")
    val totalCount: Int,
)

/** This object represents a change of a reaction on a message performed by a user. */
@OptIn(ExperimentalTime::class)
@Serializable
public data class MessageReactionUpdated(
    /** The chat containing the message the user reacted to. */
    val chat: Chat,

    /** Unique identifier of the message inside the chat. */
    @SerialName("message_id")
    val messageId: Long,

    /** Date of the change in Unix time. */
    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    /** Previous list of reaction types that were set by the user. */
    @SerialName("old_reaction")
    val oldReaction: List<ReactionType>,

    /** New list of reaction types that have been set by the user. */
    @SerialName("new_reaction")
    val newReaction: List<ReactionType>,

    /** The user that changed the reaction, if the user isn't anonymous. */
    val user: User? = null,

    /** The chat on behalf of which the reaction was changed, if the user is anonymous. */
    @SerialName("actor_chat")
    val actorChat: Chat? = null,
)

/** This object represents reaction changes on a message with anonymous reactions. */
@OptIn(ExperimentalTime::class)
@Serializable
public data class MessageReactionCountUpdated(
    /** The chat containing the message. */
    val chat: Chat,

    /** Unique message identifier inside the chat. */
    @SerialName("message_id")
    val messageId: Long,

    /** Date of the change in Unix time. */
    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    /** List of reactions that are present on the message. */
    val reactions: List<ReactionCount>,
)
