import org.gradle.internal.extensions.stdlib.toDefaultLowerCase

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.dokka)
    alias(libs.plugins.caupain)
    alias(libs.plugins.kotlinx.serialization)
    id("convention.publication")
}

val projectId = "com.github.ayastrebov"

group = projectId
val deployVersion = findProperty("KtelegramDeployVersion") as String?
version = deployVersion?.removePrefix("v") ?: "0.0.1-SNAPSHOT"
description = "Telegram API client for Kotlin"

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

val sourcesJar = tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(project.sourceSets.main.map { it.allSource })
}
val dokkaJavadocJar = tasks.register<Jar>("dokkaJavadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.dokkaJavadoc.flatMap { it.outputDirectory })
}

publishing {
    publications {
        create<MavenPublication>("telegram") {
            groupId = projectId
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
            artifact(sourcesJar)
            artifact(dokkaJavadocJar)
        }
    }
}