package com.example.backend.service;

import com.example.backend.DTO.ProductoDTO;
import com.example.backend.models.Mensaje;
import com.example.backend.models.Productos;
import com.example.backend.repository.MensajeRepository;
import com.example.backend.repository.ProductosRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductosService {

    private final ProductosRepository productoRepository;

    public ProductosService(ProductosRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Productos publicar(Productos producto) {

        if (producto.getTitulo() == null || producto.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }

        if (producto.getCategoria() == null || producto.getCategoria().isBlank()) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }

        if (producto.getTalla() == null || producto.getTalla().isBlank()) {
            throw new IllegalArgumentException("La talla es obligatoria");
        }

        if (producto.getEstado() == null || producto.getEstado().isBlank()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }

        if (producto.getImagenes() == null || producto.getImagenes().isEmpty()) {
            throw new IllegalArgumentException("Debe incluir al menos una imagen");
        }

        producto.setFechaCreacion(LocalDateTime.now());

        return productoRepository.save(producto);
    }


    public List<ProductoDTO> obtenerProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private ProductoDTO mapToDTO(Productos producto) {
        String imagenPrincipal = producto.getImagenes() != null && !producto.getImagenes().isEmpty()
                ? producto.getImagenes().iterator().next().getUrl()
                : null;

        return new ProductoDTO(
                producto.getId(),
                producto.getTitulo(),
                producto.getDescripcion(),
                producto.getCategoria(),
                producto.getMarca(),
                imagenPrincipal,
                producto.getFechaCreacion()
        );
    }
}
