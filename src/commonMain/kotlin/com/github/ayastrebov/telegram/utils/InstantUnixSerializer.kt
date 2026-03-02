package com.github.ayastrebov.telegram.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.ExperimentalTime

/**
 * Serializer that converts between Unix epoch seconds (Long) and [kotlin.time.Instant].
 *
 * Used for all timestamp fields in Telegram API responses (e.g., message dates).
 */
@OptIn(ExperimentalTime::class)
object InstantUnixSerializer : KSerializer<kotlin.time.Instant> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Instant", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): kotlin.time.Instant =
        kotlin.time.Instant.fromEpochSeconds(decoder.decodeLong())

    override fun serialize(encoder: Encoder, value: kotlin.time.Instant) {
        encoder.encodeLong(value.epochSeconds)
    }
}
