package com.demo.spring.cloud.stream.producer;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class EventMessage implements Serializable {
    @Builder.Default
    private String author = "test";
    private String message;

    @Override
    public String toString() {
        return "\033[35m" + "Message{" +
                "name='" + author + '\'' +
                ", message='" + message + '\'' +
                '}' + "\033[0;39m";
    }
}
