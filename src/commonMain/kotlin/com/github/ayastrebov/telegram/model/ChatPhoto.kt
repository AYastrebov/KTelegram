package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a chat photo.
 *
 * @property smallFileId File identifier of small (160x160) chat photo.
 * @property smallFileUniqueId Unique file identifier of small chat photo.
 * @property bigFileId File identifier of big (640x640) chat photo.
 * @property bigFileUniqueId Unique file identifier of big chat photo.
 */
@Serializable
data class ChatPhoto(
    @SerialName("small_file_id")
    val smallFileId: String,

    @SerialName("small_file_unique_id")
    val smallFileUniqueId: String,

    @SerialName("big_file_id")
    val bigFileId: String,

    @SerialName("big_file_unique_id")
    val bigFileUniqueId: String,
)
