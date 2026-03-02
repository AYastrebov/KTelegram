package com.github.ayastrebov.telegram.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateRequest(
    val offset: Long? = null,
    val limit: Long? = null,
    val timeout: Long? = null,

    @SerialName("allowed_updates")
    val allowedUpdates: List<String>? = null,
)
