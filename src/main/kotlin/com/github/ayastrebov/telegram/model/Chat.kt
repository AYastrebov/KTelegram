package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val id: Long,

    @SerialName("first_name")
    val firstName: String? = null,

    @SerialName("last_name")
    val lastName: String? = null,

    val username: String? = null,
    val type: String,
    val title: String? = null,
    val bio: String? = null,
    val description: String? = null,
)

val Chat.isGroup: Boolean
    get() = when (type) {
        "supergroup", "group" -> true
        else -> false
    }

val Chat.isPrivate: Boolean
    get() = when (type) {
        "private" -> true
        else -> false
    }
