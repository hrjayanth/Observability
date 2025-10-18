package com.learn.jay.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class ConsumerService {

    private final ProducerService producerService;
    private static final String ID_PATH = "OrderDetails/Id";

    public ConsumerService(ProducerService producerService) {
        this.producerService = producerService;
    }

    @KafkaListener(topics = "${app.kafka.input-topic}", groupId = "${spring.kafka.consumer.group-id}")
    @WithSpan(value = "ConsumerService.consume.1", kind = SpanKind.CONSUMER)
    public void consume(ConsumerRecord<String, String> record) {
        String inputMessage = record.value();
        System.out.printf("Consumed -> %s%n", inputMessage);

        Span.current().setAttribute("Application", "Message-Processor1");

        // Processing logic
        String processedMessage = "Processed: " + inputMessage;

        // Forward to Producer
        producerService.sendMessage(processedMessage);
    }

    public String extractMessageID(String inputMessage, String tagPath) {
        XmlMapper xmlMapper = new XmlMapper();

        try {
            JsonNode rootNode = xmlMapper.readTree(inputMessage);

            String[] pathParts = tagPath.split("/");
            JsonNode current = rootNode;

            for (String part : pathParts) {
                if (current == null) {
                    return null; // Path not found
                }
                current = current.path(part);
            }

            return current.isMissingNode() ? null : current.asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

//        return messageID;
    }
}
