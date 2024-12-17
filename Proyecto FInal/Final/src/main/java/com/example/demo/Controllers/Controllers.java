/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controllers;
import com.example.demo.dao.AnuncioRepository;
import com.example.demo.dto.Anuncio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.services.AnuncioServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author ernes
 */
@Controller
public class Controllers {

    @Autowired
    private AnuncioRepository anuncioRepository;
    
    @Autowired
    private AnuncioServiceImpl anuncioServiceImpl;

    
    @GetMapping("/new")
    public String mostrarFromulario(@ModelAttribute("anuncio") Anuncio anuncio,Model model,HttpSession session){
        String nombre = (String) session.getAttribute("nombre");
    
    if (nombre != null) {
        anuncio.setNombre(nombre); // Precarga el nombre desde la sesión
    }
    
    model.addAttribute("anuncio", anuncio); // Añade el objeto al modelo
    
    return "new";
    }
    
    @GetMapping({"/", "/Home", "/home"})
public String mostrarHome(HttpSession session, Model model) {
    // Obtener lista de anuncios
    List<Anuncio> listaAnuncios = anuncioRepository.findAll();
    model.addAttribute("anuncios", listaAnuncios);

    // Comprobar si es la primera vez que el usuario visita la página
    if (session.getAttribute("visited") == null) {
        // Si es la primera vez, mostrar el anuncio
        model.addAttribute("showAd", true);
        session.setAttribute("visited", true);  // Marcar como visitado
    } else {
        // Si ya ha visitado, no mostrar el anuncio
        model.addAttribute("showAd", false);
    }

    return "Home";  // Nombre de la vista HTML
}


@GetMapping("/Resultado")
public String mostrarResultado(){
    return "Resultado";
}    
    
     @GetMapping("/anuncio/{id}")
    public String verAnuncio(@PathVariable Long id, Model model) {
        // Buscar el anuncio por id
        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Anuncio no encontrado: " + id));

        // Pasar el anuncio al modelo
        model.addAttribute("anuncio", anuncio);

        // Redirigir a la vista "anuncio"
        return "anuncio";
    }
    
    
   @PostMapping("/new")
    public String crearAnuncio(@ModelAttribute Anuncio anuncio,Model model, HttpSession session) {
        try {
        // Guarda el nombre del anuncio en la sesión
        session.setAttribute("nombre", anuncio.getNombre());

        // Llama al servicio para guardar el anuncio
        anuncioServiceImpl.CreateAnuncio(anuncio);

        return "redirect:/Resultado"; // Redirige a la página de resultado
    } catch (Exception e) {
        // Opcional: manejar excepciones específicas
        e.printStackTrace();
        return "redirect:/Resultado?error=true"; // Redirige con error
    }
    }
    //Controladores para ver si ya se visito la sesion
   

    @GetMapping("/setVisited")
    public String setVisited(HttpSession session) {
        // Establecer en la sesión que el usuario ha visitado
        session.setAttribute("visited", "true");
        return "redirect:/";
    }
    
}
