package com.example.backend.service.TestIntegracion.POST;

import com.example.backend.models.Publicacion;
import com.example.backend.models.Transaccion;
import com.example.backend.models.Usuarios;
import com.example.backend.repository.TransaccionRepository;
import com.example.backend.service.TransaccionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransaccionIntegrationTest {

    @InjectMocks
    private TransaccionService transaccionService;

    @Mock
    private TransaccionRepository transaccionRepository;

    @Test
    public void crearSolicitudIntercambioTest() {
        Usuarios comprador = new Usuarios();
        comprador.setId(20L);

        Usuarios vendedor = new Usuarios();
        vendedor.setId(10L);

        Publicacion publicacion = new Publicacion();
        publicacion.setId(1L);
        publicacion.setUsuario(vendedor);

        Transaccion t = new Transaccion();
        t.setId(100L);
        t.setPublicacion(publicacion);
        t.setComprador(comprador);
        t.setVendedor(vendedor);
        t.setTipo("pendiente");
        t.setFechaTransaccion(new Timestamp(System.currentTimeMillis()));

        // Mockeamos el save
        Mockito.when(transaccionRepository.save(Mockito.any(Transaccion.class))).thenReturn(t);

        Transaccion resultado = transaccionService.guardar(t);

        assertNotNull(resultado);
        assertEquals(100L, resultado.getId());
        assertEquals("pendiente", resultado.getTipo());

        Mockito.verify(transaccionRepository).save(Mockito.any(Transaccion.class));
    }
}
