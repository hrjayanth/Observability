package com.learn.jay.java_simple;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RollController {
    private static final Logger logger = LoggerFactory.getLogger(RollController.class);

    @GetMapping("/rolldice")
    @WithSpan(
            kind = SpanKind.INTERNAL,
            value = "index-span"
    )
    public String index(@RequestParam("player") Optional<String> player) {
        Span span = Span.current();
        span.setAttribute("My_key", "Jay-Key");
        int result = this.getRandomNumber(1, 6);
        if (player.isPresent()) {
            logger.info("{} is rolling the dice: {}", player.get(), result);
        } else {
            logger.info("Anonymous player is rolling the dice: {}", result);
        }
        return Integer.toString(result);
    }

    @WithSpan(kind = SpanKind.INTERNAL, value = "getRandomNumber-span")
    public int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
