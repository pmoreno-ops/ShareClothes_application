package com.example.backend.service.TestUnitarios.POST;

import com.example.backend.models.ImagenProducto;
import com.example.backend.models.Productos;
import com.example.backend.repository.ProductosRepository;
import com.example.backend.service.ProductosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ProductosServiceTest {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private ProductosRepository productosRepository;


    private Productos crearProductoValido() {
        Productos producto = new Productos();
        producto.setTitulo("Camiseta Nike");
        producto.setDescripcion("Camiseta en buen estado");
        producto.setCategoria("Ropa");
        producto.setMarca("Nike");
        producto.setTalla("M");
        producto.setEstado("Usado");
        producto.setFechaCreacion(LocalDateTime.now());

        // Imagen vinculada al producto
        ImagenProducto imagen = new ImagenProducto();
        imagen.setUrl("http://img.test/1.jpg");
        imagen.setProducto(producto);

        // Set mutable para JPA
        Set<ImagenProducto> imagenes = new HashSet<>();
        imagenes.add(imagen);
        producto.setImagenes(imagenes);

        return producto;
    }


    //Test Positivo
    @Test
    void publicarProducto_TodosCampos_DevuelveCorrectamente() {
        Productos producto = crearProductoValido();

        Productos productoGuardado = productosService.publicar(producto);

        assertNotNull(productoGuardado.getId());
        assertEquals("Camiseta Nike", productoGuardado.getTitulo());
        assertEquals("Camiseta en buen estado", productoGuardado.getDescripcion());
        assertEquals("Ropa", productoGuardado.getCategoria());
        assertEquals("Nike", productoGuardado.getMarca());
        assertEquals("M", productoGuardado.getTalla());
        assertEquals("Usado", productoGuardado.getEstado());
        assertNotNull(productoGuardado.getFechaCreacion());
        assertFalse(productoGuardado.getImagenes().isEmpty());
    }


    @Test
    void publicarProducto_ConCamposOpcionalesNulos_SeGuarda() {
        Productos producto = crearProductoValido();
        producto.setDescripcion(null);
        producto.setMarca(null);

        Productos guardado = productosService.publicar(producto);

        assertNotNull(guardado.getId());
        assertNull(guardado.getDescripcion());
        assertNull(guardado.getMarca());
    }

    //Test Negativo

    @Test
    void publicarProducto_SinTitulo_LanzaExcepcion() {
        Productos producto = crearProductoValido();
        producto.setTitulo(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> productosService.publicar(producto)
        );

        assertEquals("El título es obligatorio", exception.getMessage());
    }

    @Test
    void publicarProducto_SinCategoria_LanzaExcepcion() {
        Productos producto = crearProductoValido();
        producto.setCategoria(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> productosService.publicar(producto)
        );

        assertEquals("La categoría es obligatoria", exception.getMessage());
    }

    @Test
    void publicarProducto_SinImagenes_LanzaExcepcion() {
        Productos producto = crearProductoValido();
        producto.setImagenes(new HashSet<>());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> productosService.publicar(producto)
        );

        assertEquals("Debe incluir al menos una imagen", exception.getMessage());
    }



}
