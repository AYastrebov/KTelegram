import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.vanniktech.publish)
}

kotlin {
    jvm()

    iosArm64()
    iosX64()
    iosSimulatorArm64()

    macosArm64()
    macosX64()

    linuxX64()
    mingwX64()

    js {
        browser()
        nodejs()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = "com.github.ayastrebov.telegram",
        artifactId = "telegram-api-client",
        version = (findProperty("KtelegramDeployVersion") as String?)?.removePrefix("v") ?: "0.0.1-SNAPSHOT"
    )

    pom {
        name.set("telegram")
        description.set("Kotlin telegram bot API client")
        url.set("https://github.com/ayastrebov/ktelegram")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                id.set("ayastrebov")
                name.set("Andrey Yastrebov")
                email.set("ayastrebov@gmail.com")
            }
        }
        scm {
            connection.set("scm:git:git://github.com/ayastrebov/ktelegram.git")
            developerConnection.set("scm:git:ssh://github.com/ayastrebov/ktelegram.git")
            url.set("https://github.com/ayastrebov/ktelegram")
        }
    }

    publishToMavenCentral()
    signAllPublications()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ayastrebov/ktelegram")
            credentials {
                username = System.getenv("GPR_USER")
                password = System.getenv("GPR_TOKEN")
            }
        }
    }
}
