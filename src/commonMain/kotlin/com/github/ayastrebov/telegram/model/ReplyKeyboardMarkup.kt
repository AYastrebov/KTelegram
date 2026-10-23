package com.github.ayastrebov.telegram.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents a custom keyboard with reply options. */
@Serializable
public data class ReplyKeyboardMarkup(
    val keyboard: List<List<KeyboardButton>>,

    @SerialName("resize_keyboard")
    val resizeKeyboard: Boolean? = null,

    @SerialName("one_time_keyboard")
    val oneTimeKeyboard: Boolean? = null,

    @SerialName("input_field_placeholder")
    val inputFieldPlaceholder: String? = null,

    val selective: Boolean? = null,

    /** Requests clients to always show the keyboard when the regular keyboard is hidden. */
    @SerialName("is_persistent")
    val isPersistent: Boolean? = null,

    /** Pass True if the reply interface must be shown to the user, as if they had manually selected the bot's message and tapped 'Reply'. */
    @SerialName("force_reply")
    val forceReply: Boolean? = null,
) : KeyboardMarkup()

/** Represents one button of the reply keyboard. */
@Serializable
public data class KeyboardButton(
    val text: String,

    @SerialName("request_contact")
    val requestContact: Boolean? = null,

    @SerialName("request_location")
    val requestLocation: Boolean? = null,

    @SerialName("request_poll")
    val requestPoll: KeyboardButtonPollType? = null,

    @SerialName("request_managed_bot")
    val requestManagedBot: KeyboardButtonRequestManagedBot? = null,

    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,

    val style: String? = null,

    /** If specified, pressing the button will open a list of suitable users. */
    @SerialName("request_users")
    val requestUsers: KeyboardButtonRequestUsers? = null,

    /** If specified, pressing the button will open a list of suitable chats. */
    @SerialName("request_chat")
    val requestChat: KeyboardButtonRequestChat? = null,

    /** If specified, the described Web App will be launched when the button is pressed. */
    @SerialName("web_app")
    val webApp: WebAppInfo? = null,
)

/** Represents type of a poll, which is allowed to be created when the corresponding button is pressed. */
@Serializable
public data class KeyboardButtonPollType(
    val type: String? = null,
)

/** Requests clients to remove the custom keyboard and display the default letter-keyboard. */
@OptIn(ExperimentalSerializationApi::class)
@Serializable
public data class ReplyKeyboardRemove(
    /** Always `true`; encoded even though it is a default so Telegram actually removes the keyboard. */
    @EncodeDefault
    @SerialName("remove_keyboard")
    val removeKeyboard: Boolean = true,

    val selective: Boolean? = null,
) : KeyboardMarkup()

/** Displays a reply interface to the user (act as if the user has selected the bot's message and tapped "Reply"). */
@Serializable
public data class ForceReply(
    @SerialName("force_reply")
    val forceReply: Boolean,

    @SerialName("input_field_placeholder")
    val inputFieldPlaceholder: String? = null,

    val selective: Boolean? = null,
) : KeyboardMarkup()
