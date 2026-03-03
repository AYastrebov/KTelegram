package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a Telegram chat (private, group, supergroup, or channel).
 */
@Serializable
data class Chat(
    val id: Long,

    @SerialName("first_name")
    val firstName: String? = null,

    @SerialName("last_name")
    val lastName: String? = null,

    val username: String? = null,
    val type: String,
    val title: String? = null,
    val bio: String? = null,
    val description: String? = null,

    @SerialName("is_forum")
    val isForum: Boolean? = null,

    val photo: ChatPhoto? = null,
    val permissions: ChatPermissions? = null,

    @SerialName("invite_link")
    val inviteLink: String? = null,

    @SerialName("pinned_message")
    val pinnedMessage: Message? = null,

    @SerialName("slow_mode_delay")
    val slowModeDelay: Int? = null,

    @SerialName("message_auto_delete_time")
    val messageAutoDeleteTime: Int? = null,

    @SerialName("linked_chat_id")
    val linkedChatId: Long? = null,

    @SerialName("is_direct_messages")
    val isDirectMessages: Boolean? = null,

    @SerialName("parent_chat")
    val parentChat: Chat? = null,
)

/** True if this chat is a group or supergroup. */
val Chat.isGroup: Boolean
    get() = when (type) {
        "supergroup", "group" -> true
        else -> false
    }

/** True if this chat is a private (one-on-one) chat. */
val Chat.isPrivate: Boolean
    get() = when (type) {
        "private" -> true
        else -> false
    }
