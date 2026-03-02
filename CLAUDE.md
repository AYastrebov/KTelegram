# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

KTelegram is a Kotlin Multiplatform library providing a type-safe wrapper around the Telegram Bot API. It uses Ktor for HTTP, kotlinx-serialization for JSON, and coroutines for async operations. Targets: JVM, iOS (arm64/x64/simulatorArm64), macOS (arm64/x64), Linux x64, Windows (mingwX64), JS (browser/Node.js), WasmJS (browser/Node.js).

## Build Commands

```bash
./gradlew build                # Build all targets and run tests
./gradlew jvmTest              # Run JVM tests only
./gradlew publishToMavenLocal  # Publish all targets to local Maven repo
./gradlew publishAllPublicationsToGitHubPackagesRepository  # Publish to GitHub Packages
```

JDK 17 is required.

## Architecture

**Source sets**: `src/commonMain/kotlin/` (shared code), `src/commonTest/kotlin/` (shared tests).

**Core components** (`src/commonMain/kotlin/com/github/ayastrebov/telegram/`):

- **`Bot` interface / `BotImp`** — Telegram Bot API client. Wraps Ktor HTTP client with JSON content negotiation and Ktor logging plugin. All API methods are `suspend` functions. Base URL is `https://api.telegram.org/bot{token}/`.

- **`HandlersContainer` / `HandlerRegistration`** — Chain-of-responsibility pattern for processing updates. Handlers are evaluated in registration order; first handler returning `true` stops the chain. Configure via DSL: `container.registerHandlers { register(handler) }`.

- **`handler/`** — Specialized handlers extending `UpdateHandler`: `CommandHandler` (slash commands), `MessageHandler` (text), `PhotoHandler`, `InlineQueryHandler`, `ServiceHandler`, `NewMemberHandler`. Each has its own DSL for registering actions.

- **`model/`** — `@Serializable` data classes mapping to Telegram API types. All use `@SerialName` for JSON field mapping. `Response<T>` wraps all API responses.

- **`request/`** — Request DTOs for API calls (`SendMessageRequest`, `SendPhotoRequest`, etc.).

- **`utils/LocalDateTimeUnixSerializer`** — Custom serializer converting Unix timestamps to `LocalDateTime` using `Europe/Moscow` timezone.

## Key Conventions

- JSON config: `ignoreUnknownKeys = true`, `isLenient = true`, `prettyPrint = true`
- IDs (message, chat, user) are `Long` type
- Version is derived from git tags (`v*` prefix stripped) or defaults to `0.0.1-SNAPSHOT`
- Publishing uses vanniktech/gradle-maven-publish-plugin with GPG signing
- Publishing credentials come from env vars (`GPR_USER`, `GPR_TOKEN`)
