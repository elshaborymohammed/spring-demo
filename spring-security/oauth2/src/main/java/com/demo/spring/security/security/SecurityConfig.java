package com.demo.spring.security.security;

import com.demo.spring.security.security.authentication.AuthenticationFilter;
import com.demo.spring.security.security.authentication.CustomAuthenticationFailureHandler;
import com.demo.spring.security.security.authorization.AuthExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationProvider authenticationProvider;
    private final AuthExceptionEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        super.configure(auth);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Enable CORS and disable CSRF
//        http = http.cors().and().csrf().disable();
//
//        http = http.oauth2ResourceServer(it -> {
//            it.opaqueToken(o -> {
//                o.introspectionUri("http://localhost:8080/oauth/check_token");
//                o.introspectionClientCredentials("resourceserver", "resourceserversecret");
//            });
//        });
//
//        // Set session management to stateless
//        http = http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and();
//
//        // Set unauthorized requests exception handler
//        http = http
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and();
//
//        // Set permissions on endpoints
//        http.authorizeRequests()
//                // Our public endpoints
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers("/h2-console/**", "/oauth/**", "/oauth/token/**").permitAll()
//                // Our private endpoints
//                .anyRequest().authenticated().and();
//
//        // Add JWT token filter
//        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
//        authenticationFilter.setFilterProcessesUrl("/login");
//        http.addFilter(authenticationFilter);
////        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
