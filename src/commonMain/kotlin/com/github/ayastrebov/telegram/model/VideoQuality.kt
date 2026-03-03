package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Describes a video file of a specific quality.
 */
@Serializable
data class VideoQuality(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    val width: Int,
    val height: Int,
    val codec: String,

    @SerialName("file_size")
    val fileSize: Int? = null,
)
