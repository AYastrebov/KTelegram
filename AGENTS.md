# AGENTS.md

Project instructions for AI coding agents (Claude Code, Gemini in Android Studio, Codex, Junie, …).
Humans: see `README.MD`. Keep this file short; link to longer docs instead of copying them.

## Project overview

KTelegram is a Kotlin Multiplatform library wrapping the Telegram Bot API (tracks Bot API 10.3).
Targets: Android, JVM, iOS, macOS, Linux, Windows, JS, WasmJS. HTTP via Ktor, JSON via
kotlinx-serialization, async via coroutines. Public API is binary-compatibility tracked.

Key locations (all under `src/commonMain/kotlin/com/github/ayastrebov/telegram/`):

- `Bot.kt` — `Bot` interface, `BotImp` implementation, `TelegramBot()` factory. One `client.post("<apiMethod>")` per Telegram method.
- `model/` — `@Serializable` types mirroring Telegram API objects.
- `request/` — request DTOs, one file per category (`MessageRequests.kt`, `ChatRequests.kt`, …).
- `handler/` + `HandlersContainer.kt` — chain-of-responsibility update handlers.
- `utils/InstantUnixSerializer.kt` — Unix seconds ↔ `kotlin.time.Instant`.
- Tests mirror this layout under `src/commonTest/kotlin/`; fixtures and `createTestBot()` live in `TestUtils.kt`.
- `api/jvm/` and `api/android/` — binary compatibility dumps (generated, but committed).

## Build and test

JDK 21. The Android target needs an SDK: set `ANDROID_HOME` or put `sdk.dir=…` in `local.properties`.

```bash
./gradlew jvmTest                 # fastest feedback loop
./gradlew build                   # all targets, all host-runnable tests, apiCheck
./gradlew apiDump                 # refresh api/jvm after any public API change (see below)
./gradlew dokkaGenerate           # docs; fails on any KDoc warning
./gradlew publishToMavenLocal     # local publish of all targets
```

Before finishing a task that touches `src/`: run `./gradlew jvmTest apiCheck dokkaGenerate` and fix failures.
When `apiCheck` fails after an intentional API change: run `./gradlew apiDump`, then copy
`api/jvm/telegram-api-client.api` over `api/android/telegram-api-client.api` (no Android dump task exists).

## Dependencies

Use the versions in `gradle/libs.versions.toml`. Ask before adding a dependency. The library must not
bundle a Ktor engine; consumers supply one.

## Code conventions

- `explicitApi()` is on: every public declaration needs `public` and an explicit return type.
- Model fields: `@SerialName("snake_case")` whenever the JSON name differs; optional fields are nullable with `= null`;
  required fields first, new optional fields appended last (binary compatibility).
- Types: IDs are `Long`; counts, sizes, thread ids are `Int`; Unix timestamps are `kotlin.time.Instant`
  with `@Serializable(with = InstantUnixSerializer::class)`; `chatId` in requests is `String`.
- Sealed "one of" types: put `@SerialName("<type value>")` on each subclass and do **not** declare a `type`
  property. A property named like the discriminator makes serialization throw at runtime.
  Use `@JsonClassDiscriminator` when Telegram's discriminator is not `type` (see `ChatBoostSource`).
- Polymorphic fields that Telegram distinguishes by shape, not by a discriminator (`KeyboardMarkup`,
  `InputMessageContent`), use the custom serializers in their files; extend those, don't add a discriminator.
- The client `Json` has `encodeDefaults = false`: a field with a default value is omitted on the wire.
  Use `@EncodeDefault` if Telegram requires it (see `ReplyKeyboardRemove`).
- Keep deprecated Telegram field names as `@Deprecated(..., ReplaceWith(...))` rather than deleting them.
- Every public declaration gets a KDoc line. Dokka runs with `failOnWarning`.
- Procedures for adding Telegram types and methods, and for releasing, are in `.claude/skills/ktelegram/SKILL.md`.

## Testing

- Serialization tests decode real-shaped Telegram JSON and assert fields; include a case with unknown keys.
- Bot method tests use `createTestBot()` with a `MockEngine`, assert `HttpMethod.Post` and the URL path, and
  inspect the request body when the wire format matters (see `SerializationFixesTest.kt`).
- Do not weaken or delete existing tests to make a check pass; fix the code or ask.

## Security

- Never commit, print or log a bot token; the token is part of the request URL, so don't enable Ktor request logging at INFO in examples.
- Publishing credentials and the GPG signing key come from environment variables / CI secrets only.
- `local.properties` and `.claude/settings.local.json` are local-only and gitignored.

## Commits and pull requests

- Small, focused commits; model types first, then request DTOs + `Bot` methods, then tests.
- Commit regenerated `api/*.api` files together with the API change that caused them.
- Update the "API coverage" table in `README.MD` when adding or removing Bot methods.
- Do not tag or publish releases unless explicitly asked; `v*` tags trigger the publish workflow.

## Reference

- Telegram Bot API: https://core.telegram.org/bots/api (fetch it; don't rely on memorised field lists).
- Consumer-facing usage guide: `.claude/skills/ktelegram-usage/SKILL.md`.
- Dokka module page: `MODULE.md`. CI: `.github/workflows/ci.yml`, releases: `.github/workflows/publish.yml`.
