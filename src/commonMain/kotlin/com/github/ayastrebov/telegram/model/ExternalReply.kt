package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** This object contains information about the quoted part of a message that is replied to by the given message. */
@Serializable
public data class TextQuote(
    /** Text of the quoted part of a message that is replied to by the given message. */
    val text: String,

    /** Approximate quote position in the original message in UTF-16 code units as specified by the sender. */
    val position: Int,

    /** Special entities that appear in the quote. */
    val entities: List<MessageEntity>? = null,

    /** True, if the quote was chosen manually by the message sender. */
    @SerialName("is_manual")
    val isManual: Boolean? = null,
)

/** This object contains information about a message that is being replied to, which may come from another chat or forum topic. */
// Not yet modelled: paid_media, checklist, game, giveaway, giveaway_winners
@Serializable
public data class ExternalReplyInfo(
    /** Origin of the message replied to by the given message. */
    val origin: MessageOrigin,

    /** Chat the original message belongs to. */
    val chat: Chat? = null,

    /** Unique message identifier inside the original chat. */
    @SerialName("message_id")
    val messageId: Long? = null,

    /** Options used for link preview generation for the original message, if it is a text message. */
    @SerialName("link_preview_options")
    val linkPreviewOptions: LinkPreviewOptions? = null,

    /** Message is an animation, information about the animation. */
    val animation: Animation? = null,

    /** Message is an audio file, information about the file. */
    val audio: Audio? = null,

    /** Message is a general file, information about the file. */
    val document: Document? = null,

    /** Message is a live photo, information about the live photo. */
    @SerialName("live_photo")
    val livePhoto: LivePhoto? = null,

    /** Message is a photo, available sizes of the photo. */
    val photo: List<PhotoSize>? = null,

    /** Message is a sticker, information about the sticker. */
    val sticker: Sticker? = null,

    /** Message is a forwarded story. */
    val story: Story? = null,

    /** Message is a video, information about the video. */
    val video: Video? = null,

    /** Message is a video note, information about the video message. */
    @SerialName("video_note")
    val videoNote: VideoNote? = null,

    /** Message is a voice message, information about the file. */
    val voice: Voice? = null,

    /** True, if the message media is covered by a spoiler animation. */
    @SerialName("has_media_spoiler")
    val hasMediaSpoiler: Boolean? = null,

    /** Message is a shared contact, information about the contact. */
    val contact: Contact? = null,

    /** Message is a dice with random value. */
    val dice: Dice? = null,

    /** Message is an invoice for a payment, information about the invoice. */
    val invoice: Invoice? = null,

    /** Message is a shared location, information about the location. */
    val location: Location? = null,

    /** Message is a native poll, information about the poll. */
    val poll: Poll? = null,

    /** Message is a venue, information about the venue. */
    val venue: Venue? = null,
)
