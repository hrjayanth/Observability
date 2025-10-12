plugins {
    id("java")
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.learn.jay"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("io.opentelemetry:opentelemetry-api:1.54.1")
    implementation("io.opentelemetry:opentelemetry-sdk:1.54.1")
    implementation("io.opentelemetry:opentelemetry-exporter-otlp:1.54.1")
    implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:2.20.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.19.2")
}

tasks.test {
    useJUnitPlatform()
}