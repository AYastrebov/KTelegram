package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class KeyboardMarkup

@Serializable
data class InlineKeyboardMarkup(
    @SerialName("inline_keyboard")
    val inlineKeyboard: List<List<InlineKeyboardButton>>
) : KeyboardMarkup()

@Serializable
data class InlineKeyboardButton(
    val text: String,
    val url: String? = null,

    @SerialName("callback_data")
    val callbackData: String? = null,

    @SerialName("switch_inline_query")
    val switchInlineQuery: String? = null,

    @SerialName("switch_inline_query_current_chat")
    val switchInlineQueryCurrentChat: String? = null,

    val pay: Boolean? = null
)