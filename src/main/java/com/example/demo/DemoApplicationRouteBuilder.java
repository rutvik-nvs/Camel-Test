package com.example.demo;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Kafka Route for creating messages and pushing into the topic
        from("timer:time?period=5000")
            .setBody(constant("Test"))
            .log(LoggingLevel.INFO, "CamelTest", "${body}")
            .to("mock:done");
    }
}