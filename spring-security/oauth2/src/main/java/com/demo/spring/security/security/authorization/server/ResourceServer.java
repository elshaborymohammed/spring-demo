package com.demo.spring.security.security.authorization.server;

import com.demo.spring.security.security.authentication.AuthenticationFilter;
import com.demo.spring.security.security.authorization.AuthExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServer extends ResourceServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final AuthExceptionEntryPoint authenticationEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and();

        // Set permissions on endpoints
        http.authorizeRequests()
                // Our public endpoints
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/h2-console/**", "/hi/**").permitAll()
                // Our private endpoints
                .anyRequest().authenticated().and();

        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
        authenticationFilter.setFilterProcessesUrl("/login");
        http.addFilter(authenticationFilter);
    }
}
