---
name: ktelegram-usage
description: >
  Guide for integrating and using the KTelegram library in Kotlin projects. Use this skill whenever
  someone asks how to add KTelegram as a dependency, set up a Telegram bot, handle updates, process
  commands, use inline keyboards, send messages/photos/polls, configure webhooks, or write bot logic.
  Trigger on: "create a telegram bot", "set up ktelegram", "add telegram dependency", "how to use
  ktelegram", "telegram bot example", "handle telegram updates", "send telegram message", "telegram
  webhook setup", "telegram inline keyboard", "telegram command handler", "poll for updates",
  "callback query", or any question about building a bot with this library.
---

# Using KTelegram

KTelegram is a Kotlin Multiplatform library for the Telegram Bot API. It runs on Android, JVM, iOS, macOS, Linux, Windows, JS, and WebAssembly.

## Installation

### 1. Add the GitHub Packages repository

KTelegram is published to GitHub Packages, which requires authentication even for public packages. Add the repository to your `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/ayastrebov/ktelegram")
        credentials {
            username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
            password = providers.gradleProperty("gpr.token").orNull ?: System.getenv("GITHUB_TOKEN")
        }
    }
}
```

For local development, set credentials in `~/.gradle/gradle.properties`:
```properties
gpr.user=your-github-username
gpr.token=ghp_your-personal-access-token
```

The token needs the `read:packages` scope. Generate one at GitHub Settings > Developer settings > Personal access tokens.

### 2. Add the dependency

```kotlin
dependencies {
    implementation("com.github.ayastrebov.telegram:telegram-api-client:0.5.0")
}
```

For Kotlin Multiplatform projects, add it to `commonMain`:
```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("com.github.ayastrebov.telegram:telegram-api-client:0.5.0")
        }
    }
}
```

## Creating a Bot

### Get a bot token

Message [@BotFather](https://t.me/BotFather) on Telegram and use `/newbot` to create a bot. You'll receive a token like `123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11`.

### Initialize the client

```kotlin
import com.github.ayastrebov.telegram.TelegramBot
import com.github.ayastrebov.telegram.model.getOrThrow

val bot = TelegramBot("YOUR_BOT_TOKEN")

// Verify the connection
val me = bot.getMe().getOrThrow()
println("Bot: ${me.firstName} (@${me.username})")
```

The `TelegramBot` factory accepts an optional Ktor `HttpClientEngine` (useful for testing) and a configuration block for the underlying `HttpClient`.

Always call `bot.close()` when done to release HTTP resources.

## Receiving Updates

Two approaches: long polling (simpler) and webhooks (more efficient for production).

### Long Polling

Poll Telegram's servers in a loop. Good for development and simple bots:

```kotlin
import com.github.ayastrebov.telegram.request.GetUpdatesRequest

val bot = TelegramBot("YOUR_BOT_TOKEN")
var offset = 0L

while (true) {
    val updates = bot.getUpdates(
        GetUpdatesRequest(offset = offset, timeout = 30)
    ).getOrThrow()

    for (update in updates) {
        offset = update.updateId + 1
        // Process each update
        println("Got update: ${update.updateId}")
    }
}
```

The `timeout` parameter enables long polling — the server holds the connection open for up to 30 seconds, returning immediately when updates arrive. This is much more efficient than rapid polling.

### Webhooks

For production, register a webhook so Telegram pushes updates to your server:

```kotlin
import com.github.ayastrebov.telegram.request.SetWebhookRequest
import com.github.ayastrebov.telegram.request.DeleteWebhookRequest

// Register webhook (HTTPS required)
bot.setWebhook(SetWebhookRequest(
    url = "https://your-server.com/telegram/webhook"
)).getOrThrow()

// In your HTTP server handler, deserialize the incoming JSON
val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
val update = json.decodeFromString<Update>(requestBody)

// Process the update
container.processUpdate(update)

// When shutting down
bot.deleteWebhook(DeleteWebhookRequest()).getOrThrow()
bot.close()
```

Webhook mode and `getUpdates` are mutually exclusive — Telegram won't send updates via polling while a webhook is set.

## Handler System

KTelegram provides a chain-of-responsibility handler system. Register handlers in priority order; the first handler returning `true` stops the chain.

### Setting up handlers

```kotlin
import com.github.ayastrebov.telegram.HandlersContainer

val container = HandlersContainer()
container.registerHandlers {
    register(commandHandler)      // Highest priority — slash commands
    register(callbackHandler)     // Inline keyboard callbacks
    register(messageHandler)      // Text message patterns (catch-all last)
}

// In your update loop:
container.processUpdate(update)
```

### Command Handler

Responds to `/command` messages:

```kotlin
import com.github.ayastrebov.telegram.handler.CommandHandler

val commandHandler = CommandHandler(botName = "my_bot")
commandHandler.registerCommands {
    register("start") { update ->
        bot.sendMessage(SendMessageRequest(
            chatId = update.message!!.chat.id.toString(),
            text = "Welcome! I'm a bot built with KTelegram."
        ))
    }
    register("help") { update ->
        bot.sendMessage(SendMessageRequest(
            chatId = update.message!!.chat.id.toString(),
            text = """
                Available commands:
                /start - Start the bot
                /help - Show this help
                /status - Check status
            """.trimIndent()
        ))
    }
}
```

The `botName` parameter ensures the handler only responds to commands directed at your bot in group chats (e.g., `/help@my_bot`).

### Message Handler

Responds to messages matching a filter:

```kotlin
import com.github.ayastrebov.telegram.handler.MessageHandler

val messageHandler = MessageHandler()
messageHandler.registerActions {
    register(
        filter = { it.contains("hello", ignoreCase = true) },
        action = { update ->
            bot.sendMessage(SendMessageRequest(
                chatId = update.message!!.chat.id.toString(),
                text = "Hello! How can I help?"
            ))
            true  // Handled — stop the chain
        }
    )
}
```

### Callback Query Handler (Inline Keyboards)

Handle button presses from inline keyboards:

```kotlin
import com.github.ayastrebov.telegram.handler.CallbackQueryHandler

val callbackHandler = CallbackQueryHandler()
callbackHandler.registerCallbacks {
    // Exact match
    onData("confirm") { query ->
        bot.answerCallbackQuery(AnswerCallbackQueryRequest(
            callbackQueryId = query.id,
            text = "Confirmed!"
        ))
    }
    // Prefix match — useful for pagination, dynamic data
    onPrefix("page_") { query ->
        val pageNum = query.data?.removePrefix("page_")?.toIntOrNull() ?: 1
        // Load and display the requested page
        bot.answerCallbackQuery(AnswerCallbackQueryRequest(
            callbackQueryId = query.id
        ))
    }
}
```

### Other Handlers

- **`PhotoHandler`** — Processes messages containing photos
- **`InlineQueryHandler`** — Responds to inline queries (when users type `@your_bot query`)
- **`EditedMessageHandler`** — Handles edited messages
- **`NewMemberHandler`** — Welcomes new chat members
- **`ServiceHandler`** — Handles service messages (restricted to specific chat IDs)

## Sending Messages

### Text messages

```kotlin
import com.github.ayastrebov.telegram.request.SendMessageRequest
import com.github.ayastrebov.telegram.model.ParseMode

bot.sendMessage(SendMessageRequest(
    chatId = "123456",
    text = "Hello <b>bold</b> and <i>italic</i>!",
    parseMode = ParseMode.HTML,
))
```

### Photos, documents, and media

```kotlin
// By file_id (if you already have one) or URL
bot.sendPhoto(SendPhotoRequest(
    chatId = "123456",
    photo = "https://example.com/image.jpg",
    caption = "Check this out!"
))

// Media group (album)
bot.sendMediaGroup(SendMediaGroupRequest(
    chatId = "123456",
    media = listOf(
        InputMediaPhoto(media = "file_id_1", caption = "First photo"),
        InputMediaPhoto(media = "file_id_2"),
        InputMediaVideo(media = "file_id_3", caption = "And a video"),
    )
))
```

### Polls

```kotlin
bot.sendPoll(SendPollRequest(
    chatId = "123456",
    question = "What's your favorite language?",
    options = listOf(
        InputPollOption(text = "Kotlin"),
        InputPollOption(text = "Swift"),
        InputPollOption(text = "Rust"),
    ),
    isAnonymous = false,
    allowsMultipleAnswers = true,
))
```

### Inline keyboards

```kotlin
bot.sendMessage(SendMessageRequest(
    chatId = "123456",
    text = "Choose an option:",
    replyMarkup = InlineKeyboardMarkup(
        inlineKeyboard = listOf(
            listOf(
                InlineKeyboardButton(text = "Option A", callbackData = "choice_a"),
                InlineKeyboardButton(text = "Option B", callbackData = "choice_b"),
            ),
            listOf(
                InlineKeyboardButton(text = "Visit Website", url = "https://example.com"),
            ),
        )
    ),
))
```

### Reply keyboards

```kotlin
bot.sendMessage(SendMessageRequest(
    chatId = "123456",
    text = "Share your location or contact:",
    replyMarkup = ReplyKeyboardMarkup(
        keyboard = listOf(
            listOf(
                KeyboardButton(text = "Share Location", requestLocation = true),
                KeyboardButton(text = "Share Contact", requestContact = true),
            ),
        ),
        resizeKeyboard = true,
        oneTimeKeyboard = true,
    ),
))
```

### Replying to messages

Use `ReplyParameters` for rich reply semantics:

```kotlin
bot.sendMessage(SendMessageRequest(
    chatId = "123456",
    text = "This is a reply!",
    replyParameters = ReplyParameters(
        messageId = originalMessage.messageId,
    ),
))
```

## Error Handling

All API methods return `Response<T>`. Three ways to handle it:

```kotlin
// 1. Throw on error (when you want to fail fast)
val user = bot.getMe().getOrThrow()

// 2. Null on error (when you want to handle gracefully)
val user = bot.getMe().getOrNull()
if (user != null) {
    println("Connected as ${user.firstName}")
}

// 3. Check the response explicitly
val response = bot.sendMessage(request)
if (response.isError) {
    println("Error ${response.errorCode}: ${response.description}")
} else {
    println("Sent message ${response.result?.messageId}")
}
```

`TelegramApiException` is thrown by `getOrThrow()` and includes `errorCode` and `message` from the Telegram API.

## Testing Your Bot

KTelegram accepts a Ktor `MockEngine` for testing without hitting the real API:

```kotlin
import io.ktor.client.engine.mock.*
import io.ktor.http.*

val mockEngine = MockEngine { request ->
    respond(
        content = """{"ok": true, "result": {"id": 1, "is_bot": true, "first_name": "TestBot"}}""",
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )
}

val bot = TelegramBot("test-token", engine = mockEngine)
val me = bot.getMe().getOrThrow()
assertEquals("TestBot", me.firstName)
```

## Complete Bot Example

A minimal echo bot that replies to every text message:

```kotlin
import com.github.ayastrebov.telegram.*
import com.github.ayastrebov.telegram.model.*
import com.github.ayastrebov.telegram.request.*
import com.github.ayastrebov.telegram.handler.*

suspend fun main() {
    val bot = TelegramBot("YOUR_BOT_TOKEN")
    val container = HandlersContainer()

    val commandHandler = CommandHandler(botName = "echo_bot")
    commandHandler.registerCommands {
        register("start") { update ->
            bot.sendMessage(SendMessageRequest(
                chatId = update.message!!.chat.id.toString(),
                text = "Send me any message and I'll echo it back!"
            ))
        }
    }

    val echoHandler = MessageHandler()
    echoHandler.registerActions {
        register(
            filter = { true },  // Match all messages
            action = { update ->
                val text = update.message?.text ?: return@register false
                bot.sendMessage(SendMessageRequest(
                    chatId = update.message!!.chat.id.toString(),
                    text = "Echo: $text"
                ))
                true
            }
        )
    }

    container.registerHandlers {
        register(commandHandler)
        register(echoHandler)
    }

    // Poll for updates
    var offset = 0L
    println("Bot started. Press Ctrl+C to stop.")
    while (true) {
        try {
            val updates = bot.getUpdates(
                GetUpdatesRequest(offset = offset, timeout = 30)
            ).getOrThrow()
            for (update in updates) {
                offset = update.updateId + 1
                container.processUpdate(update)
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            kotlinx.coroutines.delay(5000)  // Wait before retrying
        }
    }
}
```

## Best Practices

- **Always close the bot** — Call `bot.close()` in a `finally` block or use `use {}` if you wrap it
- **Set a timeout on getUpdates** — Use `timeout = 30` for long polling instead of rapid requests
- **Handle errors gracefully** — Wrap update processing in try/catch; don't let one bad update crash the loop
- **Use `ignoreUnknownKeys = true`** — Already configured in KTelegram's JSON, so new API fields won't break your bot
- **Extract chat ID early** — `update.message?.chat?.id?.toString()` is used everywhere; extract it to a val
- **Register command handlers first** — Commands should take priority over generic message handlers in the chain
- **Use `getOrNull` for non-critical calls** — Like `sendChatAction` before a slow operation; don't let it fail the whole flow
