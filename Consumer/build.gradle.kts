plugins {
    id("java")
}

group = "com.learn.jay"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mongodb:mongodb-driver-sync:5.5.1")
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.apache.kafka:kafka-clients:4.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.17")

    // Lombok dependencies
    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}