/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Configuration;


import com.example.demo.dao.UserRepository;
import com.example.demo.dto.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin1").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin1");
                admin.setPassword(passwordEncoder.encode("12345"));
                admin.setRole("User");
                userRepository.save(admin);
            }
        };
    }
}
