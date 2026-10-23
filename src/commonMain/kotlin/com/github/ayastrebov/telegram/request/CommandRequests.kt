package com.github.ayastrebov.telegram.request

import com.github.ayastrebov.telegram.model.BotCommand
import com.github.ayastrebov.telegram.model.BotCommandScope
import com.github.ayastrebov.telegram.model.ChatAdministratorRights
import com.github.ayastrebov.telegram.model.MenuButton
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters for [setMyCommands][com.github.ayastrebov.telegram.Bot.setMyCommands].
 */
@Serializable
public data class SetMyCommandsRequest(
    val commands: List<BotCommand>,

    /** A JSON-serialized object, describing scope of users for which the commands are relevant. */
    val scope: BotCommandScope? = null,

    /** A two-letter ISO 639-1 language code. */
    @SerialName("language_code")
    val languageCode: String? = null,
)

/**
 * Parameters for [getMyCommands][com.github.ayastrebov.telegram.Bot.getMyCommands].
 */
@Serializable
public data class GetMyCommandsRequest(
    /** A JSON-serialized object, describing scope of users. */
    val scope: BotCommandScope? = null,

    /** A two-letter ISO 639-1 language code or an empty string. */
    @SerialName("language_code")
    val languageCode: String? = null,
)

/**
 * Parameters for [deleteMyCommands][com.github.ayastrebov.telegram.Bot.deleteMyCommands].
 */
@Serializable
public data class DeleteMyCommandsRequest(
    /** A JSON-serialized object, describing scope of users for which the commands are relevant. */
    val scope: BotCommandScope? = null,

    /** A two-letter ISO 639-1 language code. */
    @SerialName("language_code")
    val languageCode: String? = null,
)

/**
 * Parameters for [setMyName][com.github.ayastrebov.telegram.Bot.setMyName].
 */
@Serializable
public data class SetMyNameRequest(
    /** New bot name; 0-64 characters. */
    val name: String? = null,

    /** A two-letter ISO 639-1 language code. */
    @SerialName("language_code")
    val languageCode: String? = null,
)

/**
 * Parameters for [getMyName][com.github.ayastrebov.telegram.Bot.getMyName].
 */
@Serializable
public data class GetMyNameRequest(
    /** A two-letter ISO 639-1 language code or an empty string. */
    @SerialName("language_code")
    val languageCode: String? = null,
)

/**
 * Parameters for [setMyDescription][com.github.ayastrebov.telegram.Bot.setMyDescription].
 */
@Serializable
public data class SetMyDescriptionRequest(
    /** New bot description; 0-512 characters. */
    val description: String? = null,

    /** A two-letter ISO 639-1 language code. */
    @SerialName("language_code")
    val languageCode: String? = null,
)

/**
 * Parameters for [getMyDescription][com.github.ayastrebov.telegram.Bot.getMyDescription].
 */
@Serializable
public data class GetMyDescriptionRequest(
    /** A two-letter ISO 639-1 language code or an empty string. */
    @SerialName("language_code")
    val languageCode: String? = null,
)

/**
 * Parameters for [setMyShortDescription][com.github.ayastrebov.telegram.Bot.setMyShortDescription].
 */
@Serializable
public data class SetMyShortDescriptionRequest(
    /** New short description for the bot; 0-120 characters. */
    @SerialName("short_description")
    val shortDescription: String? = null,

    /** A two-letter ISO 639-1 language code. */
    @SerialName("language_code")
    val languageCode: String? = null,
)

/**
 * Parameters for [getMyShortDescription][com.github.ayastrebov.telegram.Bot.getMyShortDescription].
 */
@Serializable
public data class GetMyShortDescriptionRequest(
    /** A two-letter ISO 639-1 language code or an empty string. */
    @SerialName("language_code")
    val languageCode: String? = null,
)

/**
 * Parameters for [setMyDefaultAdministratorRights][com.github.ayastrebov.telegram.Bot.setMyDefaultAdministratorRights].
 */
@Serializable
public data class SetMyDefaultAdministratorRightsRequest(
    /** A JSON-serialized object describing new default administrator rights. */
    val rights: ChatAdministratorRights? = null,

    /** Pass True to change the default administrator rights of the bot in channels. */
    @SerialName("for_channels")
    val forChannels: Boolean? = null,
)

/**
 * Parameters for [getMyDefaultAdministratorRights][com.github.ayastrebov.telegram.Bot.getMyDefaultAdministratorRights].
 */
@Serializable
public data class GetMyDefaultAdministratorRightsRequest(
    /** Pass True to get default administrator rights of the bot in channels. */
    @SerialName("for_channels")
    val forChannels: Boolean? = null,
)

/**
 * Parameters for [setChatMenuButton][com.github.ayastrebov.telegram.Bot.setChatMenuButton].
 */
@Serializable
public data class SetChatMenuButtonRequest(
    /** Unique identifier for the target private chat. */
    @SerialName("chat_id")
    val chatId: Long? = null,

    /** A JSON-serialized object for the bot's new menu button. */
    @SerialName("menu_button")
    val menuButton: MenuButton? = null,
)

/**
 * Parameters for [getChatMenuButton][com.github.ayastrebov.telegram.Bot.getChatMenuButton].
 */
@Serializable
public data class GetChatMenuButtonRequest(
    /** Unique identifier for the target private chat. */
    @SerialName("chat_id")
    val chatId: Long? = null,
)
