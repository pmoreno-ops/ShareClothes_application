package com.example.backend.controller;

import com.example.backend.models.ImagenProducto;
import com.example.backend.service.ImagenProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin(origins = "*")
public class ImagenesProductoController {

    @Autowired
    private ImagenProductoService imagenesService;

    @GetMapping
    public List<ImagenProducto> obtenerTodas() {
        return imagenesService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ImagenProducto obtenerPorId(@PathVariable Long id) {
        return imagenesService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada con ID: " + id));
    }

    @GetMapping("/producto/{productoId}")
    public List<ImagenProducto> obtenerPorProducto(@PathVariable Long productoId) {
        return imagenesService.obtenerPorProducto(productoId);
    }

    @PostMapping
    public ImagenProducto crear(@RequestBody ImagenProducto imagen) {
        return imagenesService.guardar(imagen);
    }

    @PutMapping("/{id}")
    public ImagenProducto actualizar(@PathVariable Long id, @RequestBody ImagenProducto imagenActualizada) {
        return imagenesService.actualizar(id, imagenActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        imagenesService.eliminar(id);
    }
}

