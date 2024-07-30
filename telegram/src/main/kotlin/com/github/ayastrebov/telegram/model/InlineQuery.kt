package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InlineQuery(
    val id: String,
    val from: User,
    val query: String,
    val offset: String,

    @SerialName("chat_type")
    val chatType: String? = null,
)
