package com.example.backend.controller;

import com.example.backend.models.Usuarios;
import com.example.backend.service.UsuariosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {

    @Autowired
    private UsuariosServices usuariosService;

    @GetMapping
    public List<Usuarios> obtenerTodos() {
        return usuariosService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Usuarios obtenerPorId(@PathVariable Long id) {
        return (Usuarios) usuariosService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @PostMapping
    public Usuarios crear(@RequestBody Usuarios usuario) {
        return usuariosService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public Usuarios actualizar(@PathVariable Long id, @RequestBody Usuarios usuarioActualizado) {
        return usuariosService.actualizar(id, usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuariosService.eliminar(id);
    }
}

