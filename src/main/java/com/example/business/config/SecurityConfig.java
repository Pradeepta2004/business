package com.example.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection for testing purposes
                .authorizeRequests(authorize ->
                        authorize
                                .requestMatchers("/auth/register", "/auth/login").permitAll() // Allow access to auth endpoints
                                .requestMatchers("/api/inventory/**").permitAll() // Allow access to all inventory API endpoints
                                .anyRequest().authenticated() // Require authentication for all other endpoints
                );

        return http.build();
    }
}
