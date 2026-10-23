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

/**
 * Content of a message to be sent as a result of an inline query.
 *
 * Serialized by [InputMessageContentSerializer], which writes the concrete object as-is
 * (Telegram distinguishes the variants by their fields, not by a discriminator).
 */
@Serializable(with = InputMessageContentSerializer::class)
public sealed class InputMessageContent {
    /** Represents the content of a text message to be sent as the result of an inline query. */
    @Serializable
    public class InputTextMessageContent(
        @SerialName("message_text")
        public val messageText: String,

        @SerialName("parse_mode")
        public val parseMode: ParseMode? = null,

        @SerialName("entities")
        public val entities: List<MessageEntity>? = null,

        /** Link preview generation options for the message. */
        @SerialName("link_preview_options")
        public val linkPreviewOptions: LinkPreviewOptions? = null,
    ) : InputMessageContent()
}

/** Serializes [InputMessageContent] without a class discriminator. */
@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
public object InputMessageContentSerializer : KSerializer<InputMessageContent> {
    override val descriptor: SerialDescriptor =
        buildSerialDescriptor("InputMessageContent", PolymorphicKind.SEALED)

    override fun serialize(encoder: Encoder, value: InputMessageContent) {
        when (value) {
            is InputMessageContent.InputTextMessageContent ->
                encoder.encodeSerializableValue(InputMessageContent.InputTextMessageContent.serializer(), value)
        }
    }

    override fun deserialize(decoder: Decoder): InputMessageContent {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("InputMessageContent can only be decoded from JSON")
        val element = jsonDecoder.decodeJsonElement().jsonObject
        val serializer = when {
            "message_text" in element -> InputMessageContent.InputTextMessageContent.serializer()
            else -> throw SerializationException("Unknown InputMessageContent variant: $element")
        }
        return jsonDecoder.json.decodeFromJsonElement(serializer, element)
    }
}
