package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,

    @SerialName("is_bot")
    val isBot: Boolean = false,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String? = null,

    val username: String? = null,

    @SerialName("language_code")
    val languageCode: String? = null,
)

