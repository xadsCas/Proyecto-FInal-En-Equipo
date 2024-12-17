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



@Entity
@Table(name="Anuncios")
public class Anuncio {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idAnuncio;
    @Column(name="Nombre")
    private String nombre;
    @Column(name="Asunto")
    private String asunto;
    @Column(name="Descripcion")
    private String descripcion;
    
    public Anuncio(Long idAnuncio,String nombre,String asunto,String descripcion){
        this.idAnuncio=idAnuncio;
        this.nombre=nombre;
        this.asunto=asunto;
        this.descripcion=descripcion;
    }

    public Anuncio() {
       
    }
    
    public Long getId(){
        return idAnuncio;
    }
    
    public void setId(Long idAnuncio){
        this.idAnuncio=idAnuncio;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public String getAsunto(){
     
        return asunto;
    }
    
    public void setAsunto(String asunto){
      this.asunto=asunto;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    
  @Override
  public String toString(){
     return "Anuncio[id= " + idAnuncio + ", "
              + "Nombre= "+ nombre +", Asunto= " + asunto+", Descripcion= "
              +descripcion;
  }
    
}
