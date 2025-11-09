package com.learn.jay.java_simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class DataInserter {

    public static void main(String[] args) {
        String orderId = "10300";  // Take ID dynamically from arguments
        int count = 100; // Number of messages to send
        String bootstrapServers = "localhost:9092";
        String topicName = "input-messages";

        // Kafka Producer properties
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        int startId = Integer.parseInt(orderId);

        for(int i = startId; i < startId + count; i++) {
            orderId = i + "";

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Do nothing
            }

            // Create Kafka producer
            try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {

                // Build XML dynamically with ID
                String xmlMessage = String.format("""
                        <Order>
                            <OrderDetails>
                                <Id>%s</Id>
                                <Customer>Dynamic User</Customer>
                                <Amount>123.45</Amount>
                            </OrderDetails>
                        </Order>
                        """, orderId);

                // Send message
                producer.send(new ProducerRecord<>(topicName, xmlMessage));

                System.out.printf("✅ Sent XML message with ID=%s to topic '%s'%n", orderId, topicName);
            }
        }
    }
}
