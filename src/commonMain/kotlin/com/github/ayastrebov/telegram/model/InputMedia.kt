package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents the content of a media message to be sent. */
@Serializable
public sealed class InputMedia {
    public abstract val type: String
    public abstract val media: String
    public abstract val caption: String?
    public abstract val parseMode: ParseMode?
}

/** Represents a photo to be sent as part of a media group. */
@Serializable
@SerialName("photo")
public data class InputMediaPhoto(
    override val media: String,
    override val caption: String? = null,

    @SerialName("parse_mode")
    override val parseMode: ParseMode? = null,

    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,
) : InputMedia() {
    override val type: String = "photo"
}

/** Represents a video to be sent as part of a media group. */
@Serializable
@SerialName("video")
public data class InputMediaVideo(
    override val media: String,
    override val caption: String? = null,

    @SerialName("parse_mode")
    override val parseMode: ParseMode? = null,

    val width: Int? = null,
    val height: Int? = null,
    val duration: Int? = null,

    @SerialName("supports_streaming")
    val supportsStreaming: Boolean? = null,

    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,
) : InputMedia() {
    override val type: String = "video"
}

/** Represents a document to be sent as part of a media group. */
@Serializable
@SerialName("document")
public data class InputMediaDocument(
    override val media: String,
    override val caption: String? = null,

    @SerialName("parse_mode")
    override val parseMode: ParseMode? = null,
) : InputMedia() {
    override val type: String = "document"
}

/** Represents an audio file to be sent as part of a media group. */
@Serializable
@SerialName("audio")
public data class InputMediaAudio(
    override val media: String,
    override val caption: String? = null,

    @SerialName("parse_mode")
    override val parseMode: ParseMode? = null,

    val duration: Int? = null,
    val performer: String? = null,
    val title: String? = null,
) : InputMedia() {
    override val type: String = "audio"
}
