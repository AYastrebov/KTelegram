package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/** Represents the rights of a business bot. */
@Serializable
public data class BusinessBotRights(
    /** True, if the bot can send and edit messages in the private chats that had incoming messages in the last 24 hours. */
    @SerialName("can_reply")
    val canReply: Boolean? = null,

    /** True, if the bot can mark incoming private messages as read. */
    @SerialName("can_read_messages")
    val canReadMessages: Boolean? = null,

    /** True, if the bot can delete messages sent by the bot. */
    @SerialName("can_delete_sent_messages")
    val canDeleteSentMessages: Boolean? = null,

    /** True, if the bot can delete all private messages in managed chats. */
    @SerialName("can_delete_all_messages")
    val canDeleteAllMessages: Boolean? = null,

    /** True, if the bot can edit the first and last name of the business account. */
    @SerialName("can_edit_name")
    val canEditName: Boolean? = null,

    /** True, if the bot can edit the bio of the business account. */
    @SerialName("can_edit_bio")
    val canEditBio: Boolean? = null,

    /** True, if the bot can edit the profile photo of the business account. */
    @SerialName("can_edit_profile_photo")
    val canEditProfilePhoto: Boolean? = null,

    /** True, if the bot can edit the username of the business account. */
    @SerialName("can_edit_username")
    val canEditUsername: Boolean? = null,

    /** True, if the bot can change the privacy settings pertaining to gifts for the business account. */
    @SerialName("can_change_gift_settings")
    val canChangeGiftSettings: Boolean? = null,

    /** True, if the bot can view gifts and the amount of Telegram Stars owned by the business account. */
    @SerialName("can_view_gifts_and_stars")
    val canViewGiftsAndStars: Boolean? = null,

    /** True, if the bot can convert regular gifts owned by the business account to Telegram Stars. */
    @SerialName("can_convert_gifts_to_stars")
    val canConvertGiftsToStars: Boolean? = null,

    /** True, if the bot can transfer and upgrade gifts owned by the business account. */
    @SerialName("can_transfer_and_upgrade_gifts")
    val canTransferAndUpgradeGifts: Boolean? = null,

    /** True, if the bot can transfer Telegram Stars received by the business account to its own account, or use them to upgrade and transfer gifts. */
    @SerialName("can_transfer_stars")
    val canTransferStars: Boolean? = null,

    /** True, if the bot can post, edit and delete stories on behalf of the business account. */
    @SerialName("can_manage_stories")
    val canManageStories: Boolean? = null,
)

/** Describes the connection of the bot with a business account. */
@OptIn(ExperimentalTime::class)
@Serializable
public data class BusinessConnection(
    /** Unique identifier of the business connection. */
    val id: String,

    /** Business account user that created the business connection. */
    val user: User,

    /** Identifier of a private chat with the user who created the business connection. */
    @SerialName("user_chat_id")
    val userChatId: Long,

    /** Date the connection was established in Unix time. */
    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    /** True, if the connection is active. */
    @SerialName("is_enabled")
    val isEnabled: Boolean,

    /** Rights of the business bot. */
    val rights: BusinessBotRights? = null,
)

/** This object is received when messages are deleted from a connected business account. */
@Serializable
public data class BusinessMessagesDeleted(
    /** Unique identifier of the business connection. */
    @SerialName("business_connection_id")
    val businessConnectionId: String,

    /** Information about a chat in the business account. */
    val chat: Chat,

    /** The list of identifiers of deleted messages in the chat of the business account. */
    @SerialName("message_ids")
    val messageIds: List<Int>,
)
