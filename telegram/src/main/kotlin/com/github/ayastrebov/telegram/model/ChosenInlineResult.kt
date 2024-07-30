package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChosenInlineResult(
    @SerialName("result_id")
    val resultId: String,

    val from: User,

    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    val query: String
)
