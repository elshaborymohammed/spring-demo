package com.demo.spring.security.security.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ResponseError {
    private HttpStatus code;
    private String message;
}
