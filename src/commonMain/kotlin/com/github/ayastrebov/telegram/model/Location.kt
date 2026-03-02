package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a point on the map.
 *
 * @property longitude Longitude as defined by sender.
 * @property latitude Latitude as defined by sender.
 * @property horizontalAccuracy The radius of uncertainty for the location, measured in meters.
 * @property livePeriod Time relative to the message sending date, during which the location can be updated (in seconds).
 * @property heading The direction in which the user is moving, in degrees (1-360).
 * @property proximityAlertRadius Maximum distance for proximity alerts about approaching another chat member, in meters.
 */
@Serializable
data class Location(
    val longitude: Double,
    val latitude: Double,

    @SerialName("horizontal_accuracy")
    val horizontalAccuracy: Double? = null,

    @SerialName("live_period")
    val livePeriod: Int? = null,

    val heading: Int? = null,

    @SerialName("proximity_alert_radius")
    val proximityAlertRadius: Int? = null,
)
