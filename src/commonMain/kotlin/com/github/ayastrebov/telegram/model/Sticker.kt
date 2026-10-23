package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a sticker.
 */
@Serializable
public data class Sticker(
    @SerialName("file_id")
    val fileId: String,

    @SerialName("file_unique_id")
    val fileUniqueId: String,

    val width: Int,
    val height: Int,

    @SerialName("is_animated")
    val isAnimated: Boolean = false,

    @SerialName("is_video")
    val isVideo: Boolean = false,

    /** Type of the sticker: "regular", "mask" or "custom_emoji". */
    val type: String? = null,

    val emoji: String? = null,
    val thumbnail: PhotoSize? = null,

    @SerialName("set_name")
    val setName: String? = null,

    @SerialName("file_size")
    val fileSize: Long? = null,

    /** For premium regular stickers, premium animation for the sticker. */
    @SerialName("premium_animation")
    val premiumAnimation: File? = null,

    /** For mask stickers, the position where the mask should be placed. */
    @SerialName("mask_position")
    val maskPosition: MaskPosition? = null,

    /** For custom emoji stickers, unique identifier of the custom emoji. */
    @SerialName("custom_emoji_id")
    val customEmojiId: String? = null,

    /** True, if the sticker must be repainted to a text color in messages, the color of the Telegram Premium badge in emoji status, white color on chat photos, or another appropriate color in other places. */
    @SerialName("needs_repainting")
    val needsRepainting: Boolean? = null,
)
