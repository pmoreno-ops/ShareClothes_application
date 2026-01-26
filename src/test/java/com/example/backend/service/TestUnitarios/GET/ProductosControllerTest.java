package com.example.backend.service.TestUnitarios.GET;

import com.example.backend.models.ImagenProducto;
import com.example.backend.models.Productos;
import com.example.backend.repository.ProductosRepository;
import com.example.backend.service.ProductosService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductosControllerTest {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private ProductosRepository productosRepository;

    private Long productoCompletoId;
    private Long productoMinimoId;

    @BeforeEach
    void cargarDatos() {
        // Producto con todos los datos
        Productos productoCompleto = new Productos();
        productoCompleto.setTitulo("Camiseta Nike");
        productoCompleto.setDescripcion("Camiseta en buen estado");
        productoCompleto.setCategoria("Ropa");
        productoCompleto.setMarca("Nike");
        productoCompleto.setTalla("M");
        productoCompleto.setEstado("Usado");
        productoCompleto.setFechaCreacion(LocalDateTime.now());

        // Imagen vinculada
        ImagenProducto imagen = new ImagenProducto();
        imagen.setUrl("http://img.test/1.jpg");
        imagen.setProducto(productoCompleto);
        Set<ImagenProducto> imagenes = new HashSet<>();
        imagenes.add(imagen);
        productoCompleto.setImagenes(imagenes);

        productoCompletoId = productosService.publicar(productoCompleto).getId();

        // Producto con solo datos obligatorios
        Productos productoMinimo = new Productos();
        productoMinimo.setTitulo("Pantalón Adidas");
        productoMinimo.setCategoria("Ropa");
        productoMinimo.setTalla("L");
        productoMinimo.setEstado("Nuevo");

        // Imagen obligatoria
        ImagenProducto imagen2 = new ImagenProducto();
        imagen2.setUrl("http://img.test/2.jpg");
        imagen2.setProducto(productoMinimo);
        Set<ImagenProducto> imagenes2 = new HashSet<>();
        imagenes2.add(imagen2);
        productoMinimo.setImagenes(imagenes2);

        productoMinimoId = productosService.publicar(productoMinimo).getId();
    }


    //TESTS POSITIVOS
    @Test
    @DisplayName("Obtener producto completo por ID")
    void obtenerProductoCompleto() {
        var producto = productosRepository.findById(productoCompletoId);

        assertTrue(producto.isPresent());
        assertEquals("Camiseta Nike", producto.get().getTitulo());
        assertEquals("Camiseta en buen estado", producto.get().getDescripcion());
        assertEquals("Ropa", producto.get().getCategoria());
        assertEquals("Nike", producto.get().getMarca());
        assertEquals("M", producto.get().getTalla());
        assertEquals("Usado", producto.get().getEstado());
        assertFalse(producto.get().getImagenes().isEmpty());
    }

    @Test
    @DisplayName("Obtener producto con solo datos mínimos por ID")
    void obtenerProductoMinimo() {
        var producto = productosRepository.findById(productoMinimoId);

        assertTrue(producto.isPresent());
        assertEquals("Pantalón Adidas", producto.get().getTitulo());
        assertNull(producto.get().getDescripcion());
        assertNull(producto.get().getMarca());
        assertEquals("Ropa", producto.get().getCategoria());
        assertEquals("L", producto.get().getTalla());
        assertEquals("Nuevo", producto.get().getEstado());
        assertFalse(producto.get().getImagenes().isEmpty());
    }


    //TEST NEGATIVO
    @Test
    @DisplayName("Obtener producto inexistente por ID")
    void obtenerProductoInexistente() {
        assertFalse(productosRepository.findById(99999L).isPresent());
    }
}
