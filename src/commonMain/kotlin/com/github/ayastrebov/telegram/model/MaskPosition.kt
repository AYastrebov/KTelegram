package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** This object describes the position on faces where a mask should be placed by default. */
@Serializable
public data class MaskPosition(
    /** The part of the face relative to which the mask should be placed. */
    val point: String,

    /** Shift by X-axis measured in widths of the mask scaled to the face size, from left to right. */
    @SerialName("x_shift")
    val xShift: Double,

    /** Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom. */
    @SerialName("y_shift")
    val yShift: Double,

    /** Mask scaling coefficient. */
    val scale: Double,
)
