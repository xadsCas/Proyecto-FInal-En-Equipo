/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Configuration;

import com.example.demo.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author ernes
 */
@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final CustomUserDetailsService userDetailsService;

    public WebSecurity(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  
            .authorizeHttpRequests((requests) -> requests
                // Permite acceso sin autenticación a los endpoints /Rest/**
                .requestMatchers("/Rest/**").permitAll()
                // Resto de las solicitudes requieren autenticación
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                // Página de login
                .loginPage("/Login")
                .permitAll()  // Permite acceso a la página de login sin autenticación
            )
            .logout((logout) -> logout.permitAll());  // Permite logout sin autenticación

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
