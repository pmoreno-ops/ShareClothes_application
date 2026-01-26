package com.example.backend.service;

import com.example.backend.models.ImagenProducto;
import com.example.backend.repository.ImagenProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenProductoService {

    private final ImagenProductoRepository imagenProductoRepository;

    @Autowired
    public ImagenProductoService(ImagenProductoRepository imagenProductoRepository) {
        this.imagenProductoRepository = imagenProductoRepository;
    }

    // Obtener todas las imágenes
    public List<ImagenProducto> obtenerTodas() {
        return imagenProductoRepository.findAll();
    }

    // Obtener imagen por ID
    public Optional<ImagenProducto> obtenerPorId(Long id) {
        return imagenProductoRepository.findById(id);
    }

    // Guardar una nueva imagen
    public ImagenProducto guardar(ImagenProducto imagen) {
        return imagenProductoRepository.save(imagen);
    }

    // Actualizar imagen existente
    public ImagenProducto actualizar(Long id, ImagenProducto imagenActualizada) {
        return imagenProductoRepository.findById(id)
                .map(imagen -> {
                    imagen.setProducto(imagenActualizada.getProducto());
                    imagen.setUrl(imagenActualizada.getUrl());
                    return imagenProductoRepository.save(imagen);
                })
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada con ID: " + id));
    }

    // Eliminar imagen
    public void eliminar(Long id) {
        if (imagenProductoRepository.existsById(id)) {
            imagenProductoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Imagen no encontrada con ID: " + id);
        }
    }

    // Obtener imágenes por producto
    public List<ImagenProducto> obtenerPorProductoId(Long productoId) {
        return imagenProductoRepository.findByProductoId(productoId);
    }

    public List<ImagenProducto> obtenerPorProducto(Long productoId) {
        return List.of();
    }
}
