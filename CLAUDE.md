# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

KTelegram is a Kotlin Multiplatform library providing a type-safe wrapper around the Telegram Bot API. It uses Ktor for HTTP, kotlinx-serialization for JSON, and coroutines for async operations. Targets: Android, JVM, iOS (arm64/simulatorArm64), macOS (arm64), Linux x64, Windows (mingwX64), JS (Node.js), WasmJS (Node.js). Uses `com.android.kotlin.multiplatform.library` plugin (AGP 9.0-ready).

## Build Commands

```bash
./gradlew build                # Build all targets and run tests
./gradlew jvmTest              # Run JVM tests only
./gradlew dokkaGenerate        # Generate API documentation (Dokka HTML)
./gradlew publishToMavenLocal  # Publish all targets to local Maven repo
./gradlew publishAllPublicationsToGitHubPackagesRepository  # Publish to GitHub Packages
```

JDK 21 is required (`jvmToolchain(21)`).

## Architecture

**Source sets**: `src/commonMain/kotlin/` (shared code), `src/commonTest/kotlin/` (shared tests).

**Core components** (`src/commonMain/kotlin/com/github/ayastrebov/telegram/`):

- **`Bot` interface / `BotImp`** — Telegram Bot API client (~45 methods). Wraps Ktor HTTP client with JSON content negotiation and logging. All API methods use `suspend fun` + `client.post()`. Supports both long-polling (`getUpdates`) and webhooks (`setWebhook`/`getWebhookInfo`/`deleteWebhook`) for receiving updates. Accepts optional `HttpClientEngine` for testing. Factory function: `TelegramBot(token, engine?, configure?)`. Call `close()` to release resources.

- **`HandlersContainer` / `HandlerRegistration`** — Chain-of-responsibility pattern for processing updates. Handlers are evaluated in registration order; first handler returning `true` stops the chain. Configure via DSL: `container.registerHandlers { register(handler) }`.

- **`handler/`** — Specialized handlers extending `UpdateHandler`: `CommandHandler` (slash commands), `MessageHandler` (text filters), `PhotoHandler`, `InlineQueryHandler`, `CallbackQueryHandler` (inline keyboards with `onData`/`onPrefix`), `EditedMessageHandler`, `ServiceHandler`, `NewMemberHandler`.

- **`model/`** — `@Serializable` data classes mapping to Telegram API types. All use `@SerialName` for JSON field mapping. `Response<T>` wraps all API responses with `getOrThrow()`, `getOrNull()`, `isError` extensions. `TelegramApiException` for error handling. Includes: `Poll`, `PollOption`, `PollAnswer`, `ManagedBotCreated`, `ManagedBotUpdated`, `ChatOwnerLeft`, `ChatOwnerChanged`, `VideoQuality`, `InputPollOption`, `PreparedKeyboardButton`.

- **`request/`** — Request DTOs grouped by category: `MessageRequests.kt`, `EditRequests.kt`, `ChatRequests.kt`, `InlineRequests.kt`, `UpdateRequests.kt`, `CommandRequests.kt`, `PollRequests.kt`, `ManagedBotRequests.kt`.

- **`utils/InstantUnixSerializer`** — Custom serializer converting Unix timestamps to `kotlin.time.Instant` (stdlib, no extra dependencies).

## Key Conventions

- `explicitApi()` mode enabled — all public declarations must have explicit visibility modifiers and return types
- JSON config: `ignoreUnknownKeys = true`, `isLenient = true`, `prettyPrint = true`
- All HTTP calls use POST (Telegram API accepts POST with JSON body for everything)
- IDs (message, chat, user) are `Long` type
- Timestamps use `kotlin.time.Instant` with `InstantUnixSerializer`
- Version is derived from git tags (`v*` prefix stripped) or defaults to `0.0.1-SNAPSHOT`
- Publishing uses vanniktech/gradle-maven-publish-plugin with GPG signing
- Publishing credentials come from env vars (`GITHUB_ACTOR`, `GITHUB_TOKEN`)
- Tests use ktor-client-mock `MockEngine` for HTTP mocking and kotlinx-coroutines-test `runTest`
- GPG signing is enabled via `signAllPublications()` in the `mavenPublishing` block
- Artifacts are signed with GPG key `D0B3B155`

## CI/CD

**Workflows** (`.github/workflows/`):

- **`ci.yml`** — Runs `./gradlew build` on pushes to `master`/`main` and PRs. Uses JDK 21 (corretto) with Gradle caching (cache-read-only for PRs). Uploads test results as artifacts.
- **`publish.yml`** — Triggered by `v*` tag pushes or manual dispatch. Three parallel jobs after test:
  - **test** — Runs `./gradlew allTests`
  - **publish** — Publishes to GitHub Packages and creates a GitHub Release with auto-generated notes. Version passed via `-PKtelegramDeployVersion`.
  - **docs** — Generates Dokka HTML documentation and deploys to GitHub Pages.
  - Requires `SIGNING_KEY_ID`, `SIGNING_PASSWORD`, `SIGNING_SECRET_KEY` secrets for GPG signing.

## Documentation

API documentation is generated with [Dokka](https://github.com/Kotlin/dokka) and deployed to GitHub Pages on each release. Source links point back to the repository master branch.
