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

    // https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder
    implementation("net.logstash.logback:logstash-logback-encoder:9.0")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    // OpenTelemetry dependencies
    implementation("io.opentelemetry:opentelemetry-api:1.54.1")
    implementation("io.opentelemetry:opentelemetry-sdk:1.54.1")
    implementation("io.opentelemetry:opentelemetry-exporter-otlp:1.54.1")
    implementation("io.opentelemetry.instrumentation:opentelemetry-instrumentation-annotations:2.20.1")

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.19.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.bootRun{
    jvmArgs = listOf(
        "-javaagent:${projectDir}/../resources/opentelemetry-javaagent.jar"
    )

    environment("OTEL_EXPORTER_OTLP_ENDPOINT", "http://localhost:4317")
    environment("OTEL_EXPORTER_OTLP_PROTOCOL", "grpc")
    environment("OTEL_LOGS_EXPORTER", "none")
    environment("OTEL_METRICS_EXPORTER", "none")
    environment("OTEL_RESOURCE_ATTRIBUTES", "deployment.environment=dev")
    environment("OTEL_SERVICE_NAME", "my-application-2")
    environment("OTEL_TRACES_EXPORTER", "otlp")
    environment("OTEL_TRACES_SAMPLER", "always_on")
    environment("service.version", "1.0.0")
}
