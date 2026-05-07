package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains information about a poll.
 */
@Serializable
public data class Poll(
    val id: String,
    val question: String,

    @SerialName("question_entities")
    val questionEntities: List<MessageEntity>? = null,

    val options: List<PollOption>,

    @SerialName("total_voter_count")
    val totalVoterCount: Int,

    @SerialName("is_closed")
    val isClosed: Boolean,

    @SerialName("is_anonymous")
    val isAnonymous: Boolean,

    val type: String,

    @SerialName("allows_multiple_answers")
    val allowsMultipleAnswers: Boolean,

    @SerialName("allows_revoting")
    val allowsRevoting: Boolean,

    @SerialName("correct_option_ids")
    val correctOptionIds: List<Int>? = null,

    val explanation: String? = null,

    @SerialName("explanation_entities")
    val explanationEntities: List<MessageEntity>? = null,

    @SerialName("open_period")
    val openPeriod: Int? = null,

    @SerialName("close_date")
    val closeDate: Int? = null,

    val description: String? = null,

    @SerialName("description_entities")
    val descriptionEntities: List<MessageEntity>? = null,
)

/**
 * Contains information about one answer option in a poll.
 */
@Serializable
public data class PollOption(
    @SerialName("persistent_id")
    val persistentId: String,

    val text: String,

    @SerialName("text_entities")
    val textEntities: List<MessageEntity>? = null,

    @SerialName("voter_count")
    val voterCount: Int,

    @SerialName("added_by_user")
    val addedByUser: User? = null,

    @SerialName("added_by_chat")
    val addedByChat: Chat? = null,

    @SerialName("addition_date")
    val additionDate: Int? = null,
)

/**
 * Represents an answer of a user in a non-anonymous poll.
 */
@Serializable
public data class PollAnswer(
    @SerialName("poll_id")
    val pollId: String,

    @SerialName("voter_chat")
    val voterChat: Chat? = null,

    val user: User? = null,

    @SerialName("option_ids")
    val optionIds: List<Int>,

    @SerialName("option_persistent_ids")
    val optionPersistentIds: List<String>,
)

/**
 * Service message about a poll option being added by a user.
 */
@Serializable
public data class PollOptionAdded(
    @SerialName("option_persistent_id")
    val optionPersistentId: String,

    @SerialName("option_text")
    val optionText: String,

    @SerialName("option_text_entities")
    val optionTextEntities: List<MessageEntity>? = null,
)

/**
 * Service message about a poll option being deleted.
 */
@Serializable
public data class PollOptionDeleted(
    @SerialName("option_persistent_id")
    val optionPersistentId: String,

    @SerialName("option_text")
    val optionText: String,

    @SerialName("option_text_entities")
    val optionTextEntities: List<MessageEntity>? = null,
)
