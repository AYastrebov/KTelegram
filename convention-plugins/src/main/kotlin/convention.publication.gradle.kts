//Publishing your Kotlin Multiplatform library to Maven Central
//https://dev.to/kotlin/how-to-build-and-publish-a-kotlin-multiplatform-library-going-public-4a8k

import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import java.util.*

plugins {
    id("maven-publish")
    id("signing")
}

// Stub secrets to let the project sync and build without the publication values set up
ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKey"] = null
ext["gpr.user"] = null
ext["gpr.token"] = null

// Grabbing secrets from local.properties file or from environment variables, which could be used on CI
val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    secretPropsFile.reader().use {
        Properties().apply { load(it) }
    }.onEach { (name, value) ->
        ext[name.toString()] = value
    }
} else {
    ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
    ext["signing.secretKey"] = System.getenv("SIGNING_SECRET_KEY")
    ext["gpr.user"] = System.getenv("GPR_USER")
    ext["gpr.token"] = System.getenv("GPR_TOKEN")
}

fun getExtraString(name: String) = ext[name]?.toString()

// Decode Base64-encoded GPG key
fun getDecodedString(name: String): String? {
    val encoded = getExtraString(name)
    return encoded?.let {
        String(Base64.getDecoder().decode(it))
    }
}

publishing {
    // Configure maven central repository
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

    // Configure all publications
    publications.withType<MavenPublication> {
        // Provide artifacts information requited by Maven Central
        pom {
            name.set("ktelegram")
            description.set("Kotlin telegram bot API client")
            url.set("https://github.com/AYastrebov/KTelegram")

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
                connection.set("scm:git:git://github.com/yourusername/your-repo.git")
                developerConnection.set("scm:git:ssh://github.com/ayastrebov/ktelegram.git")
                url.set("https://github.com/ayastrebov/ktelegram")
            }
        }
    }
}

// Signing artifacts. Signing.* extra properties values will be used
signing {
    val keyId = getExtraString("signing.keyId")
    val password = getExtraString("signing.password")
    val secretKey = getDecodedString("signing.secretKey")

    if (keyId != null && secretKey != null) {
        useInMemoryPgpKeys(keyId, secretKey, password)
        sign(publishing.publications)
    }
}

//https://github.com/gradle/gradle/issues/26132
val signingTasks = tasks.withType<Sign>()
tasks.withType<AbstractPublishToMaven>().configureEach {
    mustRunAfter(signingTasks)
}