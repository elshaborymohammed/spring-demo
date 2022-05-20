package com.demo.spring.security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class JwtUtil {
    private final Algorithm algorithm = Algorithm.HMAC256("secret");

    public User parseToken(String token) {
        try {

            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            log.info(decodedJWT.getClaim("roles")
                    .asList(String.class)
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()).toString());

            return new User(
                    decodedJWT.getSubject(),
                    "null",
                    decodedJWT.getClaim("roles")
                            .asList(String.class)
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );

        } catch (JWTVerificationException | ClassCastException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public String generateAccessToken(Authentication user) {
        return JWT.create()
                .withSubject(String.valueOf(user.getPrincipal()))
                //.withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(algorithm);
    }

    public String generateRefreshToken(Authentication user) {
        return JWT.create()
                .withSubject(String.valueOf(user.getPrincipal()))
                //.withIssuer(request.getRequestURL().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(algorithm);
    }
}
