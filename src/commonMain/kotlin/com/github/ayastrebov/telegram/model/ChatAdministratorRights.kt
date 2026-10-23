package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents the rights of an administrator in a chat. */
@Serializable
public data class ChatAdministratorRights(
    /** True, if the user's presence in the chat is hidden. */
    @SerialName("is_anonymous")
    val isAnonymous: Boolean,

    /** True, if the administrator can access the chat event log, get boost list, see hidden supergroup and channel members, report spam messages, ignore slow mode, and send messages to the chat without paying Telegram Stars. */
    @SerialName("can_manage_chat")
    val canManageChat: Boolean,

    /** True, if the administrator can delete messages of other users. */
    @SerialName("can_delete_messages")
    val canDeleteMessages: Boolean,

    /** True, if the administrator can manage video chats. */
    @SerialName("can_manage_video_chats")
    val canManageVideoChats: Boolean,

    /** True, if the administrator can restrict, ban or unban chat members, or access supergroup statistics. */
    @SerialName("can_restrict_members")
    val canRestrictMembers: Boolean,

    /** True, if the administrator can add new administrators with a subset of their own privileges or demote administrators that they have promoted, directly or indirectly (promoted by administrators that were appointed by the user). */
    @SerialName("can_promote_members")
    val canPromoteMembers: Boolean,

    /** True, if the user is allowed to change the chat title, photo and other settings. */
    @SerialName("can_change_info")
    val canChangeInfo: Boolean,

    /** True, if the user is allowed to invite new users to the chat. */
    @SerialName("can_invite_users")
    val canInviteUsers: Boolean,

    /** True, if the administrator can post stories to the chat. */
    @SerialName("can_post_stories")
    val canPostStories: Boolean,

    /** True, if the administrator can edit stories posted by other users, post stories to the chat page, pin chat stories, and access the chat's story archive. */
    @SerialName("can_edit_stories")
    val canEditStories: Boolean,

    /** True, if the administrator can delete stories posted by other users. */
    @SerialName("can_delete_stories")
    val canDeleteStories: Boolean,

    /** True, if the administrator can manage chat welcome messages or directly send them in the case of bots. */
    @SerialName("can_send_welcome_messages")
    val canSendWelcomeMessages: Boolean,

    /** True, if the administrator can post messages in the channel, approve suggested posts, or access channel statistics; for channels only. */
    @SerialName("can_post_messages")
    val canPostMessages: Boolean? = null,

    /** True, if the administrator can edit messages of other users and can pin messages; for channels only. */
    @SerialName("can_edit_messages")
    val canEditMessages: Boolean? = null,

    /** True, if the user is allowed to pin messages; for groups and supergroups only. */
    @SerialName("can_pin_messages")
    val canPinMessages: Boolean? = null,

    /** True, if the user is allowed to create, rename, close, and reopen forum topics; for supergroups only. */
    @SerialName("can_manage_topics")
    val canManageTopics: Boolean? = null,

    /** True, if the administrator can manage direct messages of the channel and decline suggested posts; for channels only. */
    @SerialName("can_manage_direct_messages")
    val canManageDirectMessages: Boolean? = null,

    /** True, if the administrator can edit the tags of regular members; for groups and supergroups only. */
    @SerialName("can_manage_tags")
    val canManageTags: Boolean? = null,
)

/** This object defines the criteria used to request suitable users. Information about the selected users will be shared with the bot when the corresponding button is pressed. More about requesting users » */
@Serializable
public data class KeyboardButtonRequestUsers(
    /** Signed 32-bit identifier of the request that will be received back in the UsersShared object. */
    @SerialName("request_id")
    val requestId: Int,

    /** Pass True to request bots, pass False to request regular users. */
    @SerialName("user_is_bot")
    val userIsBot: Boolean? = null,

    /** Pass True to request premium users, pass False to request non-premium users. */
    @SerialName("user_is_premium")
    val userIsPremium: Boolean? = null,

    /** The maximum number of users to be selected; 1-10. */
    @SerialName("max_quantity")
    val maxQuantity: Int? = null,

    /** Pass True to request the users' first and last names. */
    @SerialName("request_name")
    val requestName: Boolean? = null,

    /** Pass True to request the users' usernames. */
    @SerialName("request_username")
    val requestUsername: Boolean? = null,

    /** Pass True to request the users' photos. */
    @SerialName("request_photo")
    val requestPhoto: Boolean? = null,
)

/** This object defines the criteria used to request a suitable chat. Information about the selected chat will be shared with the bot when the corresponding button is pressed. The bot will be granted requested rights in the chat if appropriate. More about requesting chats ». */
@Serializable
public data class KeyboardButtonRequestChat(
    /** Signed 32-bit identifier of the request, which will be received back in the ChatShared object. */
    @SerialName("request_id")
    val requestId: Int,

    /** Pass True to request a channel chat, pass False to request a group or a supergroup chat. */
    @SerialName("chat_is_channel")
    val chatIsChannel: Boolean,

    /** Pass True to request a forum supergroup, pass False to request a non-forum chat. */
    @SerialName("chat_is_forum")
    val chatIsForum: Boolean? = null,

    /** Pass True to request a supergroup or a channel with a username, pass False to request a chat without a username. */
    @SerialName("chat_has_username")
    val chatHasUsername: Boolean? = null,

    /** Pass True to request a chat owned by the user. */
    @SerialName("chat_is_created")
    val chatIsCreated: Boolean? = null,

    /** A JSON-serialized object listing the required administrator rights of the user in the chat. */
    @SerialName("user_administrator_rights")
    val userAdministratorRights: ChatAdministratorRights? = null,

    /** A JSON-serialized object listing the required administrator rights of the bot in the chat. */
    @SerialName("bot_administrator_rights")
    val botAdministratorRights: ChatAdministratorRights? = null,

    /** Pass True to request a chat with the bot as a member. */
    @SerialName("bot_is_member")
    val botIsMember: Boolean? = null,

    /** Pass True to request the chat's title. */
    @SerialName("request_title")
    val requestTitle: Boolean? = null,

    /** Pass True to request the chat's username. */
    @SerialName("request_username")
    val requestUsername: Boolean? = null,

    /** Pass True to request the chat's photo. */
    @SerialName("request_photo")
    val requestPhoto: Boolean? = null,
)

/** This object contains information about a user that was shared with the bot using a KeyboardButtonRequestUsers button. */
@Serializable
public data class SharedUser(
    /** Identifier of the shared user. */
    @SerialName("user_id")
    val userId: Long,

    /** First name of the user, if the name was requested by the bot. */
    @SerialName("first_name")
    val firstName: String? = null,

    /** Last name of the user, if the name was requested by the bot. */
    @SerialName("last_name")
    val lastName: String? = null,

    /** Username of the user, if the username was requested by the bot. */
    val username: String? = null,

    /** Available sizes of the chat photo, if the photo was requested by the bot. */
    val photo: List<PhotoSize>? = null,
)

/** This object contains information about the users whose identifiers were shared with the bot using a KeyboardButtonRequestUsers button. */
@Serializable
public data class UsersShared(
    /** Identifier of the request. */
    @SerialName("request_id")
    val requestId: Int,

    /** Information about users shared with the bot. */
    val users: List<SharedUser>,
)

/** This object contains information about a chat that was shared with the bot using a KeyboardButtonRequestChat button. */
@Serializable
public data class ChatShared(
    /** Identifier of the request. */
    @SerialName("request_id")
    val requestId: Int,

    /** Identifier of the shared chat. */
    @SerialName("chat_id")
    val chatId: Long,

    /** Title of the chat, if the title was requested by the bot. */
    val title: String? = null,

    /** Username of the chat, if the username was requested by the bot and available. */
    val username: String? = null,

    /** Available sizes of the chat photo, if the photo was requested by the bot. */
    val photo: List<PhotoSize>? = null,
)
