package com.example.backend.service.TestUnitarios.PATCH;

import com.example.backend.models.Productos;
import com.example.backend.models.Publicacion;
import com.example.backend.models.Transaccion;
import com.example.backend.models.Usuarios;
import com.example.backend.repository.ProductosRepository;
import com.example.backend.repository.PublicacionRepository;
import com.example.backend.repository.TransaccionRepository;
import com.example.backend.service.TransaccionService;
import com.example.backend.service.UsuariosServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class TransaccionEstadoPatchTest {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private UsuariosServices usuariosServices;

    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    private Transaccion transaccionExistente;
    private Long idInexistente = 99999L;

    @BeforeEach
    void setUp() {

        // Crear comprador
        Usuarios comprador = new Usuarios();
        comprador.setNombre("Comprador");
        comprador.setEmail("comprador@test.com");
        comprador.setPassword("123456");
        comprador = usuariosServices.guardar(comprador);

        // Crear vendedor
        Usuarios vendedor = new Usuarios();
        vendedor.setNombre("Vendedor");
        vendedor.setEmail("vendedor@test.com");
        vendedor.setPassword("123456");
        vendedor = usuariosServices.guardar(vendedor);

        // Crear producto
        Productos producto = new Productos();
        producto.setTitulo("Camiseta de prueba");
        producto.setDescripcion("Producto de prueba");
        producto.setCategoria("Ropa");
        producto.setMarca("MarcaTest");
        producto.setTalla("M");
        producto.setEstado("Nuevo");
        producto.setFechaCreacion(LocalDateTime.now());
        producto = productosRepository.save(producto);

        // Crear publicación correctamente
        Publicacion publicacion = new Publicacion();
        publicacion.setProducto(producto);        // Asocia el producto
        publicacion.setUsuario(vendedor);         // Asocia el vendedor
        publicacion.setTipo("intercambio");
        publicacion.setPrecio(BigDecimal.ZERO);
        publicacion.setDisponible(true);
        publicacion.setFechaPublicacion(Timestamp.valueOf(LocalDateTime.now()));
        publicacion = publicacionRepository.save(publicacion); // Guarda correctamente

        // Crear transacción
        transaccionExistente = new Transaccion();
        transaccionExistente.setComprador(comprador);
        transaccionExistente.setVendedor(vendedor);
        transaccionExistente.setPublicacion(publicacion);
        transaccionExistente.setTipo("pendiente");  // ¡Este es el valor clave!
        transaccionExistente.setPrecioFinal(BigDecimal.ZERO);
        transaccionExistente.setFechaTransaccion(Timestamp.valueOf(LocalDateTime.now()));

        transaccionExistente = transaccionService.guardar(transaccionExistente);
    }





    // Test Positivo: Actualizar Estado de Pendiente a Aceptado
    @Test
    void actualizarEstado_PendienteAAceptado() {
        Transaccion actualizacion = new Transaccion();
        actualizacion.setTipo("Aceptado");

        Transaccion actualizado = transaccionService.actualizar(transaccionExistente.getId(), actualizacion);

        assertEquals("Aceptado", actualizado.getTipo());
    }

    // Test Positivo: Actualizar Estado de Aceptado a Cancelado
    @Test
    void actualizarEstado_AceptadoACancelado() {
        transaccionExistente.setTipo("aceptado");
        transaccionService.actualizar(transaccionExistente.getId(), transaccionExistente);

        Transaccion cambio = new Transaccion();
        cambio.setTipo("Cancelado");

        Transaccion actualizado = transaccionService.actualizar(transaccionExistente.getId(), cambio);

        assertEquals("Cancelado", actualizado.getTipo());
    }


    // Test Negativo: Actualizar estado de transaccion inexistente lanza excepcion
    @Test
    void actualizarEstado_TransaccionInexistente_LanzaExcepcion() {
        Transaccion actualizacion = new Transaccion();
        actualizacion.setTipo("Aceptado");

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> transaccionService.actualizar(idInexistente, actualizacion)
        );

        assertEquals("Transacción no encontrada con ID: " + idInexistente, exception.getMessage());
    }
}
