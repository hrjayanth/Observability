package com.learn.jay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageProcessorMain {
    public static void main(String[] args) {
        SpringApplication.run(MessageProcessorMain.class, args);
    }
}



//OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4317;OTEL_TRACES_EXPORTER=otlp;OTEL_METRICS_EXPORTER=none;OTEL_LOGS_EXPORTER=none;OTEL_EXPORTER_OTLP_PROTOCOL=grpc;OTEL_SERVICE_NAME=message-processor;OTEL_RESOURCE_ATTRIBUTES=deployment.environment=dev;service.version=1.0.0;OTEL_TRACES_SAMPLER=always_on
//{span.Message-ID="1475" && resource.service.name="my-application"}
