package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** This object represents a live photo. */
@Serializable
public data class LivePhoto(
    /** Identifier for the video file which can be used to download or reuse the file. */
    @SerialName("file_id")
    val fileId: String,

    /** Unique identifier for the video file which is supposed to be the same over time and for different bots. */
    @SerialName("file_unique_id")
    val fileUniqueId: String,

    /** Video width as defined by the sender. */
    val width: Int,

    /** Video height as defined by the sender. */
    val height: Int,

    /** Duration of the video in seconds as defined by the sender. */
    val duration: Int,

    /** Available sizes of the corresponding static photo. */
    val photo: List<PhotoSize>? = null,

    /** MIME type of the file as defined by the sender. */
    @SerialName("mime_type")
    val mimeType: String? = null,

    /** File size in bytes. */
    @SerialName("file_size")
    val fileSize: Long? = null,
)
