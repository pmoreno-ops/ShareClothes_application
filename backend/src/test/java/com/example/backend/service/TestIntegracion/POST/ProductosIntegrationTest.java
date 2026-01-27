package com.example.backend.service.TestIntegracion.POST;

import com.example.backend.models.ImagenProducto;
import com.example.backend.models.Productos;
import com.example.backend.repository.ProductosRepository;
import com.example.backend.service.ProductosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductosIntegrationTest {

    @InjectMocks
    private ProductosService productosService;

    @Mock
    private ProductosRepository productosRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("POST -> Publicar producto exitosamente")
    public void publicarProductoExitoso() {
        // GIVEN
        Productos producto = new Productos();
        producto.setTitulo("Camiseta Test");
        producto.setDescripcion("Descripción Test");
        producto.setCategoria("Ropa");
        producto.setTalla("M");
        producto.setEstado("Nuevo");

        ImagenProducto imagen = new ImagenProducto();
        imagen.setUrl("http://imagenmock.com");
        producto.setImagenes(new HashSet<>());
        producto.getImagenes().add(imagen);

        // Simulamos que el repositorio devuelve el mismo producto al guardar
        when(productosRepository.save(any(Productos.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        Productos productoGuardado = productosService.publicar(producto);

        // THEN
        assertNotNull(productoGuardado);
        assertEquals("Camiseta Test", productoGuardado.getTitulo());
        assertEquals(1, productoGuardado.getImagenes().size());
        assertEquals("http://imagenmock.com", productoGuardado.getImagenes().iterator().next().getUrl());

        verify(productosRepository, times(1)).save(producto);
    }

    @Test
    @DisplayName("POST -> Fallo al publicar producto sin título")
    public void publicarProductoSinTitulo() {
        // GIVEN
        Productos producto = new Productos();
        producto.setDescripcion("Descripción Test");
        producto.setCategoria("Ropa");
        producto.setTalla("M");
        producto.setEstado("Nuevo");
        producto.setImagenes(new HashSet<>());

        // THEN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productosService.publicar(producto);
        });

        assertEquals("El título es obligatorio", exception.getMessage());
        verify(productosRepository, never()).save(any());
    }

    @Test
    @DisplayName("POST -> Fallo al publicar producto sin imágenes")
    public void publicarProductoSinImagenes() {
        // GIVEN
        Productos producto = new Productos();
        producto.setTitulo("Camiseta Test");
        producto.setDescripcion("Descripción Test");
        producto.setCategoria("Ropa");
        producto.setTalla("M");
        producto.setEstado("Nuevo");
        producto.setImagenes(new HashSet<>()); // Sin imágenes

        // THEN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productosService.publicar(producto);
        });

        assertEquals("Debe incluir al menos una imagen", exception.getMessage());
        verify(productosRepository, never()).save(any());
    }
}
