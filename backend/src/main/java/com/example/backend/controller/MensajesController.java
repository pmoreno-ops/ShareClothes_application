package com.example.backend.controller;

import com.example.backend.models.Mensaje;
import com.example.backend.service.MensajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
@CrossOrigin(origins = "*")
public class MensajesController {

    @Autowired
    private MensajesService mensajesService;

    @GetMapping
    public List<Mensaje> obtenerTodos() {
        return mensajesService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Mensaje obtenerPorId(@PathVariable Long id) {
        return mensajesService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con ID: " + id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Mensaje> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return mensajesService.obtenerPorUsuario(usuarioId);
    }

    @PostMapping
    public Mensaje crear(@RequestBody Mensaje mensaje) {
        return mensajesService.guardar(mensaje);
    }

    @PutMapping("/{id}")
    public Mensaje actualizar(@PathVariable Long id, @RequestBody Mensaje mensajeActualizado) {
        return mensajesService.actualizar(id, mensajeActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        mensajesService.eliminar(id);
    }
}
