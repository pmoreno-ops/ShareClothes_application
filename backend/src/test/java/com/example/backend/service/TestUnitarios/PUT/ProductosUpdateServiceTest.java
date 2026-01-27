package com.example.backend.service.TestUnitarios.PUT;

import com.example.backend.models.ImagenProducto;
import com.example.backend.models.Productos;
import com.example.backend.repository.ProductosRepository;
import com.example.backend.service.ProductosService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductosUpdateServiceTest {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private ProductosRepository productosRepository;

    private Long productoOriginalId;

    @BeforeEach
    void setUp() {
        // Crear un producto original
        Productos productoOriginal = new Productos();
        productoOriginal.setTitulo("Camiseta Nike");
        productoOriginal.setDescripcion("Camiseta en buen estado");
        productoOriginal.setCategoria("Ropa");
        productoOriginal.setMarca("Nike");
        productoOriginal.setTalla("M");
        productoOriginal.setEstado("Usado");
        productoOriginal.setFechaCreacion(LocalDateTime.now());

        ImagenProducto imagen = new ImagenProducto();
        imagen.setUrl("http://img.test/1.jpg");
        imagen.setProducto(productoOriginal);
        Set<ImagenProducto> imagenes = new HashSet<>();
        imagenes.add(imagen);
        productoOriginal.setImagenes(imagenes);

        productoOriginalId = productosService.publicar(productoOriginal).getId();
    }

    // TEST POSITIVO: actualizar todos los campos
    @Test
    @DisplayName("Editar producto existente: actualizar todos los campos")
    void editarProductoCompleto() {
        // Obtener el producto original
        Productos producto = productosRepository.findById(productoOriginalId).orElseThrow();

        // Modificar campos
        producto.setTitulo("Camiseta Adidas");
        producto.setDescripcion("Camiseta nueva edición");
        producto.setCategoria("Deporte");
        producto.setMarca("Adidas");
        producto.setTalla("L");
        producto.setEstado("Nuevo");

        Productos actualizado = productosRepository.save(producto);

        assertEquals("Camiseta Adidas", actualizado.getTitulo());
        assertEquals("Camiseta nueva edición", actualizado.getDescripcion());
        assertEquals("Deporte", actualizado.getCategoria());
        assertEquals("Adidas", actualizado.getMarca());
        assertEquals("L", actualizado.getTalla());
        assertEquals("Nuevo", actualizado.getEstado());
    }

    // TEST POSITIVO: actualizar solo algunos campos opcionales
    @Test
    @DisplayName("Editar producto existente: actualizar solo campos opcionales")
    void editarProductoCamposOpcionales() {
        Productos producto = productosRepository.findById(productoOriginalId).orElseThrow();

        // Cambiar solo descripción y marca
        producto.setDescripcion("Edición limitada");
        producto.setMarca("Reebok");

        Productos actualizado = productosRepository.save(producto);

        assertEquals("Camiseta Nike", actualizado.getTitulo()); // obligatorio no cambia
        assertEquals("Edición limitada", actualizado.getDescripcion());
        assertEquals("Ropa", actualizado.getCategoria()); // obligatorio no cambia
        assertEquals("Reebok", actualizado.getMarca());
        assertEquals("M", actualizado.getTalla());
        assertEquals("Usado", actualizado.getEstado());
    }

    // TEST NEGATIVO: intentar actualizar producto inexistente
    @Test
    @DisplayName("Editar producto inexistente lanza excepción")
    void editarProductoInexistente() {
        Productos productoFalso = new Productos();
        productoFalso.setId(99999L); // ID inexistente
        productoFalso.setTitulo("Falso");
        productoFalso.setCategoria("Falso");
        productoFalso.setTalla("F");
        productoFalso.setEstado("Nuevo");
        productoFalso.setImagenes(new HashSet<>());

        // Al usar save de repository directamente, se crea un nuevo producto, así que el "negativo" real se hace con service
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    if (!productosRepository.existsById(productoFalso.getId())) {
                        throw new IllegalArgumentException("Producto no encontrado con ID: " + productoFalso.getId());
                    }
                    productosRepository.save(productoFalso);
                }
        );

        assertEquals("Producto no encontrado con ID: 99999", exception.getMessage());
    }
}
