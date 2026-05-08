package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents one size of a photo or a file/sticker thumbnail. */
@Serializable
public data class PhotoSize(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    val width: Int,
    val height: Int,

    @SerialName("file_size")
    val fileSize: Long? = null,
)
