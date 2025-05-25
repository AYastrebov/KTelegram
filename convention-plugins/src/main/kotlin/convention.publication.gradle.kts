// Publishing your Kotlin Multiplatform or JVM library to Maven Central / GitHub Packages
// Based on: https://dev.to/kotlin/how-to-build-and-publish-a-kotlin-multiplatform-library-going-public-4a8k

import org.jetbrains.dokka.gradle.DokkaTask
import java.util.*

plugins {
    id("maven-publish")
    id("signing")
}

// Stub secrets to allow build without actual credentials
extra["signing.keyId"] = null
extra["signing.password"] = null
extra["signing.secretKey"] = null
extra["gpr.user"] = null
extra["gpr.token"] = null

// Load secrets from local.properties or environment variables
val secretPropsFile = rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    Properties().apply {
        secretPropsFile.reader().use { load(it) }
    }.forEach { (name, value) ->
        extra[name.toString()] = value
    }
} else {
    extra["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    extra["signing.password"] = System.getenv("SIGNING_PASSWORD")
    extra["signing.secretKey"] = System.getenv("SIGNING_SECRET_KEY")
    extra["gpr.user"] = System.getenv("GPR_USER")
    extra["gpr.token"] = System.getenv("GPR_TOKEN")
}

fun getExtraString(name: String) = extra[name]?.toString()

fun getDecodedString(name: String): String? {
    val encoded = getExtraString(name)
    return encoded?.let { String(Base64.getDecoder().decode(it)) }
}

inline fun <reified T : Task> TaskContainer.safeNamed(name: String): TaskProvider<T>? =
    try {
        named<T>(name)
    } catch (_: UnknownTaskException) {
        null
    }


// Delay creation of JARs until relevant plugins are applied
plugins.withType<JavaPlugin> {
    tasks.register<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(project.extensions.getByType<SourceSetContainer>()["main"].allSource)
    }
}

plugins.withId("org.jetbrains.dokka") {
    tasks.register<Jar>("dokkaJavadocJar") {
        archiveClassifier.set("javadoc")
        from(tasks.named<DokkaTask>("dokkaJavadoc").flatMap { it.outputDirectory })
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ayastrebov/ktelegram")
            credentials {
                username = getExtraString("gpr.user")
                password = getExtraString("gpr.token")
            }
        }
    }

    publications.withType<MavenPublication>().configureEach {
        // Only add Java component if present
        if (project.plugins.hasPlugin("java") || project.plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
            from(components["java"])
        }

        // Attach artifacts if they exist
        tasks.safeNamed<Jar>("sourcesJar")?.let { artifact(it) }
        tasks.safeNamed<Jar>("dokkaJavadocJar")?.let { artifact(it) }

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
    }
}

signing {
    val keyId = getExtraString("signing.keyId")
    val password = getExtraString("signing.password")
    val secretKey = getDecodedString("signing.secretKey")

    if (keyId != null && secretKey != null) {
        useInMemoryPgpKeys(keyId, secretKey, password)
        sign(publishing.publications)
    }
}

// Workaround: ensure signing happens before publishing
val signingTasks = tasks.withType<Sign>()
tasks.withType<AbstractPublishToMaven>().configureEach {
    mustRunAfter(signingTasks)
}
