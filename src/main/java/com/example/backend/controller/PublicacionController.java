package com.example.backend.controller;

import com.example.backend.models.Publicacion;
import com.example.backend.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin(origins = "*")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public List<Publicacion> obtenerTodas() {
        return publicacionService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Publicacion obtenerPorId(@PathVariable Long id) {
        return publicacionService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada con ID: " + id));
    }

    @PostMapping
    public Publicacion crear(@RequestBody Publicacion publicacion) {
        return publicacionService.guardar(publicacion);
    }

    @PutMapping("/{id}")
    public Publicacion actualizar(@PathVariable Long id, @RequestBody Publicacion publicacionActualizada) {
        return publicacionService.actualizar(id, publicacionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        publicacionService.eliminar(id);
    }
}
