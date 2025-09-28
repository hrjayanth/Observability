package com.learn.jay.java_simple.config;

//import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;
//import io.opentelemetry.sdk.OpenTelemetrySdk;
//import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

//@Configuration
public class TelemetryConfig {

//    @PostConstruct
//    public void initOpenTelemetry() {
//        OpenTelemetrySdk openTelemetrySdk = AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
//        // Optionally store or use openTelemetrySdk as needed
//    }
}



//docker run \
//        --name otel-collector \
//        -p 127.0.0.1:4317:4317 \
//        -p 127.0.0.1:4318:4318 \
//        -p 127.0.0.1:55679:55679 \
//otel/opentelemetry-collector-contrib:0.135.0 \
//        2>&1 | tee collector-output.txt # Optionally tee output for easier search later
