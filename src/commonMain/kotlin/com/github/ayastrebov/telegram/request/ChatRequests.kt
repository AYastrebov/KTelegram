package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.ChatPermissions
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

    /** Pass True to additionally receive all bots that are administrators of the chat. */
    @SerialName("return_bots")
    val returnBots: Boolean? = null,
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

    /** Unique identifier of the business connection on behalf of which the message will be pinned. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,
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

    /** Unique identifier of the business connection on behalf of which the message will be unpinned. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,
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

    /** Pass True if the administrator can manage chat welcome messages or directly send them in the case of bots. */
    @SerialName("can_send_welcome_messages")
    val canSendWelcomeMessages: Boolean? = null,
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

/**
 * Parameters for [restrictChatMember][com.github.ayastrebov.telegram.Bot.restrictChatMember].
 */
@Serializable
public data class RestrictChatMemberRequest(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("user_id")
    val userId: Long,

    val permissions: ChatPermissions,

    @SerialName("use_independent_chat_permissions")
    val useIndependentChatPermissions: Boolean? = null,

    @SerialName("until_date")
    val untilDate: Long? = null,
)

/**
 * Parameters for [exportChatInviteLink][com.github.ayastrebov.telegram.Bot.exportChatInviteLink].
 */
@Serializable
public data class ExportChatInviteLinkRequest(
    @SerialName("chat_id")
    val chatId: String,
)

/**
 * Parameters for [createChatInviteLink][com.github.ayastrebov.telegram.Bot.createChatInviteLink].
 */
@Serializable
public data class CreateChatInviteLinkRequest(
    @SerialName("chat_id")
    val chatId: String,

    val name: String? = null,

    @SerialName("expire_date")
    val expireDate: Long? = null,

    @SerialName("member_limit")
    val memberLimit: Int? = null,

    @SerialName("creates_join_request")
    val createsJoinRequest: Boolean? = null,
)

/**
 * Parameters for [approveChatJoinRequest][com.github.ayastrebov.telegram.Bot.approveChatJoinRequest].
 */
@Serializable
public data class ApproveChatJoinRequestParams(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("user_id")
    val userId: Long,
)

/**
 * Parameters for [declineChatJoinRequest][com.github.ayastrebov.telegram.Bot.declineChatJoinRequest].
 */
@Serializable
public data class DeclineChatJoinRequestParams(
    @SerialName("chat_id")
    val chatId: String,

    @SerialName("user_id")
    val userId: Long,
)

/**
 * Parameters for [createForumTopic][com.github.ayastrebov.telegram.Bot.createForumTopic].
 */
@Serializable
public data class CreateForumTopicRequest(
    @SerialName("chat_id")
    val chatId: String,

    val name: String,

    @SerialName("icon_color")
    val iconColor: Int? = null,

    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,
)

/**
 * Parameters for [setChatPermissions][com.github.ayastrebov.telegram.Bot.setChatPermissions].
 */
@Serializable
public data class SetChatPermissionsRequest(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** A JSON-serialized object for new default chat permissions. */
    val permissions: ChatPermissions,

    /** Pass True if chat permissions are set independently. */
    @SerialName("use_independent_chat_permissions")
    val useIndependentChatPermissions: Boolean? = null,
)

/**
 * Parameters for [editChatInviteLink][com.github.ayastrebov.telegram.Bot.editChatInviteLink].
 */
@Serializable
public data class EditChatInviteLinkRequest(
    /** Unique identifier for the target chat or username of the target channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** The invite link to edit. */
    @SerialName("invite_link")
    val inviteLink: String,

    /** Invite link name; 0-32 characters. */
    val name: String? = null,

    /** Point in time (Unix timestamp) when the link will expire. */
    @SerialName("expire_date")
    val expireDate: Long? = null,

    /** The maximum number of users that can be members of the chat simultaneously after joining the chat via this invite link; 1-99999. */
    @SerialName("member_limit")
    val memberLimit: Int? = null,

    /** True, if users joining the chat via the link need to be approved by chat administrators. */
    @SerialName("creates_join_request")
    val createsJoinRequest: Boolean? = null,
)

/**
 * Parameters for [revokeChatInviteLink][com.github.ayastrebov.telegram.Bot.revokeChatInviteLink].
 */
@Serializable
public data class RevokeChatInviteLinkRequest(
    /** Unique identifier of the target chat or username of the target channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** The invite link to revoke. */
    @SerialName("invite_link")
    val inviteLink: String,
)

/**
 * Parameters for [unpinAllChatMessages][com.github.ayastrebov.telegram.Bot.unpinAllChatMessages].
 */
@Serializable
public data class UnpinAllChatMessagesRequest(
    /** Unique identifier for the target chat or username of the target channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,
)

/**
 * Parameters for [deleteChatPhoto][com.github.ayastrebov.telegram.Bot.deleteChatPhoto].
 */
@Serializable
public data class DeleteChatPhotoRequest(
    /** Unique identifier for the target chat or username of the target channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,
)

/**
 * Parameters for [setChatAdministratorCustomTitle][com.github.ayastrebov.telegram.Bot.setChatAdministratorCustomTitle].
 */
@Serializable
public data class SetChatAdministratorCustomTitleRequest(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier of the target user. */
    @SerialName("user_id")
    val userId: Long,

    /** New custom title for the administrator; 0-16 characters, emoji are not allowed. */
    @SerialName("custom_title")
    val customTitle: String,
)

/**
 * Parameters for [banChatSenderChat][com.github.ayastrebov.telegram.Bot.banChatSenderChat].
 */
@Serializable
public data class BanChatSenderChatRequest(
    /** Unique identifier for the target chat or username of the target channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier of the target sender chat. */
    @SerialName("sender_chat_id")
    val senderChatId: Long,
)

/**
 * Parameters for [unbanChatSenderChat][com.github.ayastrebov.telegram.Bot.unbanChatSenderChat].
 */
@Serializable
public data class UnbanChatSenderChatRequest(
    /** Unique identifier for the target chat or username of the target channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier of the target sender chat. */
    @SerialName("sender_chat_id")
    val senderChatId: Long,
)

/**
 * Parameters for [editForumTopic][com.github.ayastrebov.telegram.Bot.editForumTopic].
 */
@Serializable
public data class EditForumTopicRequest(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier for the target message thread of the forum topic. */
    @SerialName("message_thread_id")
    val messageThreadId: Int,

    /** New topic name, 0-128 characters. */
    val name: String? = null,

    /** New unique identifier of the custom emoji shown as the topic icon. */
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,
)

/**
 * Parameters for [closeForumTopic][com.github.ayastrebov.telegram.Bot.closeForumTopic].
 */
@Serializable
public data class CloseForumTopicRequest(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier for the target message thread of the forum topic. */
    @SerialName("message_thread_id")
    val messageThreadId: Int,
)

/**
 * Parameters for [reopenForumTopic][com.github.ayastrebov.telegram.Bot.reopenForumTopic].
 */
@Serializable
public data class ReopenForumTopicRequest(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier for the target message thread of the forum topic. */
    @SerialName("message_thread_id")
    val messageThreadId: Int,
)

/**
 * Parameters for [deleteForumTopic][com.github.ayastrebov.telegram.Bot.deleteForumTopic].
 */
@Serializable
public data class DeleteForumTopicRequest(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier for the target message thread of the forum topic. */
    @SerialName("message_thread_id")
    val messageThreadId: Int,
)

/**
 * Parameters for [unpinAllForumTopicMessages][com.github.ayastrebov.telegram.Bot.unpinAllForumTopicMessages].
 */
@Serializable
public data class UnpinAllForumTopicMessagesRequest(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier for the target message thread of the forum topic. */
    @SerialName("message_thread_id")
    val messageThreadId: Int,
)

/**
 * Parameters for [getUserProfilePhotos][com.github.ayastrebov.telegram.Bot.getUserProfilePhotos].
 */
@Serializable
public data class GetUserProfilePhotosRequest(
    /** Unique identifier of the target user. */
    @SerialName("user_id")
    val userId: Long,

    /** Sequential number of the first photo to be returned. */
    val offset: Int? = null,

    /** Limits the number of photos to be retrieved. */
    val limit: Int? = null,
)

/**
 * Parameters for [getUserChatBoosts][com.github.ayastrebov.telegram.Bot.getUserChatBoosts].
 */
@Serializable
public data class GetUserChatBoostsRequest(
    /** Unique identifier for the chat or username of the channel in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier of the target user. */
    @SerialName("user_id")
    val userId: Long,
)

/**
 * Parameters for [getBusinessConnection][com.github.ayastrebov.telegram.Bot.getBusinessConnection].
 */
@Serializable
public data class GetBusinessConnectionRequest(
    /** Unique identifier of the business connection. */
    @SerialName("business_connection_id")
    val businessConnectionId: String,
)
