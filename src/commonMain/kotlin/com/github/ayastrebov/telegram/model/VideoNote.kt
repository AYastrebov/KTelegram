package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoNote(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    val duration: Int,
    val length: Int,

    val thumb: PhotoSize? = null,

    @SerialName("file_size")
    val fileSize: Long? = null,
)
