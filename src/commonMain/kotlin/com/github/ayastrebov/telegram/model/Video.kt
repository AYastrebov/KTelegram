package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents a video file. */
@Serializable
public data class Video(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    val duration: Int,
    val width: Int,
    val height: Int,

    @Deprecated("Renamed to thumbnail in Bot API 6.6", ReplaceWith("thumbnail"))
    val thumb: PhotoSize? = null,

    @SerialName("file_name")
    val fileName: String? = null,

    @SerialName("mime_type")
    val mimeType: String? = null,

    @SerialName("file_size")
    val fileSize: Long? = null,

    val qualities: List<VideoQuality>? = null,

    /** Video thumbnail. */
    val thumbnail: PhotoSize? = null,

    /** Available sizes of the cover of the video in the message. */
    val cover: List<PhotoSize>? = null,

    /** Timestamp in seconds from which the video will play in the message. */
    @SerialName("start_timestamp")
    val startTimestamp: Int? = null,
)
