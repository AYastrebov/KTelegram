package com.github.ayastrebov.telegram

import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.client.request.*

/**
 * Creates a [MockEngine] that responds with the given JSON body.
 *
 * @param responseBody JSON string to return.
 * @param status HTTP status code (default 200).
 * @param assertRequest Optional block to assert properties of each request.
 */
fun createMockEngine(
    responseBody: String,
    status: HttpStatusCode = HttpStatusCode.OK,
    assertRequest: (HttpRequestData) -> Unit = {},
): MockEngine = MockEngine { request ->
    assertRequest(request)
    respond(
        content = responseBody,
        status = status,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )
}

/**
 * Creates a [Bot] backed by a [MockEngine] for testing.
 */
fun createTestBot(
    responseBody: String,
    status: HttpStatusCode = HttpStatusCode.OK,
    assertRequest: (HttpRequestData) -> Unit = {},
): Bot = BotImp(
    token = "test-token",
    engine = createMockEngine(responseBody, status, assertRequest),
)

// --- JSON fixtures ---

val USER_JSON = """
{
    "id": 123456,
    "is_bot": false,
    "first_name": "Test",
    "last_name": "User",
    "username": "testuser"
}
""".trimIndent()

val CHAT_JSON = """
{
    "id": -100123,
    "type": "supergroup",
    "title": "Test Group"
}
""".trimIndent()

val MESSAGE_JSON = """
{
    "message_id": 42,
    "from": $USER_JSON,
    "chat": $CHAT_JSON,
    "date": 1700000000,
    "text": "Hello, world!"
}
""".trimIndent()

val OK_TRUE_JSON = """{"ok": true, "result": true}"""
val OK_FALSE_RESULT_JSON = """{"ok": false, "error_code": 400, "description": "Bad Request: chat not found"}"""
