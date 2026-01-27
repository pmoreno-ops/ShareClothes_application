package com.example.backend.service.TestUnitarios.GET;

import com.example.backend.models.*;
import com.example.backend.repository.*;
import com.example.backend.service.EstadisticasService;
import com.example.backend.service.UsuariosServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EstadisticasPrendaPopularTest {

    @Autowired
    private EstadisticasService estadisticasService;

    @Autowired
    private UsuariosServices usuariosServices;

    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate; // inserta manualmente en publicacion_productos


    // TEST NEGATIVO
    @Test
    void top5PrendasPopulares_SinTransacciones_DevuelveListaVacia() {
        List<Object[]> resultado = estadisticasService.top5PrendasPopulares();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    // TEST POSITIVO
    @Test
    void top5PrendasPopulares_DevuelvePrendaMasPopular() {

        // Usuario vendedor
        Usuarios vendedor = new Usuarios();
        vendedor.setNombre("Vendedor");
        vendedor.setEmail("vendedor@test.com");
        vendedor.setPassword("123456");
        vendedor = usuariosServices.guardar(vendedor);

        // Usuario comprador
        Usuarios comprador = new Usuarios();
        comprador.setNombre("Comprador");
        comprador.setEmail("comprador@test.com");
        comprador.setPassword("123456");
        comprador = usuariosServices.guardar(comprador);

        // Producto
        Productos producto = new Productos();
        producto.setTitulo("Camiseta Popular");
        producto.setDescripcion("Muy intercambiada");
        producto.setCategoria("Ropa");
        producto.setMarca("MarcaTest");
        producto.setTalla("M");
        producto.setEstado("Usado");
        producto.setFechaCreacion(LocalDateTime.now());
        producto = productosRepository.saveAndFlush(producto);

        // Publicación
        Publicacion publicacion = new Publicacion();
        publicacion.setProducto(producto);
        publicacion.setUsuario(vendedor);
        publicacion.setTipo("intercambio");
        publicacion.setPrecio(BigDecimal.ZERO);
        publicacion.setDisponible(true);
        publicacion.setFechaPublicacion(Timestamp.valueOf(LocalDateTime.now()));
        publicacion = publicacionRepository.saveAndFlush(publicacion);

        // INSERTAR RELACIÓN EN publicacion_productos
        jdbcTemplate.update(
                "INSERT INTO publicacion_productos (publicacion_id, producto_id) VALUES (?, ?)",
                publicacion.getId(),
                producto.getId()
        );

        // 3 transacciones para la misma prenda
        for (int i = 0; i < 3; i++) {
            Transaccion t = new Transaccion();
            t.setPublicacion(publicacion);
            t.setComprador(comprador);
            t.setVendedor(vendedor);
            t.setTipo("aceptado");
            t.setPrecioFinal(BigDecimal.ZERO);
            t.setFechaTransaccion(Timestamp.valueOf(LocalDateTime.now()));
            transaccionRepository.save(t);
        }
        transaccionRepository.flush();

        // Ejecutar
        List<Object[]> resultado = estadisticasService.top5PrendasPopulares();

        // Validaciones
        assertNotNull(resultado, "La lista no debe ser null");
        assertFalse(resultado.isEmpty(), "Debe devolver al menos una prenda");
        assertEquals(1, resultado.size(), "Debe devolver exactamente una prenda");

        Object[] fila = resultado.get(0);
        assertEquals(producto.getId(), ((Number) fila[0]).longValue(), "ID del producto incorrecto");
        assertEquals("Camiseta Popular", fila[1], "Título incorrecto");
        assertEquals("Muy intercambiada", fila[2], "Descripción incorrecta");
        assertEquals(3L, ((Number) fila[3]).longValue(), "Total de intercambios incorrecto");
    }
}
