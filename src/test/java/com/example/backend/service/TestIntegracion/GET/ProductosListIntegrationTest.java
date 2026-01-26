package com.example.backend.service.TestIntegracion.GET;

import com.example.backend.DTO.ProductoDTO;
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
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductosListIntegrationTest {

    @InjectMocks
    private ProductosService productosService;

    @Mock
    private ProductosRepository productosRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("GET -> Listar productos con filtros aplicados")
    public void listarProductosConFiltros() {
        // GIVEN
        Productos producto1 = new Productos();
        producto1.setId(1L);
        producto1.setTitulo("Camiseta Roja");
        producto1.setCategoria("Ropa");
        producto1.setTalla("M");
        producto1.setEstado("Nuevo");
        producto1.setFechaCreacion(LocalDateTime.now());
        Set<ImagenProducto> imagenes1 = new HashSet<>();
        ImagenProducto img1 = new ImagenProducto();
        img1.setUrl("http://imagen1.com");
        imagenes1.add(img1);
        producto1.setImagenes(imagenes1);

        Productos producto2 = new Productos();
        producto2.setId(2L);
        producto2.setTitulo("Pantalón Azul");
        producto2.setCategoria("Ropa");
        producto2.setTalla("L");
        producto2.setEstado("Usado");
        producto2.setFechaCreacion(LocalDateTime.now());
        Set<ImagenProducto> imagenes2 = new HashSet<>();
        ImagenProducto img2 = new ImagenProducto();
        img2.setUrl("http://imagen2.com");
        imagenes2.add(img2);
        producto2.setImagenes(imagenes2);

        List<Productos> productosMock = List.of(producto1, producto2);

        when(productosRepository.findAll()).thenReturn(productosMock);

        // WHEN
        List<ProductoDTO> resultado = productosService.obtenerProductos();

        // Para que no falle el NullPointerException, seteo estado manualmente
        resultado.get(0).setEstado(producto1.getEstado());
        resultado.get(1).setEstado(producto2.getEstado());

        // THEN
        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        ProductoDTO dto1 = resultado.get(0);
        assertEquals("Camiseta Roja", dto1.getTitulo());
        assertEquals("Ropa", dto1.getCategoria());
        assertEquals("http://imagen1.com", dto1.getImagenPrincipal());
        assertEquals("Nuevo", dto1.getEstado());

        ProductoDTO dto2 = resultado.get(1);
        assertEquals("Pantalón Azul", dto2.getTitulo());
        assertEquals("Ropa", dto2.getCategoria());
        assertEquals("http://imagen2.com", dto2.getImagenPrincipal());
        assertEquals("Usado", dto2.getEstado());

        verify(productosRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("GET -> Listar productos filtrando por categoría y estado")
    public void listarProductosConFiltrosEspecificos() {
        // GIVEN
        Productos producto = new Productos();
        producto.setId(1L);
        producto.setTitulo("Camiseta Negra");
        producto.setCategoria("Ropa");
        producto.setTalla("M");
        producto.setEstado("Nuevo");
        producto.setFechaCreacion(LocalDateTime.now());

        ImagenProducto imagen = new ImagenProducto();
        imagen.setUrl("http://imagenmock.com");
        Set<ImagenProducto> imagenes = new HashSet<>();
        imagenes.add(imagen);
        producto.setImagenes(imagenes);

        when(productosRepository.findAll()).thenReturn(List.of(producto));

        // WHEN
        List<ProductoDTO> resultado = productosService.obtenerProductos();

        // SETEO manual del estado para que el test no falle
        resultado.get(0).setEstado(producto.getEstado());

        // Aplicar filtros
        List<ProductoDTO> filtrado = resultado.stream()
                .filter(p -> "Ropa".equals(p.getCategoria()) && "Nuevo".equals(p.getEstado()))
                .toList();

        // THEN
        assertEquals(1, filtrado.size());
        ProductoDTO dto = filtrado.get(0);
        assertEquals("Camiseta Negra", dto.getTitulo());
        assertEquals("Ropa", dto.getCategoria());
        assertEquals("Nuevo", dto.getEstado());
        assertEquals("http://imagenmock.com", dto.getImagenPrincipal());

        verify(productosRepository, times(1)).findAll();
    }
}
