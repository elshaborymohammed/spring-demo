package com.demo.spring.security.security.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException {
        log.error(authException.getMessage(), authException);

        String key;
        String exceptionMsg = authException.getMessage();

        if (exceptionMsg.startsWith("Full authentication")) {
            key = "Full authentication is required to access this resource";
        } else {
            key = "Invalid access token";
        }

        String fields = "Token";
        ResponseError error = ResponseError.builder()
                .code(HttpStatus.UNAUTHORIZED)
                .message(String.format("%s [%s]", key, fields))
                .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        adjustHeader(response);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), error);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

    private void adjustHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " + "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,X-Access-Token,X-Expires-In,X-Token-Type,X-Refresh-Token");
        response.setHeader("Access-Control-Expose-Headers", "X-User-ID,X-Access-Token,X-Token-Type,X-Expires-In,X-Refresh-Token,X-Scope");
    }
}