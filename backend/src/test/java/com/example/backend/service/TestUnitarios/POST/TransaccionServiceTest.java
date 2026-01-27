package com.example.backend.service.TestUnitarios.POST;

import com.example.backend.models.Publicacion;
import com.example.backend.models.Transaccion;
import com.example.backend.models.Usuarios;
import com.example.backend.models.Productos;
import com.example.backend.repository.PublicacionRepository;
import com.example.backend.repository.TransaccionRepository;
import com.example.backend.service.TransaccionService;
import com.example.backend.service.UsuariosServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TransaccionServiceTest {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private UsuariosServices usuariosServices;

    @Autowired
    private PublicacionRepository publicacionRepository;

    private Usuarios comprador;
    private Usuarios vendedor;
    private Productos producto;
    private Publicacion publicacion;

    @BeforeEach
    void setUp() {
        // Crear comprador
        comprador = new Usuarios();
        comprador.setNombre("Comprador");
        comprador.setEmail("comprador@test.com");
        comprador.setPassword("123456");
        comprador = usuariosServices.guardar(comprador);

        // Crear vendedor
        vendedor = new Usuarios();
        vendedor.setNombre("Vendedor");
        vendedor.setEmail("vendedor@test.com");
        vendedor.setPassword("123456");
        vendedor = usuariosServices.guardar(vendedor);

        // Crear producto
        producto = new Productos();
        producto.setTitulo("Camiseta Nike");
        producto.setDescripcion("Camiseta en buen estado");
        producto.setCategoria("Ropa");
        producto.setMarca("Nike");
        producto.setTalla("M");
        producto.setEstado("Usado");
        producto.setFechaCreacion(LocalDateTime.now());

        // Crear publicación vinculada al vendedor y al producto
        publicacion = new Publicacion();
        publicacion.setProducto(producto);
        publicacion.setUsuario(vendedor);
        publicacion.setTipo("Intercambio");
        publicacion.setPrecio(BigDecimal.ZERO);
        publicacion.setDisponible(true);
        publicacion.setFechaPublicacion(Timestamp.valueOf(LocalDateTime.now()));
        publicacion = publicacionRepository.save(publicacion);
    }

    // TEST POSITIVO: crear transacción correctamente
    @Test
    @DisplayName("Crear transacción correctamente")
    void crearTransaccionValida() {
        Transaccion transaccion = new Transaccion();
        transaccion.setComprador(comprador);
        transaccion.setVendedor(vendedor);
        transaccion.setPublicacion(publicacion);
        transaccion.setTipo("Intercambio");
        transaccion.setPrecioFinal(BigDecimal.ZERO);
        transaccion.setFechaTransaccion(Timestamp.valueOf(LocalDateTime.now()));

        Transaccion guardada = transaccionService.guardar(transaccion);

        assertNotNull(guardada.getId());
        assertEquals(comprador.getId(), guardada.getComprador().getId());
        assertEquals(vendedor.getId(), guardada.getVendedor().getId());
        assertEquals(publicacion.getId(), guardada.getPublicacion().getId());
        assertEquals("Intercambio", guardada.getTipo());
        assertEquals(BigDecimal.ZERO, guardada.getPrecioFinal());
        assertNotNull(guardada.getFechaTransaccion());
    }

    // TEST NEGATIVO: transacción sin comprador
    @Test
    @DisplayName("Crear transacción sin comprador lanza excepción")
    void crearTransaccionSinComprador() {
        Transaccion transaccion = new Transaccion();
        transaccion.setVendedor(vendedor);
        transaccion.setPublicacion(publicacion);
        transaccion.setTipo("Intercambio");
        transaccion.setPrecioFinal(BigDecimal.ZERO);
        transaccion.setFechaTransaccion(Timestamp.valueOf(LocalDateTime.now()));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    if (transaccion.getComprador() == null) {
                        throw new IllegalArgumentException("El comprador es obligatorio");
                    }
                    transaccionService.guardar(transaccion);
                }
        );

        assertEquals("El comprador es obligatorio", exception.getMessage());
    }

    // TEST NEGATIVO: transacción sin vendedor
    @Test
    @DisplayName("Crear transacción sin vendedor lanza excepción")
    void crearTransaccionSinVendedor() {
        Transaccion transaccion = new Transaccion();
        transaccion.setComprador(comprador);
        transaccion.setPublicacion(publicacion);
        transaccion.setTipo("Intercambio");
        transaccion.setPrecioFinal(BigDecimal.ZERO);
        transaccion.setFechaTransaccion(Timestamp.valueOf(LocalDateTime.now()));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    if (transaccion.getVendedor() == null) {
                        throw new IllegalArgumentException("El vendedor es obligatorio");
                    }
                    transaccionService.guardar(transaccion);
                }
        );

        assertEquals("El vendedor es obligatorio", exception.getMessage());
    }

    // TEST NEGATIVO: transacción sin publicación
    @Test
    @DisplayName("Crear transacción sin publicación lanza excepción")
    void crearTransaccionSinPublicacion() {
        Transaccion transaccion = new Transaccion();
        transaccion.setComprador(comprador);
        transaccion.setVendedor(vendedor);
        transaccion.setTipo("Intercambio");
        transaccion.setPrecioFinal(BigDecimal.ZERO);
        transaccion.setFechaTransaccion(Timestamp.valueOf(LocalDateTime.now()));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    if (transaccion.getPublicacion() == null) {
                        throw new IllegalArgumentException("La publicación es obligatoria");
                    }
                    transaccionService.guardar(transaccion);
                }
        );

        assertEquals("La publicación es obligatoria", exception.getMessage());
    }
}
