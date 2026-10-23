package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Describes actions that a non-administrator user is allowed to take in a chat.
 */
@Serializable
public data class ChatPermissions(
    @SerialName("can_send_messages")
    val canSendMessages: Boolean? = null,

    @Deprecated("Split into per-media permissions in Bot API 6.5")
    @SerialName("can_send_media_messages")
    val canSendMediaMessages: Boolean? = null,

    @SerialName("can_send_polls")
    val canSendPolls: Boolean? = null,

    @SerialName("can_send_other_messages")
    val canSendOtherMessages: Boolean? = null,

    @SerialName("can_add_web_page_previews")
    val canAddWebPagePreviews: Boolean? = null,

    @SerialName("can_change_info")
    val canChangeInfo: Boolean? = null,

    @SerialName("can_invite_users")
    val canInviteUsers: Boolean? = null,

    @SerialName("can_pin_messages")
    val canPinMessages: Boolean? = null,

    @SerialName("can_edit_tag")
    val canEditTag: Boolean? = null,

    /** True, if the user is allowed to send audios. */
    @SerialName("can_send_audios")
    val canSendAudios: Boolean? = null,

    /** True, if the user is allowed to send documents. */
    @SerialName("can_send_documents")
    val canSendDocuments: Boolean? = null,

    /** True, if the user is allowed to send photos. */
    @SerialName("can_send_photos")
    val canSendPhotos: Boolean? = null,

    /** True, if the user is allowed to send videos. */
    @SerialName("can_send_videos")
    val canSendVideos: Boolean? = null,

    /** True, if the user is allowed to send video notes. */
    @SerialName("can_send_video_notes")
    val canSendVideoNotes: Boolean? = null,

    /** True, if the user is allowed to send voice notes. */
    @SerialName("can_send_voice_notes")
    val canSendVoiceNotes: Boolean? = null,

    /** True, if the user is allowed to react to messages. */
    @SerialName("can_react_to_messages")
    val canReactToMessages: Boolean? = null,

    /** True, if the user is allowed to create forum topics. */
    @SerialName("can_manage_topics")
    val canManageTopics: Boolean? = null,
)
