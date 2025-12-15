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

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductosService {

    private final ProductosRepository productoRepository;

    public ProductosService(ProductosRepository productoRepository) {
        this.productoRepository = productoRepository;
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
