package com.example.backend.controller;

import com.example.backend.models.Transaccion;
import com.example.backend.models.Usuarios;
import com.example.backend.models.Valoracion;
import com.example.backend.service.TransaccionService;
import com.example.backend.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ValoracionesController {

    @Autowired
    private ValoracionService valoracionesService;

    @Autowired
    private TransaccionService transaccionService;

    // GET: todas las valoraciones
    @GetMapping("/valoraciones")
    public List<Valoracion> obtenerTodas() {
        return valoracionesService.obtenerTodas();
    }

    // GET: valoración por ID
    @GetMapping("/valoraciones/{id}")
    public Valoracion obtenerPorId(@PathVariable Long id) {
        return valoracionesService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Valoración no encontrada con ID: " + id));
    }

    @PostMapping("/usuarios/{usuarioId}/valoraciones")
    public Valoracion crear(@PathVariable Long usuarioId, @RequestBody Valoracion valoracion) {
        // Set usuario evaluado
        Usuarios evaluado = new Usuarios();
        evaluado.setId(usuarioId);
        valoracion.setEvaluado(evaluado);

        // Verificar transacción
        if (valoracion.getTransaccion() != null) {
            Long transaccionId = valoracion.getTransaccion().getId();
            Transaccion transaccion = transaccionService.obtenerPorId(transaccionId)
                    .orElseThrow(() -> new RuntimeException("Transacción no encontrada con ID: " + transaccionId));
            valoracion.setTransaccion(transaccion);
        }

        return valoracionesService.guardar(valoracion);
    }

    // GET: todas las valoraciones de un usuario
    @GetMapping("/usuarios/{usuarioId}/valoraciones")
    public List<Valoracion> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return valoracionesService.obtenerPorUsuario(usuarioId);
    }

    // PUT: actualizar valoración
    @PutMapping("/valoraciones/{id}")
    public Valoracion actualizar(@PathVariable Long id, @RequestBody Valoracion valoracionActualizada) {
        return valoracionesService.actualizar(id, valoracionActualizada);
    }

    // DELETE: eliminar valoración
    @DeleteMapping("/valoraciones/{id}")
    public void eliminar(@PathVariable Long id) {
        valoracionesService.eliminar(id);
    }
}
