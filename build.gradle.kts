plugins {
    id("java")
}

group = "com.learn.jay"
version = "1.0-SNAPSHOT"

subprojects {
    repositories {
        mavenCentral()
    }

    plugins.withId("java") {
        java {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(21))
            }
        }
    }
}
