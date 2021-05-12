package com.demo.spring.cloud.stream.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Processor.class)
public class Publish {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public EventMessage publish(EventMessage log) {
        return new EventMessage("Test", String.format("[1]: %s", log.getMessage()));
    }
}