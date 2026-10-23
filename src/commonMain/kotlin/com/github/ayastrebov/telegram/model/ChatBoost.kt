package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import kotlin.time.ExperimentalTime

/** This object describes the source of a chat boost. It can be one of */
@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("source")
public sealed class ChatBoostSource

/** The boost was obtained by subscribing to Telegram Premium or by gifting a Telegram Premium subscription to another user. */
@Serializable
@SerialName("premium")
public data class ChatBoostSourcePremium(
    /** User that boosted the chat. */
    val user: User,
) : ChatBoostSource()

/** The boost was obtained by the creation of Telegram Premium gift codes to boost a chat. Each such code boosts the chat 4 times for the duration of the corresponding Telegram Premium subscription. */
@Serializable
@SerialName("gift_code")
public data class ChatBoostSourceGiftCode(
    /** User for which the gift code was created. */
    val user: User,
) : ChatBoostSource()

/** The boost was obtained by the creation of a Telegram Premium or a Telegram Star giveaway. This boosts the chat 4 times for the duration of the corresponding Telegram Premium subscription for Telegram Premium giveaways and prize_star_count / 500 times for one year for Telegram Star giveaways. */
@Serializable
@SerialName("giveaway")
public data class ChatBoostSourceGiveaway(
    /** Identifier of a message in the chat with the giveaway; the message could have been deleted already. */
    @SerialName("giveaway_message_id")
    val giveawayMessageId: Long,

    /** User that won the prize in the giveaway if any; for Telegram Premium giveaways only. */
    val user: User? = null,

    /** The number of Telegram Stars to be split between giveaway winners; for Telegram Star giveaways only. */
    @SerialName("prize_star_count")
    val prizeStarCount: Int? = null,

    /** True, if the giveaway was completed, but there was no user to win the prize. */
    @SerialName("is_unclaimed")
    val isUnclaimed: Boolean? = null,
) : ChatBoostSource()

/** This object contains information about a chat boost. */
@OptIn(ExperimentalTime::class)
@Serializable
public data class ChatBoost(
    /** Unique identifier of the boost. */
    @SerialName("boost_id")
    val boostId: String,

    /** Point in time (Unix timestamp) when the chat was boosted. */
    @SerialName("add_date")
    @Serializable(with = InstantUnixSerializer::class)
    val addDate: kotlin.time.Instant,

    /** Point in time (Unix timestamp) when the boost will automatically expire, unless the booster's Telegram Premium subscription is prolonged. */
    @SerialName("expiration_date")
    @Serializable(with = InstantUnixSerializer::class)
    val expirationDate: kotlin.time.Instant,

    /** Source of the added boost. */
    val source: ChatBoostSource,
)

/** This object represents a boost added to a chat or changed. */
@Serializable
public data class ChatBoostUpdated(
    /** Chat which was boosted. */
    val chat: Chat,

    /** Information about the chat boost. */
    val boost: ChatBoost,
)

/** This object represents a boost removed from a chat. */
@OptIn(ExperimentalTime::class)
@Serializable
public data class ChatBoostRemoved(
    /** Chat which was boosted. */
    val chat: Chat,

    /** Unique identifier of the boost. */
    @SerialName("boost_id")
    val boostId: String,

    /** Point in time (Unix timestamp) when the boost was removed. */
    @SerialName("remove_date")
    @Serializable(with = InstantUnixSerializer::class)
    val removeDate: kotlin.time.Instant,

    /** Source of the removed boost. */
    val source: ChatBoostSource,
)

/** This object represents a service message about a user boosting a chat. */
@Serializable
public data class ChatBoostAdded(
    /** Number of boosts added by the user. */
    @SerialName("boost_count")
    val boostCount: Long,
)

/** This object represents a list of boosts added to a chat by a user. */
@Serializable
public data class UserChatBoosts(
    /** The list of boosts added to the chat by the user. */
    val boosts: List<ChatBoost>,
)
