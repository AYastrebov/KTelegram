plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.dokka)
    alias(libs.plugins.caupain)
    alias(libs.plugins.kotlinx.serialization)
    id("convention.publication")
}

val deployVersion = findProperty("KtelegramDeployVersion") as String?
version = deployVersion?.removePrefix("v") ?: "0.0.1-SNAPSHOT"
group = "com.github.ayastrebov.telegram"
description = "Kotlin telegram bot API client"

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.datetime)
}

publishing.publications.create<MavenPublication>("telegram") {
    groupId = project.group.toString()
    artifactId = project.name
    version = project.version.toString()
    description = project.description
}