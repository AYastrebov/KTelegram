package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains information about one answer option in a poll to be sent.
 */
@Serializable
public data class InputPollOption(
    val text: String,

    @SerialName("text_parse_mode")
    val textParseMode: String? = null,

    @SerialName("text_entities")
    val textEntities: List<MessageEntity>? = null,
)
