package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Formatting options for message text. */
@Serializable
public enum class ParseMode {
    /** MarkdownV2 style (recommended). */
    @SerialName("MarkdownV2")
    MarkdownV2,

    /** HTML style. */
    @SerialName("HTML")
    HTML,

    /** Legacy Markdown style. */
    @SerialName("Markdown")
    Markdown,
}

/** Type of action to broadcast via [sendChatAction][com.github.ayastrebov.telegram.Bot.sendChatAction]. */
@Serializable
public enum class ChatAction {
    @SerialName("typing")
    Typing,

    @SerialName("upload_photo")
    UploadPhoto,

    @SerialName("record_video")
    RecordVideo,

    @SerialName("upload_video")
    UploadVideo,

    @SerialName("record_voice")
    RecordVoice,

    @SerialName("upload_voice")
    UploadVoice,

    @SerialName("upload_document")
    UploadDocument,

    @SerialName("find_location")
    FindLocation,

    @SerialName("record_video_note")
    RecordVideoNote,

    @SerialName("upload_video_note")
    UploadVideoNote,
}
