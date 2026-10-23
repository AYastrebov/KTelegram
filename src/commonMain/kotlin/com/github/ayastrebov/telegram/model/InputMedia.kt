package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents the content of a media message to be sent. */
@Serializable
public sealed class InputMedia {
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

    /** List of special entities that appear in the caption, which can be specified instead of parse_mode. */
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    /** Pass True if the caption must be shown above the message media. */
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
) : InputMedia()

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

    /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. */
    val thumbnail: String? = null,

    /** Cover for the video in the message. */
    val cover: String? = null,

    /** Start timestamp for the video in the message. */
    @SerialName("start_timestamp")
    val startTimestamp: Int? = null,

    /** List of special entities that appear in the caption, which can be specified instead of parse_mode. */
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    /** Pass True if the caption must be shown above the message media. */
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,
) : InputMedia()

/** Represents a document to be sent as part of a media group. */
@Serializable
@SerialName("document")
public data class InputMediaDocument(
    override val media: String,
    override val caption: String? = null,

    @SerialName("parse_mode")
    override val parseMode: ParseMode? = null,

    /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. */
    val thumbnail: String? = null,

    /** List of special entities that appear in the caption, which can be specified instead of parse_mode. */
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    /** Disables automatic server-side content type detection for files uploaded using multipart/form-data. */
    @SerialName("disable_content_type_detection")
    val disableContentTypeDetection: Boolean? = null,
) : InputMedia()

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

    /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. */
    val thumbnail: String? = null,

    /** List of special entities that appear in the caption, which can be specified instead of parse_mode. */
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,
) : InputMedia()

/** Represents an animation file (GIF or H.264/MPEG-4 AVC video without sound) to be sent. */
@Serializable
@SerialName("animation")
public data class InputMediaAnimation(
    override val media: String,
    override val caption: String? = null,

    @SerialName("parse_mode")
    override val parseMode: ParseMode? = null,

    /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. */
    val thumbnail: String? = null,

    /** List of special entities that appear in the caption, which can be specified instead of parse_mode. */
    @SerialName("caption_entities")
    val captionEntities: List<MessageEntity>? = null,

    /** Pass True if the caption must be shown above the message media. */
    @SerialName("show_caption_above_media")
    val showCaptionAboveMedia: Boolean? = null,

    val width: Int? = null,
    val height: Int? = null,
    val duration: Int? = null,

    /** Pass True if the animation needs to be covered with a spoiler animation. */
    @SerialName("has_spoiler")
    val hasSpoiler: Boolean? = null,
) : InputMedia()
