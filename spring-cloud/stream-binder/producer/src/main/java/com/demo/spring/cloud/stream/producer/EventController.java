package com.demo.spring.cloud.stream.producer;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@EnableBinding(Source.class)
public class EventController {

    private final Source source;

    @Autowired
    public EventController(Source source) {
        this.source = source;
    }

    @PostMapping("/publish")
    public String writeMessageToTopic(@RequestParam("author") String author) {
        EventMessage kafkaMessage = new EventMessage(author, "Hi " + author);
        boolean send = source.output().send(MessageBuilder.withPayload(kafkaMessage).build());
        return "Published Successfully sent! " + send + "\n" + kafkaMessage;
    }
}


