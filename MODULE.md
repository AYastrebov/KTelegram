# Module KTelegram

Kotlin Multiplatform library providing a type-safe, coroutine-based client for the [Telegram Bot API](https://core.telegram.org/bots/api).

## Getting Started

Create a bot instance and start making API calls:

```kotlin
val bot = TelegramBot("YOUR_BOT_TOKEN")
val me = bot.getMe().getOrThrow()
bot.sendMessage(SendMessageRequest(chatId = "123", text = "Hello!"))
bot.close()
```

## Core Components

- **[Bot][com.github.ayastrebov.telegram.Bot]** — Main API client interface with all Telegram Bot API methods.
- **[HandlersContainer][com.github.ayastrebov.telegram.HandlersContainer]** — Chain-of-responsibility handler system for processing updates.
- **model** — Data classes mapping to Telegram API types (`Message`, `User`, `Chat`, `Update`, etc.).
- **request** — Request DTOs for each API method (`SendMessageRequest`, `SendPollRequest`, etc.).
- **handler** — Specialized update handlers: `CommandHandler`, `MessageHandler`, `CallbackQueryHandler`, `InlineQueryHandler`, `PhotoHandler`, and more.

# Package com.github.ayastrebov.telegram

Core bot client and handler system.

# Package com.github.ayastrebov.telegram.model

Telegram Bot API types — messages, users, chats, polls, keyboards, and all response/request models.

# Package com.github.ayastrebov.telegram.request

Request DTOs for Telegram Bot API methods, grouped by category.

# Package com.github.ayastrebov.telegram.handler

Specialized update handlers for commands, messages, callbacks, inline queries, photos, and service messages.

# Package com.github.ayastrebov.telegram.utils

Utility classes including `InstantUnixSerializer` for Unix timestamp conversion.
