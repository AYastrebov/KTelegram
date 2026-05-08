package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents a result of an inline query that was chosen by the user and sent to their chat partner. */
@Serializable
public data class ChosenInlineResult(
    @SerialName("result_id")
    val resultId: String,

    val from: User,

    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    val query: String
)
