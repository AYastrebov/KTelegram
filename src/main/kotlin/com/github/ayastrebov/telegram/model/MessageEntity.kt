package com.github.ayastrebov.telegram.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageEntity(
    val offset: Int,
    val length: Int,
    val type: String,
    val url: String? = null,
    val language: String? = null,
    val user: User? = null,
)
