<h1 align="center">KTelegram</h1>

<p align="center">
  <strong>Kotlin Multiplatform client for the Telegram Bot API</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-2.4.21-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin 2.4.21"/>
  <img src="https://img.shields.io/badge/JDK-21-orange" alt="JDK 21"/>
  <img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License MIT"/>
  <img src="https://img.shields.io/badge/Platforms-Android%20%7C%20JVM%20%7C%20iOS%20%7C%20macOS%20%7C%20Linux%20%7C%20Windows%20%7C%20JS%20%7C%20Wasm-lightgrey" alt="Platforms"/>
</p>

KTelegram is a Kotlin Multiplatform client for the Telegram Bot API. One codebase runs on Android, JVM, iOS, macOS, Linux, Windows, JS and WebAssembly. HTTP goes through Ktor, JSON through kotlinx-serialization, and every call is a suspend function. The public API uses `explicitApi()` and is checked for binary compatibility on every build.

## Installation

The library is published to GitHub Packages, which needs a token even for public packages. Add the repository, the library, and a Ktor engine:

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
    implementation("com.github.ayastrebov.telegram:telegram-api-client:0.6.0")
    // KTelegram does not bundle an HTTP engine; add the Ktor engine for your platform, e.g.:
    implementation("io.ktor:ktor-client-cio:3.6.0")      // JVM / Android / Native
    // implementation("io.ktor:ktor-client-js:3.6.0")    // JS / WasmJS
    // implementation("io.ktor:ktor-client-darwin:3.6.0") // iOS / macOS
}
```

KTelegram does not ship an HTTP engine. Without one on the classpath, `TelegramBot(...)` fails at construction with `Failed to find HTTP client engine implementation`. You can also pass one in: `TelegramBot(token, engine = CIO.create())`.

## Quick start

```kotlin
val bot = TelegramBot("YOUR_BOT_TOKEN")

// Verify the connection
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

## Supported platforms

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

## Polling

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

## Webhooks

If you run an HTTPS server (Ktor, Spring, anything), register a webhook and Telegram will push updates to it:

```kotlin
val bot = TelegramBot("YOUR_BOT_TOKEN")

// Register the webhook
bot.setWebhook(SetWebhookRequest(
    url = "https://your-server.com/webhook"
)).getOrThrow()

// In your HTTP handler, deserialize the incoming JSON
val json = Json { ignoreUnknownKeys = true }
val update = json.decodeFromString<Update>(requestBody)

// Process it
container.processUpdate(update)

// When shutting down
bot.deleteWebhook(DeleteWebhookRequest()).getOrThrow()
bot.close()
```

For long polling, `getUpdates` raises the HTTP request timeout to the poll `timeout` plus 10 seconds, so the engine does not cut the poll short. Every other call uses the engine's default timeout. Change it in the `configure` block: `TelegramBot(token) { install(HttpTimeout) { requestTimeoutMillis = 30_000 } }`.

Polling and webhooks cannot be used together. While a webhook is set, Telegram returns nothing from `getUpdates`.

## Handlers

Updates go through a chain of handlers in registration order. The first handler that returns `true` stops the chain, so register specific handlers before general ones.

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

### Callback queries (inline keyboards)

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

### Wiring it together

```kotlin
val container = HandlersContainer()
container.registerHandlers {
    register(commandHandler)
    register(callbackHandler)
    register(messageHandler) // catch-all last
}
```

## Error handling

Every API method returns a `Response<T>`. Pick how strict you want to be:

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

Pass a Ktor `MockEngine` to test against canned responses instead of the real API:

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

## API coverage

Models and methods follow [Bot API 10.3](https://core.telegram.org/bots/api) from August 2026.

| Category | Methods |
|----------|---------|
| Updates | `getUpdates`, `getMe` |
| Messages | `sendMessage`, `sendMessageDraft`, `sendPhoto`, `sendAudio`, `sendDocument`, `sendVideo`, `sendAnimation`, `sendVoice`, `sendVideoNote`, `sendLocation`, `sendVenue`, `sendContact`, `sendDice`, `sendSticker`, `sendPoll`, `sendMediaGroup`, `forwardMessage`, `forwardMessages`, `copyMessage`, `copyMessages`, `deleteMessages`, `sendChatAction` |
| Editing | `editMessageText`, `editMessageCaption`, `editMessageMedia`, `editMessageReplyMarkup`, `editMessageLiveLocation`, `stopMessageLiveLocation`, `setMessageReaction`, `stopPoll`, `deleteMessage` |
| Chat | `getChat`, `getChatMemberCount`, `getChatMember`, `getChatAdministrators`, `banChatMember`, `unbanChatMember`, `restrictChatMember`, `promoteChatMember`, `setChatAdministratorCustomTitle`, `banChatSenderChat`, `unbanChatSenderChat`, `setChatPermissions`, `leaveChat`, `setChatTitle`, `setChatDescription`, `deleteChatPhoto`, `pinChatMessage`, `unpinChatMessage`, `unpinAllChatMessages`, `setChatMemberTag`, `exportChatInviteLink`, `createChatInviteLink`, `editChatInviteLink`, `revokeChatInviteLink`, `approveChatJoinRequest`, `declineChatJoinRequest`, `getUserProfilePhotos`, `getUserChatBoosts`, `getBusinessConnection` |
| Forum topics | `createForumTopic`, `editForumTopic`, `closeForumTopic`, `reopenForumTopic`, `deleteForumTopic`, `unpinAllForumTopicMessages` |
| Callbacks | `answerCallbackQuery` |
| Inline | `answerInlineQuery` |
| Payments | `answerPreCheckoutQuery`, `answerShippingQuery` |
| Files | `getFile` |
| Webhooks | `setWebhook`, `getWebhookInfo`, `deleteWebhook` |
| Commands & bot info | `setMyCommands`, `getMyCommands`, `deleteMyCommands`, `setMyName`, `getMyName`, `setMyDescription`, `getMyDescription`, `setMyShortDescription`, `getMyShortDescription`, `setMyDefaultAdministratorRights`, `getMyDefaultAdministratorRights`, `setChatMenuButton`, `getChatMenuButton` |
| Managed bots | `getManagedBotToken`, `replaceManagedBotToken`, `savePreparedKeyboardButton` |
| Profile | `setMyProfilePhoto`, `removeMyProfilePhoto` |
| Bot session | `logOut`, `closeBot` |

Still missing: stickers, invoices and Stars (`sendInvoice`, `createInvoiceLink`, `refundStarPayment` and friends), gifts, business account management, stories, ephemeral messages, rich messages, checklists, paid media, games, passport, and multipart file uploads. Pull requests for any of these are welcome.

## Documentation

API docs are generated with [Dokka](https://kotlinlang.org/docs/dokka-get-started.html) and deployed to GitHub Pages on each release. To build them locally, run `./gradlew dokkaGenerate`; the output lands in `build/dokka/html/`.

## Testing

```bash
./gradlew allTests   # all platforms
./gradlew jvmTest    # JVM only (fastest)
```

<details>
<summary>Publishing</summary>

Push a tag and the release workflow does the rest:

```bash
git tag v1.0.0 && git push origin v1.0.0
```

The workflow runs the tests, publishes to GitHub Packages, creates a GitHub Release and deploys the docs.

To try a publish locally, run `./gradlew publishToMavenLocal`. To publish under a specific version, run `./gradlew publish -PKtelegramDeployVersion=1.0.0`.

</details>

## Artifact verification

Published artifacts are signed with GPG key `D0B3B155`. Import it with:

```bash
gpg --keyserver keyserver.ubuntu.com --recv-keys D0B3B155
```

<details>
<summary>Full public key</summary>

```
Fingerprint: 7E64 1A77 C010 DEB3 0E77  0A26 E324 0D17 D0B3 B155
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

Fork the repository, branch from `master`, and open a pull request against `master`. You need JDK 21, and the Android target needs an SDK (`ANDROID_HOME` or `sdk.dir` in `local.properties`). Run `./gradlew build` before you submit; CI runs the same command.

The conventions for models, request DTOs, Bot methods and tests are in [`AGENTS.md`](AGENTS.md). That file is written for AI coding agents (Claude Code, Gemini in Android Studio, Codex, Junie and others read it automatically), and it is the single source of truth for humans too. Step-by-step procedures for adding Telegram types and methods live in `.claude/skills/ktelegram/SKILL.md`.

If you change the public API, run `./gradlew apiDump` and commit the updated `api/` files. If you add Bot methods, add them to the coverage table above.

### Releasing

Push a `v*` tag such as `v1.2.0`. The publish workflow uploads to GitHub Packages, creates a GitHub Release, and deploys the docs to GitHub Pages.

## License

MIT
