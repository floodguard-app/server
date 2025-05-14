package com.floodguard.floodguard_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Desabilita CSRF para APIs REST
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/usuarios/login",
                    "/api/usuarios/cadastrar",
                    "/api/usuarios",  // Libera POST e GET
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()  // Permite acesso sem autenticação
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.disable())  // Desabilita login por formulário
            .httpBasic(basic -> basic.disable());  // Desabilita autenticação básica

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
