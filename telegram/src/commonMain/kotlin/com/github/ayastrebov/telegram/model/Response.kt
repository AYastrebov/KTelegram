package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val ok: Boolean,
    val result: T? = null,
    val description: String? = null,

    @SerialName("error_code")
    val errorCode: Int? = null,
)
