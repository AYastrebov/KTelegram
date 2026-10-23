package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents a video message (rounded square video). */
@Serializable
public data class VideoNote(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    val duration: Int,
    val length: Int,

    @Deprecated("Renamed to thumbnail in Bot API 6.6", ReplaceWith("thumbnail"))
    val thumb: PhotoSize? = null,

    @SerialName("file_size")
    val fileSize: Long? = null,

    /** Video thumbnail. */
    val thumbnail: PhotoSize? = null,
)
