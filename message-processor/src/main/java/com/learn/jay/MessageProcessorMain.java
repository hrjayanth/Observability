package com.learn.jay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MessageProcessorMain {
    public static void main(String[] args) {
        log.info("Starting Message Processor Application...");
        SpringApplication.run(MessageProcessorMain.class, args);
    }
}
