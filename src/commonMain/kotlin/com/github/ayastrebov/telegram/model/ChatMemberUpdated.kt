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
 * @property viaJoinRequest True if the user joined after a direct join request approved by an administrator.
 * @property viaChatFolderInviteLink True if the user joined via a chat folder invite link.
 */
@OptIn(ExperimentalTime::class)
@Serializable
public data class ChatMemberUpdated(
    val chat: Chat,
    val from: User,

    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    @SerialName("old_chat_member")
    val oldChatMember: ChatMember,

    @SerialName("new_chat_member")
    val newChatMember: ChatMember,

    @SerialName("invite_link")
    val inviteLink: ChatInviteLink? = null,

    @SerialName("via_join_request")
    val viaJoinRequest: Boolean? = null,

    @SerialName("via_chat_folder_invite_link")
    val viaChatFolderInviteLink: Boolean? = null,
)
