package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** This object represents a venue. */
@Serializable
public data class Venue(
    /** Venue location. */
    val location: Location,

    /** Name of the venue. */
    val title: String,

    /** Address of the venue. */
    val address: String,

    /** Foursquare identifier of the venue. */
    @SerialName("foursquare_id")
    val foursquareId: String? = null,

    /** Foursquare type of the venue. */
    @SerialName("foursquare_type")
    val foursquareType: String? = null,

    /** Google Places identifier of the venue. */
    @SerialName("google_place_id")
    val googlePlaceId: String? = null,

    /** Google Places type of the venue. */
    @SerialName("google_place_type")
    val googlePlaceType: String? = null,
)
