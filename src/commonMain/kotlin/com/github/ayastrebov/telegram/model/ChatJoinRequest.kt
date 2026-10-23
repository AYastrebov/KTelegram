package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/** Represents a join request sent to a chat. */
@OptIn(ExperimentalTime::class)
@Serializable
public data class ChatJoinRequest(
    val chat: Chat,
    val from: User,

    @SerialName("user_chat_id")
    val userChatId: Long,

    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    val bio: String? = null,

    /** Chat invite link that was used by the user to send the join request. */
    @SerialName("invite_link")
    val inviteLink: ChatInviteLink? = null,

    /** Identifier of the join request query; for bots assigned to process join requests only. */
    @SerialName("query_id")
    val queryId: String? = null,
)
