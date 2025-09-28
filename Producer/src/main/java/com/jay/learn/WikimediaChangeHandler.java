package com.jay.learn;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

@Slf4j
public class WikimediaChangeHandler implements BackgroundEventHandler {
    private KafkaProducer<String, String> kafkaProducer;
    private String topic;

    public WikimediaChangeHandler() {
        // Default constructor
    }

    public WikimediaChangeHandler(KafkaProducer<String, String> producer, String topic) {
        this.kafkaProducer = producer;
        this.topic = topic;
    }

    @Override
    public void onOpen() {
        System.out.println("Connection opened");
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) {
        log.info("Received event: " + messageEvent.getData());
        kafkaProducer.send(new ProducerRecord<String,String>(topic, messageEvent.getData()));
    }

    @Override
    public void onComment(String comment) {
        // Optional: Handle comments if needed
    }

    @Override
    public void onError(Throwable t) {
        log.error("Error in stream: " + t.getMessage());
    }

    @Override
    public void onClosed() throws Exception {
        log.info("Stream closed");
        kafkaProducer.close();
    }
}