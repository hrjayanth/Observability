# Observability with OpenTelemetry and Grafana

This repository provides a comprehensive guide to setting up observability for your applications using OpenTelemetry and Grafana. It includes instructions for instrumenting your code, collecting metrics and traces, and visualizing the data in Grafana dashboards.

## Table of Contents
- [Setup Instructions](#setup-instructions)
- [Troubleshooting](#troubleshooting)


## Setup Instructions
1. Run the following command to set up the environment:
   ```bash
   docker-compose up -d
   ```
2. Access Grafana at `http://localhost:3000` (default credentials: admin/admin).
3. Configure Grafana to connect to your Tempodb instance.
4. Build the application using Gradle:
   ```bash
   ./gradlew clean build
   ```

### Application Instrumentation
1. opentelemetry-javaagent.jar is already part of the setup.
2. Add the following JVM argument to your application startup command:
   ```bash
   -javaagent:/path/to/opentelemetry-javaagent.jar
   ```
3. Add these environment variables to configure traces export to OTEL Collector:
   ```bash
    export OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4317
    export OTEL_TRACES_EXPORTER=otlp
    export OTEL_METRICS_EXPORTER=none
    export OTEL_LOGS_EXPORTER=none
    export OTEL_EXPORTER_OTLP_PROTOCOL=grpc
    export OTEL_SERVICE_NAME=my-application
    export OTEL_RESOURCE_ATTRIBUTES=deployment.environment=dev,service.version=1.0.0
    export OTEL_TRACES_SAMPLER=always_on
   ```
4. Run the application, and you should see the traces in Grafana.

## Manual Instrumentation

In case you want to manually instrument your application, you can either do it programmatically or use annotations. Easiest way is to use annotations. you can use `@WithSpan` to create spans around methods.

```java
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@WithSpan(kind = SpanKind.INTERNAL, value = "mySpan")
public void myMethod() {
    // method implementation
}
```

## Troubleshooting
- If you don't see any traces in Grafana, ensure that your application is correctly instrumented and that the OTEL Collector is running.
- Make sure your application logs the below statement:
    ```
    INFO  [io.opentelemetry.javaagent.tooling.VersionLogger] (main) OpenTelemetry Java Agent version: 2.20.0
    ```
- Check the logs of the OTEL Collector for any errors.
- Ensure that the environment variables are correctly set in your application.
- Verify that your application is sending data to the correct endpoint.
- Make sure that the Grafana data source is correctly configured to connect to Tempodb.
