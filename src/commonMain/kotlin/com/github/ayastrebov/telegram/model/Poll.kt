package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Contains information about a poll.
 */
@OptIn(ExperimentalTime::class)
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
    @Serializable(with = InstantUnixSerializer::class)
    val closeDate: kotlin.time.Instant? = null,

    val description: String? = null,

    @SerialName("description_entities")
    val descriptionEntities: List<MessageEntity>? = null,

    /** True if voting is limited to users who have been members of the chat where the poll was originally sent for more than 24 hours. */
    @SerialName("members_only")
    val membersOnly: Boolean = false,

    /** A list of two-letter ISO 3166-1 alpha-2 country codes indicating the countries from which users can vote in the poll. */
    @SerialName("country_codes")
    val countryCodes: List<String>? = null,
)

/**
 * Contains information about one answer option in a poll.
 */
@OptIn(ExperimentalTime::class)
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
    @Serializable(with = InstantUnixSerializer::class)
    val additionDate: kotlin.time.Instant? = null,
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
