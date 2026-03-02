package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Represents changes in the status of a chat member.
 *
 * @property chat Chat the user belongs to.
 * @property from Performer of the action which resulted in the change.
 * @property date Date the change was done (Unix timestamp).
 * @property oldChatMember Previous information about the chat member.
 * @property newChatMember New information about the chat member.
 * @property inviteLink Chat invite link which was used by the user to join the chat (optional).
 */
@Serializable
data class ChatMemberUpdated @OptIn(ExperimentalTime::class) constructor(
    val chat: Chat,
    val from: User,

    @Serializable(with = InstantUnixSerializer::class)
    val date: @Suppress("OPT_IN_USAGE") kotlin.time.Instant,

    @SerialName("old_chat_member")
    val oldChatMember: ChatMember,

    @SerialName("new_chat_member")
    val newChatMember: ChatMember,
)
