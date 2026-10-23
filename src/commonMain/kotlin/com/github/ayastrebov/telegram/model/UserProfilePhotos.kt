package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** This object represent a user's profile pictures. */
@Serializable
public data class UserProfilePhotos(
    /** Total number of profile pictures the target user has. */
    @SerialName("total_count")
    val totalCount: Int,

    /** Requested profile pictures (in up to 4 sizes each). */
    val photos: List<List<PhotoSize>>,
)
