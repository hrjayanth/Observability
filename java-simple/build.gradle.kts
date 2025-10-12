plugins {
    id("java")
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.learn.jay"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.kafka:spring-kafka")
}

tasks.test {
    useJUnitPlatform()
}

//export JAVA_TOOL_OPTIONS="-javaagent:/Users/suvjay/Projects/Java/observability/resources/opentelemetry-javaagent.jar" \
//OTEL_TRACES_EXPORTER=logging \
//OTEL_METRICS_EXPORTER=logging \
//OTEL_LOGS_EXPORTER=logging \
//OTEL_METRIC_EXPORT_INTERVAL=15000