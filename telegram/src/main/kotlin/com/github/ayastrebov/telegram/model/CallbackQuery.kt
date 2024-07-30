package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CallbackQuery(
    val id: String,
    val from: User,
    val message: Message? = null,

    @SerialName("inline_message_id")
    val inlineMessageId: String? = null,

    @SerialName("chat_instance")
    val chatInstance: String? = null,

    val data: String? = null,

    @SerialName("game_short_name")
    val gameShortName: String? = null,
)
