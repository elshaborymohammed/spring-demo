package com.demo.spring.security.security.authentication;

import com.demo.spring.security.security.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("username: {}, password: {}", username, password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUtil jwtUtil = new JwtUtil();
        String accessToken = jwtUtil.generateAccessToken(authResult);
        String refreshToken = jwtUtil.generateRefreshToken(authResult);

        response.setHeader("access_token", accessToken);
        response.setHeader("refresh_token", refreshToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> token = Map.of(
                "access_token", accessToken,
                "refresh_token", refreshToken
        );
        new ObjectMapper().writeValue(response.getOutputStream(), token);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.error("unsuccessfulAuthentication - username: {}, password: {}", username, password, failed);
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
