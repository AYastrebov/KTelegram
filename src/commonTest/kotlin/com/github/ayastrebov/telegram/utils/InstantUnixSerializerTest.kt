package com.github.ayastrebov.telegram.utils

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class InstantUnixSerializerTest {

    @Serializable
    private data class Wrapper(
        @Serializable(with = InstantUnixSerializer::class)
        val timestamp: kotlin.time.Instant,
    )

    private val json = Json

    @Test
    fun deserializeEpochToInstant() {
        val input = """{"timestamp": 1700000000}"""
        val wrapper = json.decodeFromString<Wrapper>(input)
        assertEquals(1700000000L, wrapper.timestamp.epochSeconds)
    }

    @Test
    fun serializeInstantToEpoch() {
        val wrapper = Wrapper(timestamp = kotlin.time.Instant.fromEpochSeconds(1700000000))
        val output = json.encodeToString(Wrapper.serializer(), wrapper)
        assertTrue(output.contains("1700000000"))
    }

    @Test
    fun roundTrip() {
        val original = Wrapper(timestamp = kotlin.time.Instant.fromEpochSeconds(1234567890))
        val encoded = json.encodeToString(Wrapper.serializer(), original)
        val decoded = json.decodeFromString<Wrapper>(encoded)
        assertEquals(original.timestamp, decoded.timestamp)
    }
}
