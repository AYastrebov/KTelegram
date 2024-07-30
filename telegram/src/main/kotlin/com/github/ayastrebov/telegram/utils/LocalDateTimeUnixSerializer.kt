package com.github.ayastrebov.telegram.utils

import kotlinx.datetime.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object LocalDateTimeUnixSerializer : KSerializer<LocalDateTime> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): LocalDateTime {
        val timezone = TimeZone.of("Europe/Moscow")
        val instantTime = Instant.fromEpochSeconds(decoder.decodeLong())
        return instantTime.toLocalDateTime(timezone)
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val timezone = TimeZone.of("Europe/Moscow")
        val seconds = value.toInstant(timezone).epochSeconds
        encoder.encodeLong(seconds)
    }
}