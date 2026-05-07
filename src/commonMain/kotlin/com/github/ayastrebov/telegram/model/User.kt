package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a Telegram user or bot.
 */
@Serializable
public data class User(
    val id: Long,

    @SerialName("is_bot")
    val isBot: Boolean = false,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String? = null,

    val username: String? = null,

    @SerialName("language_code")
    val languageCode: String? = null,

    @SerialName("is_premium")
    val isPremium: Boolean? = null,

    @SerialName("allows_users_to_create_topics")
    val allowsUsersToCreateTopics: Boolean? = null,

    @SerialName("added_to_attachment_menu")
    val addedToAttachmentMenu: Boolean? = null,

    @SerialName("can_join_groups")
    val canJoinGroups: Boolean? = null,

    @SerialName("can_read_all_group_messages")
    val canReadAllGroupMessages: Boolean? = null,

    @SerialName("supports_inline_queries")
    val supportsInlineQueries: Boolean? = null,

    @SerialName("can_connect_to_business")
    val canConnectToBusiness: Boolean? = null,

    @SerialName("has_main_web_app")
    val hasMainWebApp: Boolean? = null,

    @SerialName("has_topics_enabled")
    val hasTopicsEnabled: Boolean? = null,

    @SerialName("can_manage_bots")
    val canManageBots: Boolean? = null,
)
