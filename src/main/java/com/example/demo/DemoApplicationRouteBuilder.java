package com.example.demo;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Kafka Route for creating messages and pushing into the topic
        from("timer:time?period=5000")
            .process(new Processor(){
                @Override
                public void process(Exchange exchange) throws Exception {
                    String output = System.getenv("TestString");
                    if (output == null) {
                        output = "Test";
                    }
                    exchange.getIn().setBody(output);
                }
            })
            .log(LoggingLevel.INFO, "CamelTest", "${body}")
            .to("mock:done");
    }
}