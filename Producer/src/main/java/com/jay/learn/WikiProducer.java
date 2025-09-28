package com.jay.learn;

import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.opentelemetry.api.trace.SpanKind;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import com.launchdarkly.eventsource.ConnectStrategy;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;

import io.opentelemetry.instrumentation.annotations.WithSpan;
//import io.opentelemetry.api.OpenTelemetry;
//import io.opentelemetry.api.common.AttributeKey;
//import io.opentelemetry.api.common.Attributes;
//import io.opentelemetry.api.trace.Span;
//import io.opentelemetry.api.trace.Tracer;
//import io.opentelemetry.sdk.OpenTelemetrySdk;
//import io.opentelemetry.sdk.resources.Resource;
//import io.opentelemetry.sdk.trace.SdkTracerProvider;
//import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
//import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;

@Slf4j
public class WikiProducer {
//    private static final Logger log = LoggerFactory.getLogger(WikiProducer.class);

    @WithSpan(value = "WikiProducer.main", kind = SpanKind.PRODUCER)
    public static void main(String[] args) throws InterruptedException {
        // --- OpenTelemetry Initialization ---
//        Resource resource = Resource.create(Attributes.of(AttributeKey.stringKey("service.name"), "wiki-producer"));
//        OtlpGrpcSpanExporter otlpExporter = OtlpGrpcSpanExporter.builder()
//                .setEndpoint("http://localhost:4317")
//                .build();
//        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
//                .addSpanProcessor(BatchSpanProcessor.builder(otlpExporter).build())
//                .setResource(resource)
//                .build();
//        OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
//                .setTracerProvider(tracerProvider)
//                .build();
//        Tracer tracer = openTelemetry.getTracer("com.jay.learn.WikiProducer");
//        // --- End OpenTelemetry Initialization ---
//
//        Span mainSpan = tracer.spanBuilder("WikiProducer.main").startSpan();
        try {
            log.info("Starting Wikimedia Change Stream...");
            // Producer Properties
//            Span producerSpan = tracer.spanBuilder("Create KafkaProducer").startSpan();
            Properties properties = new Properties();
            properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
            properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
//            producerSpan.end();

            // Kafka Topic
            String topic = "wikimedia.recentchange";

//            Span handlerSpan = tracer.spanBuilder("Setup WikimediaChangeHandler").startSpan();
            BackgroundEventHandler eventHandler = new WikimediaChangeHandler(producer, topic);
            String url = "https://stream.wikimedia.org/v2/stream/recentchange";
//            handlerSpan.end();

//            Span eventSourceSpan = tracer.spanBuilder("Start EventSource").startSpan();
            EventSource.Builder esBuilder = new EventSource.Builder(
                    ConnectStrategy.http(URI.create(url))
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .header("User-Agent", "WikiProducerBot/1.0 (abc@gmail.com)"));
            try (BackgroundEventSource eventSource = new BackgroundEventSource.Builder(eventHandler, esBuilder)
                    .build()) {
                eventSource.start();
//                eventSourceSpan.end();
                TimeUnit.MINUTES.sleep(10);
            }
        } finally {
//            mainSpan.end();
            // Shutdown OpenTelemetry
//            tracerProvider.shutdown();
        }
    }
}