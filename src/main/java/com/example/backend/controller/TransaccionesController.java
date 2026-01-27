package com.example.backend.controller;

import com.example.backend.models.Transaccion;
import com.example.backend.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionesController {

    @Autowired
    private TransaccionService transaccionesService;

    @GetMapping
    public List<Transaccion> obtenerTodas() {
        return transaccionesService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Transaccion obtenerPorId(@PathVariable Long id) {
        return transaccionesService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada con ID: " + id));
    }

    @PostMapping
    public Transaccion crear(@RequestBody Transaccion transaccion) {
        return transaccionesService.guardar(transaccion);
    }

    @PutMapping("/{id}")
    public Transaccion actualizar(@PathVariable Long id, @RequestBody Transaccion transaccionActualizada) {
        return transaccionesService.actualizar(id, transaccionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        transaccionesService.eliminar(id);
    }
}

