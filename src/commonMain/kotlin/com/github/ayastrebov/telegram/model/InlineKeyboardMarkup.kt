package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Base type for all reply markup options (inline keyboard, reply keyboard, remove, force reply). */
@Serializable
public sealed class KeyboardMarkup

/** Represents an inline keyboard that appears right next to the message it belongs to. */
@Serializable
public data class InlineKeyboardMarkup(
    @SerialName("inline_keyboard")
    val inlineKeyboard: List<List<InlineKeyboardButton>>
) : KeyboardMarkup()

/** Represents one button of an inline keyboard. */
@Serializable
public data class InlineKeyboardButton(
    val text: String,
    val url: String? = null,

    @SerialName("callback_data")
    val callbackData: String? = null,

    @SerialName("switch_inline_query")
    val switchInlineQuery: String? = null,

    @SerialName("switch_inline_query_current_chat")
    val switchInlineQueryCurrentChat: String? = null,

    val pay: Boolean? = null,

    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,

    val style: String? = null,
)
