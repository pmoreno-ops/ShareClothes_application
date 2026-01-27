package com.example.backend.service.TestIntegracion.PATCH;

import com.example.backend.models.Transaccion;
import com.example.backend.repository.TransaccionRepository;
import com.example.backend.service.TransaccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TransaccionEstadoIntegrationTest {

    @Mock
    private TransaccionRepository transaccionRepository; // mock clásico de Mockito

    @InjectMocks
    private TransaccionService transaccionService; // inyecta el mock en el servicio

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // inicializa los mocks
    }

    @Test
    @DisplayName("Actualizar estado de intercambio exitosamente con mock")
    public void actualizarEstadoIntercambioTest() {
        // Creamos una transacción existente simulada
        Transaccion transaccionExistente = new Transaccion();
        transaccionExistente.setId(1L);
        transaccionExistente.setTipo("pendiente");
        transaccionExistente.setFechaTransaccion(new Timestamp(System.currentTimeMillis()));

        // Creamos la actualización parcial
        Transaccion actualizacion = new Transaccion();
        actualizacion.setTipo("aceptado");

        // Configuramos el comportamiento del mock
        when(transaccionRepository.findById(1L)).thenReturn(Optional.of(transaccionExistente));
        when(transaccionRepository.save(any(Transaccion.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecutamos el método
        Transaccion resultado = transaccionService.actualizar(1L, actualizacion);

        // Verificaciones
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getTipo()).isEqualTo("aceptado");

        // Verificar interacción con el repositorio
        verify(transaccionRepository).findById(1L);
        verify(transaccionRepository).save(transaccionExistente);
    }

    @Test
    @DisplayName("Actualizar estado de intercambio inexistente lanza excepción")
    public void actualizarEstadoIntercambioInexistenteTest() {
        Transaccion actualizacion = new Transaccion();
        actualizacion.setTipo("aceptado");

        // Mock que devuelve vacío
        when(transaccionRepository.findById(999L)).thenReturn(Optional.empty());

        try {
            transaccionService.actualizar(999L, actualizacion);
        } catch (RuntimeException e) {
            assertThat(e).hasMessageContaining("Transacción no encontrada con ID: 999");
        }

        // Verificar que save nunca fue llamado
        verify(transaccionRepository, never()).save(any(Transaccion.class));
    }
}
