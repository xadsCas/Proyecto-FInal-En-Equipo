/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import com.example.demo.dto.Anuncio;
/**
 *
 * @author ernes
 */

public interface AnuncioService {
    List<Anuncio> GetAllAnuncios();
    Optional<Anuncio> getAnuncioId(Long idAnuncio);
    Optional<Anuncio> CreateAnuncio(Anuncio anuncio);
   
}
