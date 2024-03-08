package com.tienda.controller;

import com.tienda.domain.Articulo;
import com.tienda.service.ArticuloService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/articulo")
public class ArticuloController {
    
    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var articulos = articuloService.getArticulos(false);
        model.addAttribute("articulos", articulos);
        model.addAttribute("totalArticulos", articulos.size());
        return "/articulo/listado";
    }
    
    @GetMapping("/nuevo")
    public String articuloNuevo(Articulo articulo) {
        return "/articulo/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String articuloGuardar(Articulo articulo,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            articuloService.save(articulo);
            articulo.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "articulo", 
                            articulo.getIdArticulo()));
        }
        articuloService.save(articulo);
        return "redirect:/articulo/listado";
    }

    @GetMapping("/eliminar/{idArticulo}")
    public String articuloEliminar(Articulo articulo) {
        articuloService.delete(articulo);
        return "redirect:/articulo/listado";
    }

    @GetMapping("/modifica/{idArticulo}")
    public String articuloModificar(Articulo articulo, Model model) {
        articulo = articuloService.getArticulo(articulo);
        model.addAttribute("articulo", articulo);
        return "/articulo/modifica";
    }
}