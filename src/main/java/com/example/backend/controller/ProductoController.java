package com.example.backend.controller;

import com.example.backend.DTO.ProductoDTO;
import com.example.backend.models.Productos;
import com.example.backend.repository.ProductosRepository;
import com.example.backend.service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductosService productosService;
    private final ProductosRepository productoRepository;

    public ProductoController(ProductosService productosService,
                              ProductosRepository productoRepository) {
        this.productosService = productosService;
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos (con DTO)
    @GetMapping("/all")
    public List<ProductoDTO> getAllProductos() {
        return productosService.obtenerProductos();
    }

    @PostMapping
    public ResponseEntity<Productos> createProducto(@Valid @RequestBody ProductoDTO dto) {
        Productos producto = new Productos();
        producto.setTitulo(dto.getTitulo());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCategoria(dto.getCategoria());
        producto.setGenero(dto.getGenero());
        producto.setEstado(dto.getEstado());
        producto.setMarca(dto.getMarca());
        producto.setTalla(dto.getTalla());
        producto.setColor(dto.getColor());
        producto.setFechaCreacion(LocalDateTime.now());

        Productos guardado = productoRepository.save(producto);
        return ResponseEntity.ok(guardado);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Productos> getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> updateProducto(@PathVariable Long id,
                                                    @Valid @RequestBody ProductoDTO dto) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setTitulo(dto.getTitulo());
                    producto.setDescripcion(dto.getDescripcion());
                    producto.setCategoria(dto.getCategoria());
                    producto.setGenero(dto.getGenero());
                    producto.setEstado(dto.getEstado());
                    producto.setMarca(dto.getMarca());
                    producto.setTalla(dto.getTalla());
                    producto.setColor(dto.getColor());
                    Productos actualizado = productoRepository.save(producto);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
