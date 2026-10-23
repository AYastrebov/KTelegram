package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Describes a Web App. */
@Serializable
public data class WebAppInfo(
    /** An HTTPS URL of a Web App to be opened with additional data as specified in Initializing Web Apps. */
    val url: String,
)

/** Describes data sent from a Web App to the bot. */
@Serializable
public data class WebAppData(
    /** The data. */
    val data: String,

    /** Text of the web_app keyboard button from which the Web App was opened. */
    @SerialName("button_text")
    val buttonText: String,
)

/** This object represents a parameter of the inline keyboard button used to automatically authorize a user. It serves as a great replacement for the Telegram Login Widget when the user is coming from Telegram. All the user needs to do is tap/click a button and confirm that they want to log in: */
@Serializable
public data class LoginUrl(
    /** An HTTPS URL to be opened with user authorization data added to the query string when the button is pressed. */
    val url: String,

    /** New text of the button in forwarded messages. */
    @SerialName("forward_text")
    val forwardText: String? = null,

    /** Username of a bot, which will be used for user authorization; not supported in RichMessageButton. */
    @SerialName("bot_username")
    val botUsername: String? = null,

    /** Pass True to request the permission for your bot to send messages to the user. */
    @SerialName("request_write_access")
    val requestWriteAccess: Boolean? = null,
)

/** This object represents an inline keyboard button that copies specified text to the clipboard. */
@Serializable
public data class CopyTextButton(
    /** The text to be copied to the clipboard; 1-256 characters. */
    val text: String,
)

/** This object represents an inline button that switches the current user to inline mode in a chosen chat, with an optional default inline query. */
@Serializable
public data class SwitchInlineQueryChosenChat(
    /** The default inline query to be inserted in the input field. */
    val query: String? = null,

    /** True, if private chats with users can be chosen. */
    @SerialName("allow_user_chats")
    val allowUserChats: Boolean? = null,

    /** True, if private chats with bots can be chosen. */
    @SerialName("allow_bot_chats")
    val allowBotChats: Boolean? = null,

    /** True, if group and supergroup chats can be chosen. */
    @SerialName("allow_group_chats")
    val allowGroupChats: Boolean? = null,

    /** True, if channel chats can be chosen. */
    @SerialName("allow_channel_chats")
    val allowChannelChats: Boolean? = null,
)

/** A placeholder, currently holds no information. Use BotFather to set up your game. */
@Serializable
public class CallbackGame

/** This object describes the bot's menu button in a private chat. It should be one of */
@Serializable
public sealed class MenuButton

/** Represents a menu button, which opens the bot's list of commands. */
@Serializable
@SerialName("commands")
public class MenuButtonCommands : MenuButton()

/** Represents a menu button, which launches a Web App. */
@Serializable
@SerialName("web_app")
public data class MenuButtonWebApp(
    /** Text on the button. */
    val text: String,

    /** Description of the Web App that will be launched when the user presses the button. */
    @SerialName("web_app")
    val webApp: WebAppInfo,
) : MenuButton()

/** Describes that no specific value for the menu button was set. */
@Serializable
@SerialName("default")
public class MenuButtonDefault : MenuButton()

/** This object represents a button to be shown above inline query results. You must use exactly one of the optional fields. */
@Serializable
public data class InlineQueryResultsButton(
    /** Label text on the button. */
    val text: String,

    /** Description of the Web App that will be launched when the user presses the button. */
    @SerialName("web_app")
    val webApp: WebAppInfo? = null,

    /** Deep-linking parameter for the /start message sent to the bot when a user presses the button. */
    @SerialName("start_parameter")
    val startParameter: String? = null,
)
