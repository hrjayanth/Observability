# Use a supported Temurin JDK image
FROM eclipse-temurin:21-jdk

# Working directory inside the image
WORKDIR /app

# Copy the shadow (fat) JAR produced by the build
COPY Producer/build/libs/Producer-1.0-SNAPSHOT-all.jar /app/producer.jar
COPY resources/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar

# Set sensible defaults for OpenTelemetry; these can be overridden at runtime
ENV OTEL_TRACES_EXPORTER=otlp \
    OTEL_EXPORTER_OTLP_PROTOCOL=grpc \
    OTEL_EXPORTER_OTLP_ENDPOINT=http://otel-collector:4317 \
    OTEL_RESOURCE_ATTRIBUTES=service.name=wiki-producer \
    OTEL_METRICS_EXPORTER=none

# Allow passing additional java options via JAVA_OPTS
ENV JAVA_OPTS="-Xms128m -Xmx512m"

# Run the producer; enable the javaagent only if it exists
ENTRYPOINT ["sh", "-c", "if [ -f /app/opentelemetry-javaagent.jar ]; then AGENT=\"-javaagent:/app/opentelemetry-javaagent.jar\"; else AGENT=\"\"; fi; exec java $JAVA_OPTS $AGENT -jar /app/producer.jar"]
