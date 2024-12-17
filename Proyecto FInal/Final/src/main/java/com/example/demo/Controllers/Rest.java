/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controllers;

import com.example.demo.dao.AnuncioRepository;
import com.example.demo.dto.Anuncio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ernes
 */

@RestController
@RequestMapping("/Rest")
public class Rest {
    
    @Autowired
    private AnuncioRepository anuncioRepository;

    @GetMapping("/anuncio")
    public ResponseEntity<List<Anuncio>> getAllAnuncios() {
        return new ResponseEntity<>(anuncioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/anuncio/{id}")
    public ResponseEntity<Anuncio> getAnuncioById(@PathVariable Long id) {
        return anuncioRepository.findById(id)
                .map(anuncio -> new ResponseEntity<>(anuncio, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/crear")
    public ResponseEntity<Anuncio> createAnuncio(@RequestBody Anuncio anuncio) {
        Anuncio savedAnuncio = anuncioRepository.save(anuncio);
        return new ResponseEntity<>(savedAnuncio, HttpStatus.CREATED);
    }
}
