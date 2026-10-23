package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents a general file (as opposed to photos, voice messages and audio files). */
@Serializable
public data class Document(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    @Deprecated("Renamed to thumbnail in Bot API 6.6", ReplaceWith("thumbnail"))
    val thumb: PhotoSize? = null,

    @SerialName("file_name")
    val fileName: String? = null,

    @SerialName("mime_type")
    val mimeType: String? = null,

    @SerialName("file_size")
    val fileSize: Long? = null,

    /** Document thumbnail as defined by the sender. */
    val thumbnail: PhotoSize? = null,
)
