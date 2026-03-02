package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Contains information about the current status of a webhook.
 */
@OptIn(ExperimentalTime::class)
@Serializable
data class WebhookInfo(
    val url: String,

    @SerialName("has_custom_certificate")
    val hasCustomCertificate: Boolean,

    @SerialName("pending_update_count")
    val pendingUpdateCount: Long,

    @SerialName("last_error_date")
    @Serializable(with = InstantUnixSerializer::class)
    val lastErrorDate: kotlin.time.Instant? = null,

    @SerialName("last_error_message")
    val lastErrorMessage: String? = null,

    @SerialName("last_synchronization_error_date")
    @Serializable(with = InstantUnixSerializer::class)
    val lastSynchronizationErrorDate: kotlin.time.Instant? = null,

    @SerialName("max_connections")
    val maxConnections: Long? = null,

    @SerialName("ip_address")
    val ipAddress: String? = null,

    @SerialName("allowed_updates")
    val allowedUpdates: List<String>? = null
)
