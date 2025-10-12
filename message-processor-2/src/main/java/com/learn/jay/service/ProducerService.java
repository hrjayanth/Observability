package com.learn.jay.service;

import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String outputTopic;

    public ProducerService(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${app.kafka.output-topic}") String outputTopic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.outputTopic = outputTopic;
    }

    @WithSpan(value = "ProducerService.sendMessage.1", kind = SpanKind.PRODUCER)
    public void sendMessage(String message) {
        kafkaTemplate.send(outputTopic, message);
        System.out.printf("Produced -> %s%n", message);
    }
}

