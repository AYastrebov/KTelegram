package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.InputPollOption
import com.github.ayastrebov.telegram.model.KeyboardMarkup
import com.github.ayastrebov.telegram.model.MessageEntity
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

    @SerialName("reply_markup")
    val replyMarkup: KeyboardMarkup? = null,
)
