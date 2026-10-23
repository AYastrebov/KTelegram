package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.InputPollOption
import com.github.ayastrebov.telegram.model.KeyboardMarkup
import com.github.ayastrebov.telegram.model.MessageEntity
import com.github.ayastrebov.telegram.model.ReplyParameters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [sendPoll][com.github.ayastrebov.telegram.Bot.sendPoll].
 */
@Serializable
public data class SendPollRequest(
    @SerialName("chat_id")
    val chatId: String,

    val question: String,

    val options: List<InputPollOption>,

    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,

    @SerialName("direct_messages_topic_id")
    val directMessagesTopicId: Int? = null,

    @SerialName("question_parse_mode")
    val questionParseMode: String? = null,

    @SerialName("question_entities")
    val questionEntities: List<MessageEntity>? = null,

    @SerialName("is_anonymous")
    val isAnonymous: Boolean? = null,

    val type: String? = null,

    @SerialName("allows_multiple_answers")
    val allowsMultipleAnswers: Boolean? = null,

    @SerialName("correct_option_ids")
    val correctOptionIds: List<Int>? = null,

    val explanation: String? = null,

    @SerialName("explanation_parse_mode")
    val explanationParseMode: String? = null,

    @SerialName("explanation_entities")
    val explanationEntities: List<MessageEntity>? = null,

    @SerialName("open_period")
    val openPeriod: Int? = null,

    @SerialName("close_date")
    val closeDate: Int? = null,

    @SerialName("is_closed")
    val isClosed: Boolean? = null,

    @SerialName("allows_revoting")
    val allowsRevoting: Boolean? = null,

    @SerialName("shuffle_options")
    val shuffleOptions: Boolean? = null,

    @SerialName("allow_adding_options")
    val allowAddingOptions: Boolean? = null,

    @SerialName("hide_results_until_closes")
    val hideResultsUntilCloses: Boolean? = null,

    val description: String? = null,

    @SerialName("description_parse_mode")
    val descriptionParseMode: String? = null,

    @SerialName("description_entities")
    val descriptionEntities: List<MessageEntity>? = null,

    @SerialName("disable_notification")
    val disableNotification: Boolean? = null,

    @SerialName("protect_content")
    val protectContent: Boolean? = null,

    @SerialName("reply_parameters")
    val replyParameters: ReplyParameters? = null,

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,

    /** Unique identifier of the business connection on behalf of which the message will be sent. */
    @SerialName("business_connection_id")
    val businessConnectionId: String? = null,

    /** Pass True if voting is limited to users who have been members of the chat where the poll is being sent for more than 24 hours; for channel chats only. */
    @SerialName("members_only")
    val membersOnly: Boolean? = null,

    /** A JSON-serialized list of 0-12 two-letter ISO 3166-1 alpha-2 country codes indicating the countries from which users can vote in the poll; for channel chats only. */
    @SerialName("country_codes")
    val countryCodes: List<String>? = null,

    /** Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message. */
    @SerialName("allow_paid_broadcast")
    val allowPaidBroadcast: Boolean? = null,

    /** Unique identifier of the message effect to be added to the message; for private chats only. */
    @SerialName("message_effect_id")
    val messageEffectId: String? = null,
)
