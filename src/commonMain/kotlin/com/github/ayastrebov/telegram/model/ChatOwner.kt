package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Describes a service message about the chat owner leaving the chat. */
@Serializable
public data class ChatOwnerLeft(
    /** The user who will become the new owner of the chat if the previous owner does not return to the chat. */
    @SerialName("new_owner")
    val newOwner: User? = null,
)

/** Describes a service message about an ownership change in the chat. */
@Serializable
public data class ChatOwnerChanged(
    /** The new owner of the chat. */
    @SerialName("new_owner")
    val newOwner: User,
)
