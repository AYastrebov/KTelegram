package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an incoming update from Telegram.
 *
 * At most one of the optional fields can be present in any given update.
 */
@Serializable
public data class Update(
    @SerialName("update_id")
    val updateId: Long,

    val message: Message? = null,

    @SerialName("edited_message")
    val editedMessage: Message? = null,

    @SerialName("channel_post")
    val channelPost: Message? = null,

    @SerialName("edited_channel_post")
    val editedChannelPost: Message? = null,

    @SerialName("chosen_inline_result")
    val chosenInlineResult: ChosenInlineResult? = null,

    @SerialName("callback_query")
    val callbackQuery: CallbackQuery? = null,

    @SerialName("inline_query")
    val inlineQuery: InlineQuery? = null,

    @SerialName("my_chat_member")
    val myChatMember: ChatMemberUpdated? = null,

    @SerialName("chat_member")
    val chatMember: ChatMemberUpdated? = null,

    @SerialName("chat_join_request")
    val chatJoinRequest: ChatJoinRequest? = null,

    val poll: Poll? = null,

    @SerialName("poll_answer")
    val pollAnswer: PollAnswer? = null,

    @SerialName("managed_bot")
    val managedBot: ManagedBotUpdated? = null,

    /** The bot was connected to or disconnected from a business account, or a user edited an existing connection with the bot. */
    @SerialName("business_connection")
    val businessConnection: BusinessConnection? = null,

    /** New message from a connected business account. */
    @SerialName("business_message")
    val businessMessage: Message? = null,

    /** New version of a message from a connected business account. */
    @SerialName("edited_business_message")
    val editedBusinessMessage: Message? = null,

    /** Messages were deleted from a connected business account. */
    @SerialName("deleted_business_messages")
    val deletedBusinessMessages: BusinessMessagesDeleted? = null,

    /** New guest message. */
    @SerialName("guest_message")
    val guestMessage: Message? = null,

    /** A reaction to a message was changed by a user. */
    @SerialName("message_reaction")
    val messageReaction: MessageReactionUpdated? = null,

    /** Reactions to a message with anonymous reactions were changed. */
    @SerialName("message_reaction_count")
    val messageReactionCount: MessageReactionCountUpdated? = null,

    /** New incoming shipping query. */
    @SerialName("shipping_query")
    val shippingQuery: ShippingQuery? = null,

    /** New incoming pre-checkout query. */
    @SerialName("pre_checkout_query")
    val preCheckoutQuery: PreCheckoutQuery? = null,

    /** A user purchased paid media with a non-empty payload sent by the bot in a non-channel chat. */
    @SerialName("purchased_paid_media")
    val purchasedPaidMedia: PaidMediaPurchased? = null,

    /** A chat boost was added or changed. */
    @SerialName("chat_boost")
    val chatBoost: ChatBoostUpdated? = null,

    /** A boost was removed from a chat. */
    @SerialName("removed_chat_boost")
    val removedChatBoost: ChatBoostRemoved? = null,

    /** User payment subscription has changed. */
    val subscription: BotSubscriptionUpdated? = null,

    /** A user asked the bot to stop the generation of a message. */
    @SerialName("stopped_message_generation")
    val stoppedMessageGeneration: MessageGenerationStopped? = null,
)
