package com.demo.spring.websocket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Builder.Default
    @JsonProperty
    private String author = "author";
    @Builder.Default
    @JsonProperty
    private String message = "message";
}
