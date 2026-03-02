package com.github.ayastrebov.telegram.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [getUpdates][com.github.ayastrebov.telegram.Bot.getUpdates].
 */
@Serializable
data class GetUpdatesRequest(
    val offset: Long? = null,
    val limit: Long? = null,
    val timeout: Long? = null,

    @SerialName("allowed_updates")
    val allowedUpdates: List<String>? = null,
)

/**
 * Parameters for [setWebhook][com.github.ayastrebov.telegram.Bot.setWebhook].
 */
@Serializable
data class SetWebhookRequest(
    val url: String,

    @SerialName("max_connections")
    val maxConnections: Int? = null,

    @SerialName("allowed_updates")
    val allowedUpdates: List<String>? = null,

    @SerialName("drop_pending_updates")
    val dropPendingUpdates: Boolean? = null,

    @SerialName("secret_token")
    val secretToken: String? = null,
)
