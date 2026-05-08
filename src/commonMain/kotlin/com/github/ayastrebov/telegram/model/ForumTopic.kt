package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
)

/** Represents a chat invite link. */
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
    val expireDate: Long? = null,

    @SerialName("member_limit")
    val memberLimit: Int? = null,

    @SerialName("pending_join_request_count")
    val pendingJoinRequestCount: Int? = null,
)
