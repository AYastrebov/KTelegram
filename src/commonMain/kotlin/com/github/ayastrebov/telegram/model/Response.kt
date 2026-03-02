package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Wraps all Telegram Bot API responses.
 *
 * @param T The type of the result payload.
 * @property ok True if the request was successful.
 * @property result The result of the request (present when [ok] is true).
 * @property description Human-readable description of the error (present when [ok] is false).
 * @property errorCode Error code returned by Telegram (present when [ok] is false).
 */
@Serializable
data class Response<T>(
    val ok: Boolean,
    val result: T? = null,
    val description: String? = null,

    @SerialName("error_code")
    val errorCode: Int? = null,
)

/**
 * Exception thrown when a Telegram API request fails.
 *
 * @property errorCode The HTTP-like error code from Telegram.
 * @property description Human-readable error description.
 */
class TelegramApiException(
    val errorCode: Int,
    override val message: String,
) : Exception("Telegram API error $errorCode: $message")

/**
 * Returns the result if the response is successful, or throws [TelegramApiException].
 */
fun <T> Response<T>.getOrThrow(): T {
    if (ok && result != null) return result
    throw TelegramApiException(
        errorCode = errorCode ?: -1,
        message = description ?: "Unknown error",
    )
}

/**
 * Returns the result if the response is successful, or null otherwise.
 */
fun <T> Response<T>.getOrNull(): T? = if (ok) result else null

/**
 * True if this response represents an error.
 */
val Response<*>.isError: Boolean get() = !ok
