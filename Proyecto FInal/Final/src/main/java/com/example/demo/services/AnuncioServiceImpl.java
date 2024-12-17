/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.AnuncioRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.Anuncio;

/**
 *
 * @author ernes
 */

   
@Service
public class AnuncioServiceImpl implements AnuncioService{
    
    @Autowired
    public AnuncioRepository anuncioRepository;
    
    org.slf4j.Logger logger= LoggerFactory.getLogger(AnuncioServiceImpl.class);
    
    @Override
    public List<Anuncio> GetAllAnuncios(){
        List<Anuncio> Anuncio=anuncioRepository.findAll();
        return Anuncio;
    }
    
    @Override
    public Optional<Anuncio> getAnuncioId(Long id){
        Optional<Anuncio> searchAnuncio=anuncioRepository.findById(id);
        if(searchAnuncio.isPresent()){
            logger.error("Producto encontrado!!");
            return searchAnuncio;
        }else{
            logger.error("No se han encontrado anuncios con el id" + id );
            throw new NoSuchElementException("No");
        }
    }
    
    @Override
    public Optional<Anuncio> CreateAnuncio(Anuncio anuncio){
        Optional<Anuncio> optAnuncio=Optional.of(anuncio);
        if(optAnuncio.isPresent()){
            Anuncio newAnuncio= new Anuncio();
            newAnuncio.setNombre(anuncio.getNombre());
            newAnuncio.setDescripcion(anuncio.getDescripcion());
            newAnuncio.setAsunto(anuncio.getAsunto());
            anuncioRepository.save(newAnuncio);
            logger.info("Se ha creado el producto correctamente");
            Optional<Anuncio>rturnOptAnuncio=Optional .of(newAnuncio);
            return rturnOptAnuncio;
        }else{
            logger.error("No se ha recibido un producto para crear");
            throw new NoSuchElementException("No se ha recibido un Producto para crear");
        }
    }
    
        
   
} 

