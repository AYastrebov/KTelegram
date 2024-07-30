package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sticker(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    val width: Int,
    val height: Int,

    @SerialName("is_animated")
    val isAnimated: Boolean = false,

    @SerialName("is_video")
    val isVideo: Boolean = false,

    val emoji: String? = null,
    val thumb: PhotoSize? = null,

    @SerialName("set_name")
    val setName: String? = null,

    @SerialName("file_size")
    val fileSize: Long? = null,
)
