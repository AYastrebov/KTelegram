package com.github.ayastrebov.telegram.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonObject

/** Base type for all reply markup options (inline keyboard, reply keyboard, remove, force reply). */
/**
 * Marker for the `reply_markup` parameter: one of [InlineKeyboardMarkup], [ReplyKeyboardMarkup],
 * [ReplyKeyboardRemove] or [ForceReply].
 *
 * Serialized by [KeyboardMarkupSerializer], which writes the concrete object as-is (Telegram
 * distinguishes the variants by their fields, not by a discriminator).
 */
@Serializable(with = KeyboardMarkupSerializer::class)
public sealed class KeyboardMarkup

/** Serializes [KeyboardMarkup] without a class discriminator and picks the variant by its distinguishing field. */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
public object KeyboardMarkupSerializer : KSerializer<KeyboardMarkup> {
    override val descriptor: SerialDescriptor =
        buildSerialDescriptor("KeyboardMarkup", PolymorphicKind.SEALED)

    override fun serialize(encoder: Encoder, value: KeyboardMarkup) {
        when (value) {
            is InlineKeyboardMarkup -> encoder.encodeSerializableValue(InlineKeyboardMarkup.serializer(), value)
            is ReplyKeyboardMarkup -> encoder.encodeSerializableValue(ReplyKeyboardMarkup.serializer(), value)
            is ReplyKeyboardRemove -> encoder.encodeSerializableValue(ReplyKeyboardRemove.serializer(), value)
            is ForceReply -> encoder.encodeSerializableValue(ForceReply.serializer(), value)
        }
    }

    override fun deserialize(decoder: Decoder): KeyboardMarkup {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("KeyboardMarkup can only be decoded from JSON")
        val element = jsonDecoder.decodeJsonElement().jsonObject
        val serializer = when {
            "inline_keyboard" in element -> InlineKeyboardMarkup.serializer()
            "keyboard" in element -> ReplyKeyboardMarkup.serializer()
            "remove_keyboard" in element -> ReplyKeyboardRemove.serializer()
            "force_reply" in element -> ForceReply.serializer()
            else -> throw SerializationException("Unknown KeyboardMarkup variant: $element")
        }
        return jsonDecoder.json.decodeFromJsonElement(serializer, element)
    }
}

/** Represents an inline keyboard that appears right next to the message it belongs to. */
@Serializable
public data class InlineKeyboardMarkup(
    @SerialName("inline_keyboard")
    val inlineKeyboard: List<List<InlineKeyboardButton>>,

    /** Pass True if the reply interface must be shown to the user, as if they had manually selected the bot's message and tapped 'Reply'. The value of the field can't be changed when the inline keyboard is edited. */
    @SerialName("force_reply")
    val forceReply: Boolean? = null,
) : KeyboardMarkup()

/** Represents one button of an inline keyboard. */
@Serializable
public data class InlineKeyboardButton(
    val text: String,
    val url: String? = null,

    @SerialName("callback_data")
    val callbackData: String? = null,

    @SerialName("switch_inline_query")
    val switchInlineQuery: String? = null,

    @SerialName("switch_inline_query_current_chat")
    val switchInlineQueryCurrentChat: String? = null,

    val pay: Boolean? = null,

    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,

    val style: String? = null,

    /** Description of the Web App that will be launched when the user presses the button. */
    @SerialName("web_app")
    val webApp: WebAppInfo? = null,

    /** An HTTPS URL used to automatically authorize the user. */
    @SerialName("login_url")
    val loginUrl: LoginUrl? = null,

    /** If set, pressing the button will prompt the user to select one of their chats of the specified type, open that chat and insert the bot's username and the specified inline query in the input field. */
    @SerialName("switch_inline_query_chosen_chat")
    val switchInlineQueryChosenChat: SwitchInlineQueryChosenChat? = null,

    /** Description of the button that copies the specified text to the clipboard. */
    @SerialName("copy_text")
    val copyText: CopyTextButton? = null,

    /** Description of the game that will be launched when the user presses the button.NOTE: This type of button must always be the first button in the first row. */
    @SerialName("callback_game")
    val callbackGame: CallbackGame? = null,
)
