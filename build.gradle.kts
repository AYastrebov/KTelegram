@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kotlinx.binary.validator)
}

val deployVersion = findProperty("KtelegramDeployVersion") as String?
version = deployVersion?.removePrefix("v") ?: "0.0.1-SNAPSHOT"
group = "com.github.ayastrebov.telegram"
description = "Kotlin telegram bot API client"

// Dokka configuration
dokka {
    moduleName.set("KTelegram")
    moduleVersion.set(project.version.toString())

    dokkaSourceSets.configureEach {
        sourceLink {
            localDirectory.set(projectDir.resolve("src"))
            remoteUrl("https://github.com/ayastrebov/ktelegram/tree/master/src")
            remoteLineSuffix.set("#L")
        }

        externalDocumentationLinks.register("ktor") {
            url("https://api.ktor.io/")
        }
        externalDocumentationLinks.register("kotlinx-coroutines") {
            url("https://kotlinlang.org/api/kotlinx.coroutines/")
        }
        externalDocumentationLinks.register("kotlinx-serialization") {
            url("https://kotlinlang.org/api/kotlinx.serialization/")
        }

        includes.from("MODULE.md")
    }

    dokkaPublications.html {
        outputDirectory.set(layout.buildDirectory.dir("dokka/html"))
        failOnWarning.set(true)
    }

    pluginsConfiguration.html {
        footerMessage.set("KTelegram - MIT License")
        homepageLink.set("https://github.com/ayastrebov/ktelegram")
    }
}

kotlin {
    explicitApi()
    jvmToolchain(21)

    android {
        namespace = "com.github.ayastrebov.telegram"
        compileSdk = 36
    }
    jvm()

    iosArm64()
    iosSimulatorArm64()

    macosArm64()
    linuxX64()
    mingwX64()

    wasmJs { nodejs() }
    js { nodejs() }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.serialization.json)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

mavenPublishing {
    signAllPublications()

    coordinates(
        groupId = project.group.toString(),
        artifactId = "telegram-api-client",
        version = project.version.toString()
    )

    pom {
        name.set("KTelegram")
        description.set(project.description)
        url.set("https://github.com/ayastrebov/ktelegram")
        inceptionYear.set("2024")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("ayastrebov")
                name.set("Andrey Yastrebov")
                email.set("ayastrebov@gmail.com")
                url.set("https://github.com/ayastrebov")
            }
        }

        scm {
            url.set("https://github.com/ayastrebov/ktelegram")
            connection.set("scm:git:git://github.com/ayastrebov/ktelegram.git")
            developerConnection.set("scm:git:ssh://github.com/ayastrebov/ktelegram.git")
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ayastrebov/ktelegram")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: findProperty("gpr.user") as String?
                password = System.getenv("GITHUB_TOKEN") ?: findProperty("gpr.token") as String?
            }
        }
    }
}
