---
name: ktelegram
description: >
  Expert skill for developing the KTelegram library — a Kotlin Multiplatform Telegram Bot API client.
  Use this skill whenever working on /Users/Andrey.Yastrebov/IdeaProjects/KTelegram or when the user
  mentions KTelegram, Telegram Bot API types, Telegram bot methods, or wants to add/update Telegram
  API coverage. Also trigger when the user says "add sendX method", "new Telegram type", "update
  Bot API", "telegram model", or references the Telegram Bot API changelog. Covers: adding new API
  types and methods, fixing bugs, writing tests, maintaining serialization consistency, updating
  documentation, and releasing new versions.
---

# KTelegram Development Guide

You are working on KTelegram, a Kotlin Multiplatform library wrapping the Telegram Bot API. The project lives at `/Users/Andrey.Yastrebov/IdeaProjects/KTelegram`.

## Project Architecture

```
src/commonMain/kotlin/com/github/ayastrebov/telegram/
├── Bot.kt                  # Bot interface + BotImp implementation + TelegramBot factory
├── HandlersContainer.kt    # Handler interface + chain-of-responsibility container
├── handler/                # Specialized update handlers (Command, Message, Callback, etc.)
├── model/                  # @Serializable data classes mapping to Telegram API types
├── request/                # Request DTOs grouped by category
└── utils/                  # InstantUnixSerializer
```

Tests mirror the structure under `src/commonTest/kotlin/`.

## How to Add a New Telegram Bot API Type

This is the most common task. Follow this exact pattern:

### 1. Create the model class

Place it in `model/`. Every type must:
- Be `@Serializable`
- Have `public` visibility (the project uses `explicitApi()`)
- Use `@SerialName` for snake_case JSON field mapping
- Have a KDoc comment describing what the type represents
- Use `Long` for IDs, `Int` for counts/sizes, `String` for string fields
- Make optional fields nullable with `= null` default

**Example** (this is the actual pattern used throughout the codebase):

```kotlin
package com.github.ayastrebov.telegram.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents a forum topic. */
@Serializable
public data class ForumTopic(
    @SerialName("message_thread_id")
    val messageThreadId: Int,

    val name: String,

    @SerialName("icon_color")
    val iconColor: Int,

    @SerialName("icon_custom_emoji_id")
    val iconCustomEmojiId: String? = null,
)
```

### 2. Wire it into parent types

If the type appears as a field on `Message`, `Update`, or another existing type, add the field there. Required fields go before optional ones in parameter order, but new optional fields go at the end to maintain binary compatibility.

### 3. Marker types (no fields)

For service message types that hold no data (like `ChatOwnerLeft`), use a plain class — not a data class:

```kotlin
@Serializable
public class ChatOwnerLeft
```

### 4. Sealed hierarchies

When the API has a "one of" type (like `InputMedia`), use a sealed class with `@SerialName` discriminator on subclasses:

```kotlin
@Serializable
public sealed class InputMedia {
    public abstract val type: String
    public abstract val media: String
}

@Serializable
@SerialName("photo")
public data class InputMediaPhoto(
    override val media: String,
    // ... fields specific to photos
) : InputMedia() {
    override val type: String = "photo"
}
```

### 5. Custom serializers

Only needed for special cases (like `EditMessageResult` which can be either a `Message` or `true`). Use `JsonDecoder`/`JsonEncoder` pattern. See `EditMessageResult.kt` for the canonical example.

For Unix timestamps, use the existing `InstantUnixSerializer`:
```kotlin
@Serializable(with = InstantUnixSerializer::class)
val date: kotlin.time.Instant
```

## How to Add a New Bot API Method

Every method needs three things: Bot interface declaration, BotImp implementation, and a request DTO.

### 1. Create the request DTO

Place it in the appropriate `request/` file by category:
- `MessageRequests.kt` — send*, forward*, copy*, delete*, getFile, sendChatAction
- `ChatRequests.kt` — chat management, member management, invite links
- `EditRequests.kt` — editMessage*, deleteMessage
- `PollRequests.kt` — sendPoll
- `ManagedBotRequests.kt` — managed bot operations
- `CommandRequests.kt` — setMyCommands
- `UpdateRequests.kt` — getUpdates, webhooks
- `InlineRequests.kt` — answerInlineQuery, answerCallbackQuery

The request DTO pattern:

```kotlin
/**
 * Parameters for [methodName][com.github.ayastrebov.telegram.Bot.methodName].
 */
@Serializable
public data class MethodNameRequest(
    @SerialName("chat_id")
    val chatId: String,          // Required params first, no default

    @SerialName("user_id")
    val userId: Long,            // Required params, no default

    @SerialName("optional_field")
    val optionalField: String? = null,  // Optional params last, = null
)
```

Key rules for request DTOs:
- `chatId` is always `String` (supports both numeric IDs and @usernames)
- `userId` is always `Long`
- `messageId` is always `Long`
- Include `replyParameters: ReplyParameters? = null` and `replyMarkup: KeyboardMarkup? = null` as the last two fields on send methods
- Import model types explicitly (not wildcard) in request files

### 2. Add to Bot interface

Add a suspend function in the appropriate section (Updates, Messages, Editing, Chat, etc.):

```kotlin
/** Short description of what this method does. */
public suspend fun methodName(params: MethodNameRequest): Response<ReturnType>
```

Return types follow Telegram API:
- Methods returning a message → `Response<Message>`
- Methods returning true/false → `Response<Boolean>`
- Methods returning a string → `Response<String>`
- Methods returning arrays → `Response<List<T>>`

### 3. Add implementation in BotImp

In the same section as the interface method:

```kotlin
override suspend fun methodName(params: MethodNameRequest): Response<ReturnType> =
    client.post("methodName") { setBody(params) }.body()
```

The string passed to `client.post()` must exactly match the Telegram Bot API method name (camelCase).

For methods with no request body (like `getMe`, `removeMyProfilePhoto`):
```kotlin
override suspend fun removeMyProfilePhoto(): Response<Boolean> =
    client.post("removeMyProfilePhoto").body()
```

## How to Write Tests

Two types of tests:

### Serialization tests (`model/` tests)

Test that JSON from Telegram API deserializes correctly:

```kotlin
@Test
fun deserializeTypeName() {
    val input = """
    {
        "field_name": "value",
        "optional_field": 42
    }
    """.trimIndent()

    val result = json.decodeFromString<TypeName>(input)
    assertEquals("value", result.fieldName)
    assertEquals(42, result.optionalField)
}
```

Always test:
- Required fields are parsed correctly
- Optional fields work when present and when absent
- Nested types deserialize
- The `ignoreUnknownKeys` behavior (future fields don't break parsing)

### Bot method tests

Test that the method hits the correct endpoint with POST:

```kotlin
@Test
fun methodNameUsesPost() = runTestBot(
    response = """{"ok": true, "result": ...}""",
    assertRequest = {
        assertEquals(HttpMethod.Post, it.method)
        assertTrue(it.url.encodedPath.endsWith("methodName"))
    },
) { bot ->
    val result = bot.methodName(MethodNameRequest(...)).getOrThrow()
    // Assert on the result
}
```

Use `runTestBot` helper (defined in each test class) with `createTestBot` from `TestUtils.kt`. Use `MESSAGE_JSON`, `USER_JSON`, `CHAT_JSON`, `OK_TRUE_JSON` fixtures for mock responses.

## Build Verification Checklist

After any change, run these in order:

1. `./gradlew jvmTest` — Compile and run tests
2. `./gradlew apiDump` — Update binary compatibility API dump (commit the `api/` changes)
3. `./gradlew dokkaGenerate` — Verify docs generate without warnings (`failOnWarning` is enabled)

If adding Android-specific changes, ensure `local.properties` has `sdk.dir` pointing to the Android SDK.

## Field Type Mapping (Telegram API → Kotlin)

| Telegram Type | Kotlin Type |
|--------------|-------------|
| Integer (ID) | `Long` |
| Integer (count/size/offset) | `Int` |
| Float | `Double` |
| String | `String` |
| Boolean | `Boolean` |
| True | `Boolean` |
| Integer (Unix timestamp) | `kotlin.time.Instant` with `@Serializable(with = InstantUnixSerializer::class)` |
| Array of X | `List<X>` |

## Common Pitfalls

- **Forgetting `public`**: The project uses `explicitApi()` — every public declaration needs an explicit `public` modifier and explicit return types on public functions.
- **Forgetting `@SerialName`**: Any field whose JSON name differs from the Kotlin property name needs `@SerialName("snake_case_name")`. If the Kotlin name is just the camelCase version, you still need it.
- **Breaking binary compatibility**: Always add new optional fields at the end of data classes with `= null`. Run `apiDump` after changes. Never remove or reorder existing fields.
- **Wrong ID type**: Message/chat/user IDs are `Long`, not `Int`. Thread IDs and counts are `Int`.
- **Commit sequence**: When adding new types/methods, commit in this order: model types first, then request DTOs + Bot methods, then tests. Each as a separate commit with a descriptive message.

## Releasing

1. Update `README.md` version number in the dependency snippet
2. Commit and push to master
3. `git tag v<version> && git push origin v<version>`
4. The publish workflow handles: tests → GitHub Packages → GitHub Release → Dokka → GitHub Pages

Version is extracted from the tag (`v0.5.0` → `0.5.0`) via `-PKtelegramDeployVersion`. GitHub Packages does not allow re-publishing the same version — if a publish partially fails, bump to a patch version.

## Fetching Telegram Bot API Changes

To check what's new in the Telegram Bot API:

1. Fetch `https://core.telegram.org/bots/api` and look at the "Recent changes" section
2. For exact field definitions, use curl to extract specific type/method sections:
   ```bash
   curl -s https://core.telegram.org/bots/api | sed -n '/<a class="anchor" name="typename"/,/<h4/p' | sed 's/<[^>]*>//g' | sed '/^$/d'
   ```
3. Compare against existing model classes to identify gaps
