package com.demo.spring.websocket.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Greeting implements Serializable {
    @JsonProperty("name")
    private String content;
}
