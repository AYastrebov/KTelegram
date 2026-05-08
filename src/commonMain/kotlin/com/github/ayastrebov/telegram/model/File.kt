package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a file ready to be downloaded.
 *
 * The file can be downloaded via the link `https://api.telegram.org/file/bot<token>/<file_path>`.
 */
@Serializable
public data class File(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    @SerialName("file_size")
    val fileSize: Long? = null,

    @SerialName("file_path")
    val filePath: String? = null,
)
