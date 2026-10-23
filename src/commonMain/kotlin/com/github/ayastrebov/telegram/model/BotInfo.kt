package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** This object represents the bot's name. */
@Serializable
public data class BotName(
    /** The bot's name. */
    val name: String,
)

/** This object represents the bot's description. */
@Serializable
public data class BotDescription(
    /** The bot's description. */
    val description: String,
)

/** This object represents the bot's short description. */
@Serializable
public data class BotShortDescription(
    /** The bot's short description. */
    @SerialName("short_description")
    val shortDescription: String,
)

/** This object represents the scope to which bot commands are applied. Currently, the following 7 scopes are supported: */
@Serializable
public sealed class BotCommandScope

/** Represents the default scope of bot commands. Default commands are used if no commands with a narrower scope are specified for the user. */
@Serializable
@SerialName("default")
public class BotCommandScopeDefault : BotCommandScope()

/** Represents the scope of bot commands, covering all private chats. */
@Serializable
@SerialName("all_private_chats")
public class BotCommandScopeAllPrivateChats : BotCommandScope()

/** Represents the scope of bot commands, covering all group and supergroup chats. */
@Serializable
@SerialName("all_group_chats")
public class BotCommandScopeAllGroupChats : BotCommandScope()

/** Represents the scope of bot commands, covering all group and supergroup chat administrators. */
@Serializable
@SerialName("all_chat_administrators")
public class BotCommandScopeAllChatAdministrators : BotCommandScope()

/** Represents the scope of bot commands, covering a specific chat. */
@Serializable
@SerialName("chat")
public data class BotCommandScopeChat(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,
) : BotCommandScope()

/** Represents the scope of bot commands, covering all administrators of a specific group or supergroup chat. */
@Serializable
@SerialName("chat_administrators")
public data class BotCommandScopeChatAdministrators(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,
) : BotCommandScope()

/** Represents the scope of bot commands, covering a specific member of a group or supergroup chat. */
@Serializable
@SerialName("chat_member")
public data class BotCommandScopeChatMember(
    /** Unique identifier for the target chat or username of the target supergroup in the format @username. */
    @SerialName("chat_id")
    val chatId: String,

    /** Unique identifier of the target user. */
    @SerialName("user_id")
    val userId: Long,
) : BotCommandScope()
