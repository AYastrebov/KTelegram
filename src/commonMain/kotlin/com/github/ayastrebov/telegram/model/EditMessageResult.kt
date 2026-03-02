package com.github.ayastrebov.telegram.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.decodeFromJsonElement

/**
 * Result payload for edit methods that may return either the edited [Message] or boolean `true`.
 */
@Serializable(with = EditMessageResultSerializer::class)
sealed interface EditMessageResult {
    data class MessageResult(val message: Message) : EditMessageResult
    data object TrueResult : EditMessageResult
}

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
object EditMessageResultSerializer : KSerializer<EditMessageResult> {
    override val descriptor: SerialDescriptor =
        buildSerialDescriptor("EditMessageResult", PolymorphicKind.SEALED)

    override fun serialize(encoder: Encoder, value: EditMessageResult) {
        require(encoder is JsonEncoder) { "EditMessageResultSerializer works only with JSON" }
        when (value) {
            is EditMessageResult.MessageResult -> encoder.encodeSerializableValue(Message.serializer(), value.message)
            EditMessageResult.TrueResult -> encoder.encodeJsonElement(JsonPrimitive(true))
        }
    }

    override fun deserialize(decoder: Decoder): EditMessageResult {
        require(decoder is JsonDecoder) { "EditMessageResultSerializer works only with JSON" }
        val element: JsonElement = decoder.decodeJsonElement()
        val primitive = element as? JsonPrimitive
        if (primitive?.booleanOrNull == true) return EditMessageResult.TrueResult
        return EditMessageResult.MessageResult(decoder.json.decodeFromJsonElement(Message.serializer(), element))
    }
}
