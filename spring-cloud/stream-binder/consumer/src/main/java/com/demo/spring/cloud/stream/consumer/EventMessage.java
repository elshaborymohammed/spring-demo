package com.demo.spring.cloud.stream.consumer;

import lombok.Data;

import java.io.Serializable;

@Data
public class EventMessage implements Serializable {
    private String author;
    private String message;

    @Override
    public String toString() {
        return "\033[35m" + "KafkaMessage{" +
                "author='" + author + '\'' +
                ", message='" + message + '\'' +
                '}' + "\033[0;39m";
    }
}
