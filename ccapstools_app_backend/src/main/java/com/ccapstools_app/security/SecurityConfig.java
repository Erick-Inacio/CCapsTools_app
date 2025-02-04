package com.ccapstools_app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private FirebaseAuthenticationFilter firebaseAuthenticationFilter;

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .httpBasic(basic -> basic.disable())
                        .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(auth -> auth
                                        .requestMatchers(
                                                        "/v3/api-docs/**",
                                                        "/swagger-ui/**",
                                                        "/api/test/**")
                                        .permitAll()
                                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                        .requestMatchers("/api/user/**").hasAnyRole("ADMIN", "STUDENT")
                                        .requestMatchers("/speaker/**").hasAnyRole("ADMIN", "SPEAKER")
                                        .anyRequest().authenticated())
                        .addFilterBefore(firebaseAuthenticationFilter,
                                        UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
