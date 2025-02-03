package com.ccapstools_app.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .httpBasic(basic -> basic.disable())
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("api/auth/signin", "api/v3/api-docs/**")
                                                .permitAll()
                                                .requestMatchers("/api/test/validate-token").permitAll() // 🔓 Permite
                                                                                                         // teste sem
                                                                                                         // autenticação
                                                .requestMatchers("/api/user/**").authenticated()
                                                .anyRequest().authenticated())
                                .addFilterBefore(new FirebaseAuthenticationFilter(),
                                                UsernamePasswordAuthenticationFilter.class)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                // 🔥 Adiciona um filtro para exibir os cabeçalhos recebidos
                http.addFilterBefore(new OncePerRequestFilter() {
                        @Override
                        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                        FilterChain filterChain)
                                        throws ServletException, IOException {
                                System.out.println("🔍 Cabeçalhos recebidos:");
                                request.getHeaderNames().asIterator().forEachRemaining(header -> System.out
                                                .println(header + ": " + request.getHeader(header)));
                                filterChain.doFilter(request, response);
                        }
                }, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
