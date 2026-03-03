package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a member of a chat with their status and permissions.
 *
 * @property user Information about the user.
 * @property status The member's status: "creator", "administrator", "member", "restricted", "left", or "kicked".
 * @property customTitle Custom title for this user (owner and administrators only).
 * @property isAnonymous True if the user's presence in the chat is hidden.
 * @property canBeEdited True if the bot can change the member's administrative privileges.
 * @property canManageChat True if the administrator can access chat event log, statistics, etc.
 * @property canDeleteMessages True if the administrator can delete messages of other users.
 * @property canManageVideoChats True if the administrator can manage video chats.
 * @property canRestrictMembers True if the administrator can restrict, ban or unban chat members.
 * @property canPromoteMembers True if the administrator can promote members.
 * @property canChangeInfo True if the user can change the chat title, photo and other settings.
 * @property canInviteUsers True if the user can invite new users to the chat.
 * @property canPostMessages True if the administrator can post messages in the channel (channels only).
 * @property canEditMessages True if the administrator can edit messages of other users (channels only).
 * @property canPinMessages True if the user can pin messages (supergroups only).
 * @property canSendMessages True if the user can send text messages, contacts, locations and venues.
 * @property canSendMediaMessages True if the user can send audios, documents, photos, videos, video notes and voice notes.
 * @property canSendPolls True if the user can send polls.
 * @property canSendOtherMessages True if the user can send animations, games, stickers and use inline bots.
 * @property canAddWebPagePreviews True if the user can add web page previews to their messages.
 * @property untilDate Date when restrictions will be lifted for this user (Unix timestamp). 0 means forever.
 */
@Serializable
data class ChatMember(
    val user: User,
    val status: String,

    @SerialName("custom_title")
    val customTitle: String? = null,

    @SerialName("is_anonymous")
    val isAnonymous: Boolean? = null,

    @SerialName("can_be_edited")
    val canBeEdited: Boolean? = null,

    @SerialName("can_manage_chat")
    val canManageChat: Boolean? = null,

    @SerialName("can_delete_messages")
    val canDeleteMessages: Boolean? = null,

    @SerialName("can_manage_video_chats")
    val canManageVideoChats: Boolean? = null,

    @SerialName("can_restrict_members")
    val canRestrictMembers: Boolean? = null,

    @SerialName("can_promote_members")
    val canPromoteMembers: Boolean? = null,

    @SerialName("can_change_info")
    val canChangeInfo: Boolean? = null,

    @SerialName("can_invite_users")
    val canInviteUsers: Boolean? = null,

    @SerialName("can_post_messages")
    val canPostMessages: Boolean? = null,

    @SerialName("can_edit_messages")
    val canEditMessages: Boolean? = null,

    @SerialName("can_pin_messages")
    val canPinMessages: Boolean? = null,

    @SerialName("can_send_messages")
    val canSendMessages: Boolean? = null,

    @SerialName("can_send_media_messages")
    val canSendMediaMessages: Boolean? = null,

    @SerialName("can_send_polls")
    val canSendPolls: Boolean? = null,

    @SerialName("can_send_other_messages")
    val canSendOtherMessages: Boolean? = null,

    @SerialName("can_add_web_page_previews")
    val canAddWebPagePreviews: Boolean? = null,

    @SerialName("until_date")
    val untilDate: Long? = null,

    val tag: String? = null,

    @SerialName("can_edit_tag")
    val canEditTag: Boolean? = null,

    @SerialName("can_manage_tags")
    val canManageTags: Boolean? = null,

    @SerialName("can_manage_direct_messages")
    val canManageDirectMessages: Boolean? = null,
)
