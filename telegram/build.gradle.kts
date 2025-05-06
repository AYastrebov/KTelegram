plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.kotlinx.serialization)
    id("convention.publication")
}

group = "com.github.ayastrebov"
val deployVersion = findProperty("KtelegramDeployVersion") as String?
version = deployVersion?.removePrefix("v") ?: "0.0.1-SNAPSHOT"

dependencies {
    implementation(libs.kotlinx.datetime)

    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)

    api(libs.slf4j.api)
}

publishing {
    publications {
        create<MavenPublication>("telegram") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}