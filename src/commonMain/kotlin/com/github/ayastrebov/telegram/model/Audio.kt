package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents an audio file to be treated as music by the Telegram clients. */
@Serializable
public data class Audio(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    val duration: Int,
    val performer: String? = null,
    val title: String? = null,

    @SerialName("file_name")
    val fileName: String? = null,

    @SerialName("mime_type")
    val mimeType: String? = null,

    @SerialName("file_size")
    val fileSize: Long? = null,

    @Deprecated("Renamed to thumbnail in Bot API 6.6", ReplaceWith("thumbnail"))
    val thumb: PhotoSize? = null,

    /** Thumbnail of the album cover to which the music file belongs. */
    val thumbnail: PhotoSize? = null,
)
