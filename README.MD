<h1 align="center">KTelegram</h1>

<p align="center">
  <strong>Kotlin Multiplatform library for the Telegram Bot API</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-2.3.21-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin 2.3.21"/>
  <img src="https://img.shields.io/badge/JDK-21-orange" alt="JDK 21"/>
  <img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License MIT"/>
  <img src="https://img.shields.io/badge/Platforms-Android%20%7C%20JVM%20%7C%20iOS%20%7C%20macOS%20%7C%20Linux%20%7C%20Windows%20%7C%20JS%20%7C%20Wasm-lightgrey" alt="Platforms"/>
</p>

---

## Features

- **Multiplatform** — Write once, run on Android, JVM, iOS, macOS, Linux, Windows, JS, and WebAssembly
- **Type-Safe** — Kotlin data classes for all request/response models with compile-time safety
- **Coroutine-Based** — Fully asynchronous with Kotlin coroutines
- **Handler System** — Chain-of-responsibility pattern for processing updates with built-in handlers for commands, messages, callbacks, inline queries, and more
- **Explicit API** — All public declarations have explicit visibility modifiers

## Installation

Add the GitHub Packages repository and dependency to your project:

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/ayastrebov/ktelegram")
        credentials {
            username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
            password = providers.gradleProperty("gpr.token").orNull ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

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

## Supported Platforms

| Platform | Target |
|----------|--------|
| Android | `android` |
| JVM | `jvm` |
| iOS | `iosArm64`, `iosSimulatorArm64` |
| macOS | `macosArm64` |
| Linux | `linuxX64` |
| Windows | `mingwX64` |
| JavaScript | `js` (Node.js) |
| WebAssembly | `wasmJs` |

## Polling Example

```kotlin
val bot = TelegramBot("YOUR_BOT_TOKEN")
var offset = 0L

while (true) {
    val updates = bot.getUpdates(GetUpdatesRequest(offset = offset, timeout = 30)).getOrThrow()
    for (update in updates) {
        offset = update.updateId + 1
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
val json = Json { ignoreUnknownKeys = true }
val update = json.decodeFromString<Update>(requestBody)

// 3. Process the update through your handlers
container.processUpdate(update)

// 4. When shutting down, remove the webhook
bot.deleteWebhook(DeleteWebhookRequest()).getOrThrow()
bot.close()
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

| Category | Methods |
|----------|---------|
| Updates | `getUpdates`, `getMe` |
| Messages | `sendMessage`, `sendMessageDraft`, `sendPhoto`, `sendAudio`, `sendDocument`, `sendVideo`, `sendAnimation`, `sendVoice`, `sendVideoNote`, `sendLocation`, `sendContact`, `sendDice`, `sendSticker`, `sendPoll`, `forwardMessage`, `forwardMessages`, `copyMessage`, `copyMessages`, `deleteMessages`, `sendChatAction` |
| Editing | `editMessageText`, `editMessageCaption`, `editMessageReplyMarkup`, `deleteMessage` |
| Chat | `getChat`, `getChatMemberCount`, `getChatMember`, `getChatAdministrators`, `banChatMember`, `unbanChatMember`, `leaveChat`, `setChatTitle`, `setChatDescription`, `pinChatMessage`, `unpinChatMessage`, `promoteChatMember`, `setChatMemberTag` |
| Callbacks | `answerCallbackQuery` |
| Inline | `answerInlineQuery` |
| Files | `getFile` |
| Webhooks | `setWebhook`, `getWebhookInfo`, `deleteWebhook` |
| Commands | `setMyCommands`, `getMyCommands`, `deleteMyCommands` |
| Managed Bots | `getManagedBotToken`, `replaceManagedBotToken`, `savePreparedKeyboardButton` |
| Profile | `setMyProfilePhoto`, `removeMyProfilePhoto` |
| Bot Session | `logOut`, `closeBot` |

## Documentation

Generate API documentation using [Dokka](https://kotlinlang.org/docs/dokka-get-started.html):

```bash
./gradlew dokkaGenerate
```

Output: `build/dokka/html/index.html`

Documentation is also deployed to GitHub Pages on each release.

## Testing

```bash
# Run all platform tests
./gradlew allTests

# Run JVM tests only
./gradlew jvmTest
```

<details>
<summary><strong>Publishing</strong></summary>

### Publish to MavenLocal

```bash
./gradlew publishToMavenLocal
```

Artifacts: `~/.m2/repository/com/github/ayastrebov/telegram/`

### Publish to GitHub Packages

```bash
export GITHUB_ACTOR=your-github-username
export GITHUB_TOKEN=your-github-token
./gradlew publishAllPublicationsToGitHubPackagesRepository
```

Or set in `local.properties`:
```properties
gpr.user=your-github-username
gpr.token=your-github-token
```

### Version Override

```bash
./gradlew publish -PKtelegramDeployVersion=1.0.0
```

</details>

## Artifact Verification

Published artifacts are signed with GPG. To verify signatures, import the public key:

```bash
gpg --keyserver keyserver.ubuntu.com --recv-keys D0B3B155
```

Or import manually:

<details>
<summary>Public Key</summary>

```
Fingerprint: 7E64 1A77 C010 DEB3 0E77  0A26 E324 0D17 D0B3 B155
Key ID: D0B3B155
```

```bash
gpg --import <<'EOF'
-----BEGIN PGP PUBLIC KEY BLOCK-----

mQINBGn7XzcBEACqtfHxqRBbHwPZF+SkcP9ns0lxLqRiONZge1NIwfjvlGELPpWv
Rf2e6rGGlu17ppqmpWAN9dK2y+dHogEDXcOxquGNsQAb1tL72yfxPam7WihLkaTJ
tUo6fX7L/wGZSJeV9Us+SasvN3xqZsVtxb0k5qAu0083VlDFcztCaP/pse5AEJ3M
F4EbhIG47EofFMaSbY0ysyL7PvgcVfqxUmGeZH5rufR5Sl9a3JNF65pOryyQWh3J
PPzqWTXGD3sbG4HKaKIdIhqFQYvC5crf+mhZFxPnZuN3X8z+5Ccc/+Hvuw4TMgEl
U50Jqg2YQilabtsputTzoxfRCcibov5S1oiZd+dijYQ9PotGupp5TCYb/UIqOSP9
Wh6xdzXZKMVjJxVYrJesvrt5Zrb672tKZutu17mOaZvt3gF4/B33uEX81Fjr6yKT
KTqSQYqBDC+tgX9xQ8R5N9mNNlmJvG181q7MqjvKVEG4mwzJ5ELj81w1va7y7wN9
QTv4o9WTAEV9n18HVBXJkuYb5eeHhflIcNvuxxWLPx2kU8FIFsXvqWoCloRRXtmv
q5D9gvcCstX4nHz4Bwp5TmxNl5zMuLMF7IohO+oMRgXwhMSeqtUyDY1gzYTiS/iH
tWLaC/BWfduw/UtmpACl8PeSo7FiL3giIBWyJkBxXVTiY13WFf14zSiNUwARAQAB
tD9BbmRyZXkgWWFzdHJlYm92IChNYXZlbiBzaWduaW5nIGtleSBSU0EpIDxheWFz
dHJlYm92QGdtYWlsLmNvbT6JAm4EEwEIAFgWIQR+ZBp3wBDesw53CibjJA0X0LOx
VQUCaftfNxsUgAAAAAAEAA5tYW51MiwyLjUrMS4xMiwwLDMDGy8EBQsJCAcCAiIC
BhUKCQgLAgQWAgMBAh4HAheAAAoJEOMkDRfQs7FVm1MP/isy1rZCh3L4p7PbUSTQ
OMJs0QS+fC8YwFJTG1B6ZJmRPJWGoniOtG9pG1I2JKWYlUM32jFuUAdNjFehbL8q
pBzsdEuAn7c586d6t+tHghFBV5JHf8G9006UPHtDXRugNpU4AiKjQ543CaF9kPIX
Chyd99NXbKFkGrH2ILjL9NHkfBh5DsaiHvymIPIgqLh4lexMD9Az5FKD3/jsF+IW
+NxhchGGOz1nHCKEPmXZYWVQhC2YI/GWLOlcqsn8A9RJmxJFICuKyHbwMy3zcayo
K9D1Q3D4ShbiRGL32cjblTwZrehZjc5znOugW3poa9MXJOT7Lv3orkEOB9HPKAVl
wgoS5g8BbbYgKDniFZIAyfVlW+Zmmeyt2VGdNOu2kQbc166MsKd6neUWN1FVCMoy
oIpvFnWoVtAhghhRNa6g7YXtgS9CcqH2wHUsOmOCiFiGej9iBTcRqjQWBCkkrrii
4KZRus+VL6ihI4IDLplu1KNc1dk+6z3lMlg9EKE5lmr8cbkKWv5I3fXPUGYbHDqn
OUcDq4fGLMyLrBbD2469BuH1vkn6Tfr7zCWb4JlCur+/oH1sa9va5rS+nSmOc4NY
sIRZEunN6f9TpE4Iik5YrpS6y+OnKCSICG5RycJFp9msyDl745HUYN0sRD5U6UuC
jnazxtucoAKo26CUAGUsRpjO
=tPzu
-----END PGP PUBLIC KEY BLOCK-----
EOF
```

</details>

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository and create a branch from `master`.
2. Make sure JDK 21 is installed.
3. Run the full build before submitting:
   ```bash
   ./gradlew build
   ```
4. Follow existing code conventions:
   - Use `@Serializable` data classes with `@SerialName` for new Telegram API types in `model/`.
   - Group request DTOs by category in `request/`.
   - All Bot API methods should be `suspend fun` using `client.post()`.
   - Use `Long` for IDs and `kotlin.time.Instant` (with `InstantUnixSerializer`) for timestamps.
   - All public declarations must have explicit `public` visibility modifiers.
5. Add tests using `MockEngine` and `runTest` in `src/commonTest/kotlin/`.
6. Open a pull request against `master`. CI will run automatically.

### Releasing

Releases are automated. Push a `v*` tag (e.g., `v1.2.0`) to trigger the publish workflow, which publishes to GitHub Packages, creates a GitHub Release, and deploys documentation to GitHub Pages.

## License

MIT
