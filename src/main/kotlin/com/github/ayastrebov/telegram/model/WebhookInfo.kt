package com.github.ayastrebov.telegram.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebhookInfo(
    val url: String,

    @SerialName("has_custom_certificate")
    val hasCustomCertificate: Boolean,

    @SerialName("pending_update_count")
    val pendingUpdateCount: Long,

    @SerialName("last_error_date")
    val lastErrorDate: LocalDateTime? = null,

    @SerialName("last_error_message")
    val lastErrorMessage: String? = null,

    @SerialName("last_synchronization_error_date")
    val lastSynchronizationErrorDate: LocalDateTime? = null,

    @SerialName("max_connections")
    val maxConnections: Long? = null,

    @SerialName("ip_address")
    val ipAddress: String? = null,

    @SerialName("allowed_updates")
    val allowedUpdates: List<String>? = null
)