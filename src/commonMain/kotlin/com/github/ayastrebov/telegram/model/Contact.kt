package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a phone contact.
 *
 * @property phoneNumber Contact's phone number.
 * @property firstName Contact's first name.
 * @property lastName Contact's last name.
 * @property userId Contact's user identifier in Telegram.
 * @property vcard Additional data about the contact in the form of a vCard.
 */
@Serializable
data class Contact(
    @SerialName("phone_number")
    val phoneNumber: String,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String? = null,

    @SerialName("user_id")
    val userId: Long? = null,

    val vcard: String? = null,
)
