package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an animation file (GIF or H.264/MPEG-4 AVC video without sound).
 *
 * @property fileId Identifier for this file.
 * @property fileUniqueId Unique identifier for this file.
 * @property width Video width as defined by sender.
 * @property height Video height as defined by sender.
 * @property duration Duration of the video in seconds.
 * @property thumbnail Animation thumbnail as defined by sender.
 * @property fileName Original animation filename.
 * @property mimeType MIME type of the file.
 * @property fileSize File size in bytes.
 */
@Serializable
data class Animation(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    val width: Int,
    val height: Int,
    val duration: Int,

    val thumbnail: PhotoSize? = null,

    @SerialName("file_name")
    val fileName: String? = null,

    @SerialName("mime_type")
    val mimeType: String? = null,

    @SerialName("file_size")
    val fileSize: Long? = null,
)
