# KTelegram

**KTelegram** is a Kotlin library for interacting with the Telegram Bot API. It provides a straightforward and idiomatic way to build Telegram bots using Kotlin.

## Installation

To use **KTelegram** in your Kotlin project, follow these steps:

### 1. Add the GitHub Packages Repository

Add the following Maven repository to your `build.gradle.kts`:

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

Add the following dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("com.github.ayastrebov:telegram:0.1.0")
}
```