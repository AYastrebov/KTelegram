package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/** Represents a forum topic. */
@Serializable
public data class ForumTopic(
    @SerialName("message_thread_id")
    val messageThreadId: Int,

    val name: String,

    @SerialName("icon_color")
    val iconColor: Int,

    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,

    /** True, if the name of the topic wasn't specified explicitly by its creator and likely needs to be changed by the bot. */
    @SerialName("is_name_implicit")
    val isNameImplicit: Boolean? = null,
)

/** Represents a chat invite link. */
@OptIn(ExperimentalTime::class)
@Serializable
public data class ChatInviteLink(
    @SerialName("invite_link")
    val inviteLink: String,

    val creator: User,

    @SerialName("creates_join_request")
    val createsJoinRequest: Boolean,

    @SerialName("is_primary")
    val isPrimary: Boolean,

    @SerialName("is_revoked")
    val isRevoked: Boolean,

    val name: String? = null,

    @SerialName("expire_date")
    @Serializable(with = InstantUnixSerializer::class)
    val expireDate: kotlin.time.Instant? = null,

    @SerialName("member_limit")
    val memberLimit: Int? = null,

    @SerialName("pending_join_request_count")
    val pendingJoinRequestCount: Int? = null,

    /** The number of seconds the subscription will be active for before the next payment. */
    @SerialName("subscription_period")
    val subscriptionPeriod: Int? = null,

    /** The amount of Telegram Stars a user must pay initially and after each subsequent subscription period to be a member of the chat using the link. */
    @SerialName("subscription_price")
    val subscriptionPrice: Int? = null,
)
