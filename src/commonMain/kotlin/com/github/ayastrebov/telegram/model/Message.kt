package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Represents a message in Telegram.
 *
 * This is the central type in the Telegram Bot API — most updates contain a message.
 */
@OptIn(ExperimentalTime::class)
@Serializable
public data class Message(
    @SerialName("message_id")
    val messageId: Long,

    val from: User? = null,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("sender_chat")
    val senderChat: Chat? = null,

    @Serializable(with = InstantUnixSerializer::class)
    val date: kotlin.time.Instant,

    val chat: Chat,

    @Deprecated("Replaced by forwardOrigin in Bot API 7.0", ReplaceWith("forwardOrigin"))
    @SerialName("forward_from")
    val forwardFrom: User? = null,

    @Deprecated("Replaced by forwardOrigin in Bot API 7.0", ReplaceWith("forwardOrigin"))
    @SerialName("forward_from_chat")
    val forwardFromChat: Chat? = null,

    @Deprecated("Replaced by forwardOrigin in Bot API 7.0", ReplaceWith("forwardOrigin"))
    @SerialName("forward_from_message_id")
    val forwardFromMessageId: Long? = null,

    @Deprecated("Replaced by forwardOrigin in Bot API 7.0", ReplaceWith("forwardOrigin"))
    @SerialName("forward_signature")
    val forwardSignature: String? = null,

    @Deprecated("Replaced by forwardOrigin in Bot API 7.0", ReplaceWith("forwardOrigin"))
    @SerialName("forward_sender_name")
    val forwardSenderName: String? = null,

    @Deprecated("Replaced by forwardOrigin in Bot API 7.0", ReplaceWith("forwardOrigin"))
    @SerialName("forward_date")
    @Serializable(with = InstantUnixSerializer::class)
    val forwardDate: kotlin.time.Instant? = null,

    @SerialName("is_topic_message")
    val isTopicMessage: Boolean? = null,

    @SerialName("is_automatic_forward")
    val isAutomaticForward: Boolean? = null,

    @SerialName("reply_to_message")
    val replyToMessage: Message? = null,

    @SerialName("via_bot")
    val viaBot: User? = null,

    @SerialName("edit_date")
    @Serializable(with = InstantUnixSerializer::class)
    val editDate: kotlin.time.Instant? = null,

    @SerialName("has_protected_content")
    val hasProtectedContent: Boolean? = null,

    @SerialName("media_group_id")
    val mediaGroupId: String? = null,

    @SerialName("author_signature")
    val authorSignature: String? = null,

    @SerialName("sender_tag")
    val senderTag: String? = null,

    val text: String? = null,
    val entities: List<MessageEntity>? = null,
    val audio: Audio? = null,
    val document: Document? = null,
    val photo: List<PhotoSize>? = null,
    val sticker: Sticker? = null,
    val voice: Voice? = null,
    val video: Video? = null,
    val animation: Animation? = null,
    val location: Location? = null,
    val contact: Contact? = null,
    val dice: Dice? = null,

    @SerialName("video_note")
    val videoNote: VideoNote? = null,

    val caption: String? = null,

    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    @SerialName("new_chat_members")
    val newChatMembers: List<User>? = null,

    @SerialName("left_chat_member")
    val leftChatMember: User? = null,

    @SerialName("pinned_message")
    val pinnedMessage: Message? = null,

    @SerialName("reply_markup")
    val replyMarkup: InlineKeyboardMarkup? = null,

    val poll: Poll? = null,

    @SerialName("chat_owner_left")
    val chatOwnerLeft: ChatOwnerLeft? = null,

    @SerialName("chat_owner_changed")
    val chatOwnerChanged: ChatOwnerChanged? = null,

    @SerialName("managed_bot_created")
    val managedBotCreated: ManagedBotCreated? = null,

    @SerialName("poll_option_added")
    val pollOptionAdded: PollOptionAdded? = null,

    @SerialName("poll_option_deleted")
    val pollOptionDeleted: PollOptionDeleted? = null,

    @SerialName("reply_to_poll_option_id")
    val replyToPollOptionId: String? = null,

    @SerialName("is_paid_post")
    val isPaidPost: Boolean? = null,

    @SerialName("paid_star_count")
    val paidStarCount: Int? = null,

    /** Information about the direct messages chat topic that contains the message. */
    @SerialName("direct_messages_topic")
    val directMessagesTopic: DirectMessagesTopic? = null,

    /** If the sender of the message boosted the chat, the number of boosts added by the user. */
    @SerialName("sender_boost_count")
    val senderBoostCount: Int? = null,

    /** The bot that actually sent the message on behalf of the business account. */
    @SerialName("sender_business_bot")
    val senderBusinessBot: User? = null,

    /** For ephemeral messages, the user who received the message. */
    @SerialName("receiver_user")
    val receiverUser: User? = null,

    /** For ephemeral messages, identifier of the ephemeral message inside this chat. */
    @SerialName("ephemeral_message_id")
    val ephemeralMessageId: Long? = null,

    /** The unique identifier for the guest query. */
    @SerialName("guest_query_id")
    val guestQueryId: String? = null,

    /** Unique identifier of the business connection from which the message was received. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Information about the original message for forwarded messages. */
    @SerialName("forward_origin")
    val forwardOrigin: MessageOrigin? = null,

    /** Information about the message that is being replied to, which may come from another chat or forum topic. */
    @SerialName("external_reply")
    val externalReply: ExternalReplyInfo? = null,

    /** For replies that quote part of the original message, the quoted part of the message. */
    val quote: TextQuote? = null,

    /** For replies to a story, the original story. */
    @SerialName("reply_to_story")
    val replyToStory: Story? = null,

    /** Identifier of the specific checklist task that is being replied to. */
    @SerialName("reply_to_checklist_task_id")
    val replyToChecklistTaskId: Int? = null,

    /** For a message sent by a guest bot, this is the user whose original message triggered the bot's response. */
    @SerialName("guest_bot_caller_user")
    val guestBotCallerUser: User? = null,

    /** For a message sent by a guest bot, this is the chat whose original message triggered the bot's response. */
    @SerialName("guest_bot_caller_chat")
    val guestBotCallerChat: Chat? = null,

    /** True, if the message was sent by an implicit action, for example, as an away or a greeting business message, or as a scheduled message. */
    @SerialName("is_from_offline")
    val isFromOffline: Boolean? = null,

    /** Options used for link preview generation for the message, if it is a text message and link preview options were changed. */
    @SerialName("link_preview_options")
    val linkPreviewOptions: LinkPreviewOptions? = null,

    /** Unique identifier of the message effect added to the message. */
    @SerialName("effect_id")
    val effectId: String? = null,

    /** Message is a live photo, information about the live photo. */
    @SerialName("live_photo")
    val livePhoto: LivePhoto? = null,

    /** Message is a forwarded story. */
    val story: Story? = null,

    /** True, if the caption must be shown above the message media. */
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,

    /** True, if the message media is covered by a spoiler animation. */
    @SerialName("has_media_spoiler")
    val hasMediaSpoiler: Boolean? = null,

    /** Message is a venue, information about the venue. */
    val venue: Venue? = null,

    /** A chat title was changed to this value. */
    @SerialName("new_chat_title")
    val newChatTitle: String? = null,

    /** A chat photo was change to this value. */
    @SerialName("new_chat_photo")
    val newChatPhoto: List<PhotoSize>? = null,

    /** Service message: the chat photo was deleted. */
    @SerialName("delete_chat_photo")
    val deleteChatPhoto: Boolean? = null,

    /** Service message: the group has been created. */
    @SerialName("group_chat_created")
    val groupChatCreated: Boolean? = null,

    /** Service message: the supergroup has been created. */
    @SerialName("supergroup_chat_created")
    val supergroupChatCreated: Boolean? = null,

    /** Service message: the channel has been created. */
    @SerialName("channel_chat_created")
    val channelChatCreated: Boolean? = null,

    /** Service message: auto-delete timer settings changed in the chat. */
    @SerialName("message_auto_delete_timer_changed")
    val messageAutoDeleteTimerChanged: MessageAutoDeleteTimerChanged? = null,

    /** The group has been migrated to a supergroup with the specified identifier. */
    @SerialName("migrate_to_chat_id")
    val migrateToChatId: Long? = null,

    /** The supergroup has been migrated from a group with the specified identifier. */
    @SerialName("migrate_from_chat_id")
    val migrateFromChatId: Long? = null,

    /** Message is an invoice for a payment, information about the invoice. */
    val invoice: Invoice? = null,

    /** Message is a service message about a successful payment, information about the payment. */
    @SerialName("successful_payment")
    val successfulPayment: SuccessfulPayment? = null,

    /** Message is a service message about a refunded payment, information about the payment. */
    @SerialName("refunded_payment")
    val refundedPayment: RefundedPayment? = null,

    /** Service message: users were shared with the bot. */
    @SerialName("users_shared")
    val usersShared: UsersShared? = null,

    /** Service message: a chat was shared with the bot. */
    @SerialName("chat_shared")
    val chatShared: ChatShared? = null,

    /** Service message: the user allowed the bot to write messages after adding it to the attachment or side menu, launching a Web App from a link, or accepting an explicit request from a Web App sent by the method requestWriteAccess. */
    @SerialName("write_access_allowed")
    val writeAccessAllowed: WriteAccessAllowed? = null,

    /** Service message: a user in the chat triggered another user's proximity alert while sharing Live Location. */
    @SerialName("proximity_alert_triggered")
    val proximityAlertTriggered: ProximityAlertTriggered? = null,

    /** Service message: user boosted the chat. */
    @SerialName("boost_added")
    val boostAdded: ChatBoostAdded? = null,

    /** Service message: forum topic created. */
    @SerialName("forum_topic_created")
    val forumTopicCreated: ForumTopicCreated? = null,

    /** Service message: forum topic edited. */
    @SerialName("forum_topic_edited")
    val forumTopicEdited: ForumTopicEdited? = null,

    /** Service message: forum topic closed. */
    @SerialName("forum_topic_closed")
    val forumTopicClosed: ForumTopicClosed? = null,

    /** Service message: forum topic reopened. */
    @SerialName("forum_topic_reopened")
    val forumTopicReopened: ForumTopicReopened? = null,

    /** Service message: the 'General' forum topic hidden. */
    @SerialName("general_forum_topic_hidden")
    val generalForumTopicHidden: GeneralForumTopicHidden? = null,

    /** Service message: the 'General' forum topic unhidden. */
    @SerialName("general_forum_topic_unhidden")
    val generalForumTopicUnhidden: GeneralForumTopicUnhidden? = null,

    /** Service message: video chat scheduled. */
    @SerialName("video_chat_scheduled")
    val videoChatScheduled: VideoChatScheduled? = null,

    /** Service message: video chat started. */
    @SerialName("video_chat_started")
    val videoChatStarted: VideoChatStarted? = null,

    /** Service message: video chat ended. */
    @SerialName("video_chat_ended")
    val videoChatEnded: VideoChatEnded? = null,

    /** Service message: new participants invited to a video chat. */
    @SerialName("video_chat_participants_invited")
    val videoChatParticipantsInvited: VideoChatParticipantsInvited? = null,

    /** Service message: data sent by a Web App. */
    @SerialName("web_app_data")
    val webAppData: WebAppData? = null,
)

/** The first bot_command entity in this message, if any. */
public val Message.commandEntity: MessageEntity?
    get() = entities?.firstOrNull {
        it.type == "bot_command"
    }

/** The command text (without leading `/`), if this message contains a bot command. */
public val Message.commandText: CharSequence?
    get() =
        commandEntity?.let {
            text?.subSequence(it.offset, it.offset + it.length)
                ?.removePrefix("/")
        }
