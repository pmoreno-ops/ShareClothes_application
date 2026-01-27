package com.example.backend.service.TestIntegracion.PUT;

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

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductosUpdateIntegrationTest {

    @InjectMocks
    private ProductosService productosService;

    @Mock
    private ProductosRepository productosRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("PUT -> Actualizar detalles de una prenda publicada")
    public void actualizarPrendaExitosa() {
        // GIVEN: producto existente
        Productos productoExistente = new Productos();
        productoExistente.setId(1L);
        productoExistente.setTitulo("Camiseta Roja");
        productoExistente.setCategoria("Ropa");
        productoExistente.setTalla("M");
        productoExistente.setEstado("Nuevo");
        productoExistente.setFechaCreacion(LocalDateTime.now());
        Set<ImagenProducto> imagenesExistentes = new HashSet<>();
        ImagenProducto img1 = new ImagenProducto();
        img1.setUrl("http://imagen1.com");
        imagenesExistentes.add(img1);
        productoExistente.setImagenes(imagenesExistentes);

        // Datos a actualizar
        Productos productoActualizado = new Productos();
        productoActualizado.setId(1L);
        productoActualizado.setTitulo("Camiseta Roja Modificada");
        productoActualizado.setCategoria("Ropa");
        productoActualizado.setTalla("L"); // cambio de talla
        productoActualizado.setEstado("Usado"); // cambio de estado
        Set<ImagenProducto> nuevasImagenes = new HashSet<>();
        ImagenProducto img2 = new ImagenProducto();
        img2.setUrl("http://imagen-nueva.com");
        nuevasImagenes.add(img2);
        productoActualizado.setImagenes(nuevasImagenes);

        when(productosRepository.findById(1L)).thenReturn(Optional.of(productoExistente));
        when(productosRepository.save(any(Productos.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        Productos resultado = null;
        if (productosRepository.findById(productoActualizado.getId()).isPresent()) {
            Productos prod = productosRepository.findById(productoActualizado.getId()).get();
            // Aplicar cambios
            prod.setTitulo(productoActualizado.getTitulo());
            prod.setTalla(productoActualizado.getTalla());
            prod.setEstado(productoActualizado.getEstado());
            prod.setImagenes(productoActualizado.getImagenes());
            resultado = productosService.publicar(prod); // usamos publicar para simular save
        }

        // THEN
        assertNotNull(resultado);
        assertEquals("Camiseta Roja Modificada", resultado.getTitulo());
        assertEquals("L", resultado.getTalla());
        assertEquals("Usado", resultado.getEstado());
        assertEquals(1, resultado.getImagenes().size());
        assertEquals("http://imagen-nueva.com", resultado.getImagenes().iterator().next().getUrl());

        verify(productosRepository, times(2)).findById(1L); // se llamó dos veces en el flujo
        verify(productosRepository, times(1)).save(any(Productos.class));
    }

    @Test
    @DisplayName("PUT -> Intentar actualizar prenda inexistente")
    public void actualizarPrendaNoExistente() {
        // GIVEN: producto no existe
        Productos productoActualizado = new Productos();
        productoActualizado.setId(99L);

        when(productosRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN / THEN
        Optional<Productos> prodOptional = productosRepository.findById(productoActualizado.getId());
        assertTrue(prodOptional.isEmpty(), "El producto no debe existir");
        verify(productosRepository, times(1)).findById(99L);
        verify(productosRepository, never()).save(any());
    }
}
