package com.github.ayastrebov.telegram.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [getChat][com.github.ayastrebov.telegram.Bot.getChat].
 */
@Serializable
public data class GetChatRequest(
    @SerialName("chat_id")
    val chatId: String,
)

/**
 * Parameters for [getChatMemberCount][com.github.ayastrebov.telegram.Bot.getChatMemberCount].
 */
@Serializable
public data class GetChatMemberCountRequest(
    @SerialName("chat_id")
    val chatId: String,
)

/**
 * Parameters for [getChatMember][com.github.ayastrebov.telegram.Bot.getChatMember].
 */
@Serializable
public data class GetChatMemberRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("user_id")
    val userId: Long,
)

/**
 * Parameters for [getChatAdministrators][com.github.ayastrebov.telegram.Bot.getChatAdministrators].
 */
@Serializable
public data class GetChatAdministratorsRequest(
    @SerialName("chat_id")
    val chatId: String,
)

/**
 * Parameters for [banChatMember][com.github.ayastrebov.telegram.Bot.banChatMember].
 */
@Serializable
public data class BanChatMemberRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("user_id")
    val userId: Long,

    @SerialName("until_date")
    val untilDate: Long? = null,

    @SerialName("revoke_messages")
    val revokeMessages: Boolean? = null,
)

/**
 * Parameters for [unbanChatMember][com.github.ayastrebov.telegram.Bot.unbanChatMember].
 */
@Serializable
public data class UnbanChatMemberRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("user_id")
    val userId: Long,

    @SerialName("only_if_banned")
    val onlyIfBanned: Boolean? = null,
)

/**
 * Parameters for [leaveChat][com.github.ayastrebov.telegram.Bot.leaveChat].
 */
@Serializable
public data class LeaveChatRequest(
    @SerialName("chat_id")
    val chatId: String,
)

/**
 * Parameters for [setChatTitle][com.github.ayastrebov.telegram.Bot.setChatTitle].
 */
@Serializable
public data class SetChatTitleRequest(
    @SerialName("chat_id")
    val chatId: String,

    val title: String,
)

/**
 * Parameters for [setChatDescription][com.github.ayastrebov.telegram.Bot.setChatDescription].
 */
@Serializable
public data class SetChatDescriptionRequest(
    @SerialName("chat_id")
    val chatId: String,

    val description: String? = null,
)

/**
 * Parameters for [pinChatMessage][com.github.ayastrebov.telegram.Bot.pinChatMessage].
 */
@Serializable
public data class PinChatMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_id")
    val messageId: Long,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,
)

/**
 * Parameters for [unpinChatMessage][com.github.ayastrebov.telegram.Bot.unpinChatMessage].
 */
@Serializable
public data class UnpinChatMessageRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("message_id")
    val messageId: Long? = null,
)

/**
 * Parameters for [promoteChatMember][com.github.ayastrebov.telegram.Bot.promoteChatMember].
 */
@Serializable
public data class PromoteChatMemberRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("user_id")
    val userId: Long,

    @SerialName("is_anonymous")
    val isAnonymous: Boolean? = null,

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

    @SerialName("can_post_stories")
    val canPostStories: Boolean? = null,

    @SerialName("can_edit_stories")
    val canEditStories: Boolean? = null,

    @SerialName("can_delete_stories")
    val canDeleteStories: Boolean? = null,

    @SerialName("can_post_messages")
    val canPostMessages: Boolean? = null,

    @SerialName("can_edit_messages")
    val canEditMessages: Boolean? = null,

    @SerialName("can_pin_messages")
    val canPinMessages: Boolean? = null,

    @SerialName("can_manage_topics")
    val canManageTopics: Boolean? = null,

    @SerialName("can_manage_direct_messages")
    val canManageDirectMessages: Boolean? = null,

    @SerialName("can_manage_tags")
    val canManageTags: Boolean? = null,
)

/**
 * Parameters for [setChatMemberTag][com.github.ayastrebov.telegram.Bot.setChatMemberTag].
 */
@Serializable
public data class SetChatMemberTagRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("user_id")
    val userId: Long,

    val tag: String,
)
