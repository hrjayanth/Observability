plugins {
    id("java")
}

group = "com.learn.jay"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("com.launchdarkly:okhttp-eventsource:4.1.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.apache.kafka:kafka-clients:4.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.17")

    // OpenTelemetry dependencies
    implementation("io.opentelemetry:opentelemetry-api:1.52.0")
    implementation("io.opentelemetry:opentelemetry-sdk:1.52.0")
    implementation("io.opentelemetry:opentelemetry-exporter-otlp:1.52.0")

    implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:2.20.1")

    // Lombok dependencies
    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.jar {
    manifest {
        attributes(mapOf("Main-Class" to "com.jay.learn.WikiProducer"))
    }
}

tasks.test {
    useJUnitPlatform()
}
