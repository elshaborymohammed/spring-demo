package com.demo.spring.cloud.stream.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@EnableBinding(Sink.class)
public class Consumer {

    @StreamListener(Sink.INPUT)
    public void getMessage(String message) {
        log.info(message);
    }
}
