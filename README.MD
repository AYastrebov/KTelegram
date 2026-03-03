# KTelegram

A **Kotlin Multiplatform** library providing a type-safe, coroutine-based client for the [Telegram Bot API](https://core.telegram.org/bots/api).

## Supported Platforms

JVM, iOS (arm64/x64/simulatorArm64), macOS (arm64/x64), Linux x64, Windows (mingwX64), JS (browser/Node.js), WasmJS (browser/Node.js).

## Installation

### 1. Add the GitHub Packages Repository

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/ayastrebov/ktelegram")
        credentials {
            username = findProperty("gpr.user") as String? ?: System.getenv("GPR_USER")
            password = findProperty("gpr.token") as String? ?: System.getenv("GPR_TOKEN")
        }
    }
}
```

### 2. Add the Dependency

```kotlin
dependencies {
    implementation("com.github.ayastrebov.telegram:telegram-api-client:<version>")
}
```

## Quick Start

```kotlin
val bot = TelegramBot("YOUR_BOT_TOKEN")

// Get bot info
val me = bot.getMe().getOrThrow()
println("Bot: ${me.firstName} (@${me.username})")

// Send a message
bot.sendMessage(SendMessageRequest(
    chatId = "123456",
    text = "Hello from KTelegram!"
)).getOrThrow()

// Don't forget to close when done
bot.close()
```

## Polling Example

```kotlin
val bot = TelegramBot("YOUR_BOT_TOKEN")
var offset = 0L

while (true) {
    val updates = bot.getUpdates(GetUpdatesRequest(offset = offset, timeout = 30)).getOrThrow()
    for (update in updates) {
        offset = update.updateId + 1
        // Process each update
        container.processUpdate(update)
    }
}
```

## Webhook Example

Instead of polling with `getUpdates`, you can receive updates via a [webhook](https://core.telegram.org/bots/api#setwebhook). You'll need your own HTTP server (e.g., Ktor server) to handle incoming POST requests from Telegram.

```kotlin
val bot = TelegramBot("YOUR_BOT_TOKEN")

// 1. Register the webhook with Telegram
bot.setWebhook(SetWebhookRequest(
    url = "https://your-server.com/webhook"
)).getOrThrow()

// 2. In your HTTP server handler, parse the incoming Update
//    Telegram sends a JSON-serialized Update object in the POST body.
val json = Json { ignoreUnknownKeys = true }
val update = json.decodeFromString<Update>(requestBody)

// 3. Process the update through your handlers
container.processUpdate(update)

// 4. When shutting down, remove the webhook
bot.deleteWebhook(DeleteWebhookRequest()).getOrThrow()
bot.close()
```

You can check the current webhook status at any time:

```kotlin
val info = bot.getWebhookInfo().getOrThrow()
println("Webhook URL: ${info.url}, pending updates: ${info.pendingUpdateCount}")
```

> **Note:** Webhook mode and `getUpdates` polling are mutually exclusive — Telegram will not deliver updates via `getUpdates` while a webhook is set.

## Handler System

KTelegram provides a chain-of-responsibility handler system. Register handlers in order; the first one that returns `true` stops the chain.

### Commands

```kotlin
val commandHandler = CommandHandler(botName = "my_bot")
commandHandler.registerCommands {
    register("start") { update ->
        bot.sendMessage(SendMessageRequest(
            chatId = update.message!!.chat.id.toString(),
            text = "Welcome!"
        ))
    }
    register("help") { update ->
        bot.sendMessage(SendMessageRequest(
            chatId = update.message!!.chat.id.toString(),
            text = "Available commands: /start, /help"
        ))
    }
}
```

### Messages

```kotlin
val messageHandler = MessageHandler()
messageHandler.registerActions {
    register(
        filter = { it.contains("hello", ignoreCase = true) },
        action = { update ->
            bot.sendMessage(SendMessageRequest(
                chatId = update.message!!.chat.id.toString(),
                text = "Hello to you too!"
            ))
            true
        }
    )
}
```

### Callback Queries (Inline Keyboards)

```kotlin
val callbackHandler = CallbackQueryHandler()
callbackHandler.registerCallbacks {
    onData("approve") { query ->
        bot.answerCallbackQuery(AnswerCallbackQueryRequest(
            callbackQueryId = query.id,
            text = "Approved!"
        ))
    }
    onPrefix("page_") { query ->
        // Handle pagination: query.data is "page_1", "page_2", etc.
    }
}
```

### Wiring It Together

```kotlin
val container = HandlersContainer()
container.registerHandlers {
    register(commandHandler)
    register(callbackHandler)
    register(messageHandler) // catch-all last
}
```

## Error Handling

All API methods return `Response<T>`. Use the extensions:

```kotlin
// Throw on error
val user = bot.getMe().getOrThrow()

// Null on error
val user = bot.getMe().getOrNull()

// Check manually
val response = bot.sendMessage(request)
if (response.isError) {
    println("Error ${response.errorCode}: ${response.description}")
}
```

## Testing with MockEngine

The bot accepts an optional Ktor `HttpClientEngine` for testing:

```kotlin
val mockEngine = MockEngine { request ->
    respond(
        content = """{"ok": true, "result": {"id": 1, "is_bot": true, "first_name": "Test"}}""",
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )
}

val bot = TelegramBot("test-token", engine = mockEngine)
val me = bot.getMe().getOrThrow()
assertEquals("Test", me.firstName)
```

## API Coverage

KTelegram intentionally provides **partial** Telegram Bot API coverage focused on the most commonly used bot workflows.

| Category | Methods |
|----------|---------|
| Updates | `getUpdates`, `getMe` |
| Messages | `sendMessage`, `sendMessageDraft`, `sendPhoto`, `sendAudio`, `sendDocument`, `sendVideo`, `sendAnimation`, `sendVoice`, `sendVideoNote`, `sendLocation`, `sendContact`, `sendDice`, `sendSticker`, `forwardMessage`, `forwardMessages`, `copyMessage`, `copyMessages`, `deleteMessages`, `sendChatAction` |
| Editing | `editMessageText`, `editMessageCaption`, `editMessageReplyMarkup`, `deleteMessage` |
| Chat | `getChat`, `getChatMemberCount`, `getChatMember`, `getChatAdministrators`, `banChatMember`, `unbanChatMember`, `leaveChat`, `setChatTitle`, `setChatDescription`, `pinChatMessage`, `unpinChatMessage`, `setChatMemberTag` |
| Callbacks | `answerCallbackQuery` |
| Inline | `answerInlineQuery` |
| Files | `getFile` |
| Webhooks | `setWebhook`, `getWebhookInfo`, `deleteWebhook` |
| Commands | `setMyCommands`, `getMyCommands`, `deleteMyCommands` |
| Bot Session | `logOut`, `closeBot` |

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository and create a branch from `master`.
2. Make sure JDK 17 is installed.
3. Run the full build before submitting:
   ```bash
   ./gradlew build
   ```
4. Follow existing code conventions:
   - Use `@Serializable` data classes with `@SerialName` for new Telegram API types in `model/`.
   - Group request DTOs by category in `request/`.
   - All Bot API methods should be `suspend fun` using `client.post()`.
   - Use `Long` for IDs and `kotlin.time.Instant` (with `InstantUnixSerializer`) for timestamps.
5. Add tests using `MockEngine` and `runTest` in `src/commonTest/kotlin/`.
6. Open a pull request against `master`. CI will run automatically.

### Releasing

Releases are automated. Push a `v*` tag (e.g., `v1.2.0`) to trigger the release workflow, which publishes to GitHub Packages and creates a GitHub Release.

## License

MIT
