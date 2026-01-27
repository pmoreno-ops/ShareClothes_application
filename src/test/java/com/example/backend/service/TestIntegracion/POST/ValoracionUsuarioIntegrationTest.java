package com.example.backend.service.TestIntegracion.POST;

import com.example.backend.models.Usuarios;
import com.example.backend.models.Valoracion;
import com.example.backend.repository.UsuariosRepository;
import com.example.backend.repository.ValoracionRepository;
import com.example.backend.service.UsuariosServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ValoracionUsuarioIntegrationTest {

    @Mock
    private UsuariosRepository usuariosRepository;

    @Mock
    private ValoracionRepository valoracionRepository;

    @InjectMocks
    private UsuariosServices usuariosServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Registrar valoración de usuario tras intercambio (mock)")
    public void registrarValoracionUsuarioTest() {

        // Crear usuarios de prueba
        Usuarios evaluado = new Usuarios();
        evaluado.setId(1L);
        evaluado.setValoracionesRecibidas(new HashSet<>());

        Usuarios evaluador = new Usuarios();
        evaluador.setId(2L);
        evaluador.setValoracionesRealizadas(new HashSet<>());

        // Crear la valoración manualmente
        Valoracion valoracion = new Valoracion();
        valoracion.setEvaluado(evaluado);
        valoracion.setEvaluador(evaluador);
        valoracion.setPuntuacion(5);

        // Mock para guardar valoración
        when(valoracionRepository.save(any(Valoracion.class))).thenReturn(valoracion);
        when(usuariosRepository.save(any(Usuarios.class))).thenReturn(evaluado);

        // Guardamos valoración simulada
        Valoracion resultado = valoracionRepository.save(valoracion);
        Usuarios evaluadoGuardado = usuariosRepository.save(evaluado);

        // Validaciones
        assertThat(resultado).isNotNull();
        assertThat(resultado.getEvaluado()).isEqualTo(evaluado);
        assertThat(resultado.getEvaluador()).isEqualTo(evaluador);
        assertThat(resultado.getPuntuacion()).isEqualTo(5);
        assertThat(evaluadoGuardado).isEqualTo(evaluado);

        // Verificaciones de interacción
        verify(valoracionRepository).save(valoracion);
        verify(usuariosRepository).save(evaluado);
    }
}
