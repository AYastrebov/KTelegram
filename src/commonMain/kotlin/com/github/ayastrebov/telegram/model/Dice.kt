package com.github.ayastrebov.telegram.model

import kotlinx.serialization.Serializable

/**
 * Represents an animated emoji that displays a random value.
 *
 * @property emoji Emoji on which the dice throw animation is based.
 * @property value Value of the dice: 1-6 for dice/darts/bowling, 1-5 for basketball/football, 1-64 for slot machine.
 */
@Serializable
data class Dice(
    val emoji: String,
    val value: Int,
)
