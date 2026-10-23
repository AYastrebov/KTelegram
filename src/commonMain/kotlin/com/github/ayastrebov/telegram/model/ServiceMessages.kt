package com.github.ayastrebov.telegram.model

import com.github.ayastrebov.telegram.utils.InstantUnixSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/** This object represents a service message about a new forum topic created in the chat. */
@Serializable
public data class ForumTopicCreated(
    /** Name of the topic. */
    val name: String,

    /** Color of the topic icon in RGB format. */
    @SerialName("icon_color")
    val iconColor: Int,

    /** Unique identifier of the custom emoji shown as the topic icon. */
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,

    /** True, if the name of the topic wasn't specified explicitly by its creator and likely needs to be changed by the bot. */
    @SerialName("is_name_implicit")
    val isNameImplicit: Boolean? = null,
)

/** This object represents a service message about an edited forum topic. */
@Serializable
public data class ForumTopicEdited(
    /** New name of the topic, if it was edited. */
    val name: String? = null,

    /** New identifier of the custom emoji shown as the topic icon, if it was edited; an empty string if the icon was removed. */
    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,
)

/** This object represents a service message about a forum topic closed in the chat. Currently holds no information. */
@Serializable
public class ForumTopicClosed

/** This object represents a service message about a forum topic reopened in the chat. Currently holds no information. */
@Serializable
public class ForumTopicReopened

/** This object represents a service message about General forum topic hidden in the chat. Currently holds no information. */
@Serializable
public class GeneralForumTopicHidden

/** This object represents a service message about General forum topic unhidden in the chat. Currently holds no information. */
@Serializable
public class GeneralForumTopicUnhidden

/** This object represents a service message about a video chat scheduled in the chat. */
@OptIn(ExperimentalTime::class)
@Serializable
public data class VideoChatScheduled(
    /** Point in time (Unix timestamp) when the video chat is supposed to be started by a chat administrator. */
    @SerialName("start_date")
    @Serializable(with = InstantUnixSerializer::class)
    val startDate: kotlin.time.Instant,
)

/** This object represents a service message about a video chat started in the chat. Currently holds no information. */
@Serializable
public class VideoChatStarted

/** This object represents a service message about a video chat ended in the chat. */
@Serializable
public data class VideoChatEnded(
    /** Video chat duration in seconds. */
    val duration: Int,
)

/** This object represents a service message about new members invited to a video chat. */
@Serializable
public data class VideoChatParticipantsInvited(
    /** New members that were invited to the video chat. */
    val users: List<User>,
)

/** This object represents a service message about a change in auto-delete timer settings. */
@Serializable
public data class MessageAutoDeleteTimerChanged(
    /** New auto-delete time for messages in the chat; in seconds. */
    @SerialName("message_auto_delete_time")
    val messageAutoDeleteTime: Int,
)

/** This object represents a service message about a user allowing a bot to write messages after adding it to the attachment menu, launching a Web App from a link, or accepting an explicit request from a Web App sent by the method requestWriteAccess. */
@Serializable
public data class WriteAccessAllowed(
    /** True, if the access was granted after the user accepted an explicit request from a Web App sent by the method requestWriteAccess. */
    @SerialName("from_request")
    val fromRequest: Boolean? = null,

    /** Name of the Web App, if the access was granted when the Web App was launched from a link. */
    @SerialName("web_app_name")
    val webAppName: String? = null,

    /** True, if the access was granted when the bot was added to the attachment or side menu. */
    @SerialName("from_attachment_menu")
    val fromAttachmentMenu: Boolean? = null,
)

/** This object represents the content of a service message, sent whenever a user in the chat triggers a proximity alert set by another user. */
@Serializable
public data class ProximityAlertTriggered(
    /** User that triggered the alert. */
    val traveler: User,

    /** User that set the alert. */
    val watcher: User,

    /** The distance between the users. */
    val distance: Int,
)

/** This object describes an update about a user stopping message generation. */
@Serializable
public data class MessageGenerationStopped(
    /** Chat in which the message is generated. */
    val chat: Chat,

    /** Unique identifier of the message draft which was stopped. */
    @SerialName("draft_id")
    val draftId: Int,

    /** Unique identifier of the message thread in which the message is generated. */
    @SerialName("message_thread_id")
    val messageThreadId: Int? = null,
)

/** Describes a topic of a direct messages chat. */
@Serializable
public data class DirectMessagesTopic(
    /** Unique identifier of the topic. */
    @SerialName("topic_id")
    val topicId: Long,

    /** Information about the user that created the topic. */
    val user: User? = null,
)
