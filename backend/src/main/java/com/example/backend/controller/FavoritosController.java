package com.example.backend.controller;

import com.example.backend.models.Favoritos;
import com.example.backend.service.FavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritosController {

    @Autowired
    private FavoritosService favoritosService;

    @GetMapping
    public List<Favoritos> obtenerTodos() {
        return favoritosService.obtenerTodos();
    }

    @GetMapping("/usuario/{userId}")
    public List<Favoritos> obtenerPorUsuario(@PathVariable Long userId) {
        return favoritosService.obtenerPorUsuario(userId);
    }

    @PostMapping
    public Favoritos crear(@RequestBody Favoritos favorito) {
        return favoritosService.guardar(favorito);
    }

    @DeleteMapping("/{userId}/{publicacionId}")
    public void eliminar(@PathVariable Long userId, @PathVariable Long publicacionId) {
        favoritosService.eliminar(userId, publicacionId);
    }
}

