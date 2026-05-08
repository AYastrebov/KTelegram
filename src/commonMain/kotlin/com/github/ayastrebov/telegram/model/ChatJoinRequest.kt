package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents a join request sent to a chat. */
@Serializable
public data class ChatJoinRequest(
    val chat: Chat,
    val from: User,

    @SerialName("user_chat_id")
    val userChatId: Long,

    val date: Long,

    val bio: String? = null,
)
