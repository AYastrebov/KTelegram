package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReplyKeyboardMarkup(
    val keyboard: List<List<KeyboardButton>>,

    @SerialName("resize_keyboard")
    val resizeKeyboard: Boolean? = null,

    @SerialName("one_time_keyboard")
    val oneTimeKeyboard: Boolean? = null,

    @SerialName("input_field_placeholder")
    val inputFieldPlaceholder: String? = null,

    val selective: Boolean? = null,
) : KeyboardMarkup()

@Serializable
data class KeyboardButton(
    val text: String,

    @SerialName("request_contact")
    val requestContact: Boolean? = null,

    @SerialName("request_location")
    val requestLocation: Boolean? = null,

    @SerialName("request_poll")
    val requestPoll: KeyboardButtonPollType? = null,
)

@Serializable
data class KeyboardButtonPollType(
    val type: String? = null,
)

@Serializable
data class ReplyKeyboardRemove(
    @SerialName("remove_keyboard")
    val removeKeyboard: Boolean = true,

    val selective: Boolean? = null,
) : KeyboardMarkup()

@Serializable
data class ForceReply(
    @SerialName("force_reply")
    val forceReply: Boolean,

    @SerialName("input_field_placeholder")
    val inputFieldPlaceholder: String? = null,

    val selective: Boolean? = null,
) : KeyboardMarkup()