package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**  */
@Serializable
public data class EphemeralMessageParameters(
    /** Identifier of the user who will receive the message. */
    @SerialName("receiver_user_id")
    val receiverUserId: Long,

    /** Identifier of the callback query which triggered the message, if any. */
    @SerialName("callback_query_id")
    val callbackQueryId: String? = null,

    /** Pass True if the ephemeral message must be shown in place of the original message. */
    @SerialName("replace_callback_query_message")
    val replaceCallbackQueryMessage: Boolean? = null,
)
