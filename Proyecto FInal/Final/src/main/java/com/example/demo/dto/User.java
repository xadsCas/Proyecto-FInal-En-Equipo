/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="username")
    private String username;
   
    @Column(name="password")
    private String password;
    
    private String role;
   
    public User(){}
    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public String getPassword(){
        return password;
    }    
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devuelve una lista con los roles del usuario
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
