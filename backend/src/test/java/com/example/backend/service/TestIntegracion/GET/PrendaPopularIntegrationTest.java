package com.example.backend.service.TestIntegracion.GET;

import com.example.backend.models.Publicacion;
import com.example.backend.models.Transaccion;
import com.example.backend.repository.TransaccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PrendaPopularIntegrationTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private EstadisticasServiceMock estadisticasServiceMock;

    // Servicio simulado para el test
    public static class EstadisticasServiceMock {
        private final TransaccionRepository transaccionRepository;

        public EstadisticasServiceMock(TransaccionRepository transaccionRepository) {
            this.transaccionRepository = transaccionRepository;
        }

        public List<Publicacion> obtenerTopPrendas() {
            List<Transaccion> transacciones = transaccionRepository.findAll();

            Map<Publicacion, Long> conteo = transacciones.stream()
                    .collect(Collectors.groupingBy(Transaccion::getPublicacion, Collectors.counting()));

            return conteo.entrySet().stream()
                    .sorted(Map.Entry.<Publicacion, Long>comparingByValue().reversed())
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Top 5 publicaciones más populares en intercambios")
    public void top5PrendasMasPopularesTest() {

        // Crear publicaciones de ejemplo
        Publicacion p1 = new Publicacion();
        p1.setId(1L);
        p1.setTipo("Camiseta");
        p1.setPrecio(new BigDecimal("15.50"));

        Publicacion p2 = new Publicacion();
        p2.setId(2L);
        p2.setTipo("Pantalón");
        p2.setPrecio(new BigDecimal("25.00"));

        Publicacion p3 = new Publicacion();
        p3.setId(3L);
        p3.setTipo("Chaqueta");
        p3.setPrecio(new BigDecimal("50.00"));

        // Crear transacciones simulando intercambios
        List<Transaccion> transacciones = Arrays.asList(
                new Transaccion(1L, p1, null, null, "intercambio", BigDecimal.valueOf(15.50), null),
                new Transaccion(2L, p2, null, null, "intercambio", BigDecimal.valueOf(25.00), null),
                new Transaccion(3L, p1, null, null, "intercambio", BigDecimal.valueOf(15.50), null),
                new Transaccion(4L, p3, null, null, "intercambio", BigDecimal.valueOf(50.00), null),
                new Transaccion(5L, p1, null, null, "intercambio", BigDecimal.valueOf(15.50), null),
                new Transaccion(6L, p2, null, null, "intercambio", BigDecimal.valueOf(25.00), null)
        );

        // Mock del repositorio
        when(transaccionRepository.findAll()).thenReturn(transacciones);

        // Llamada al "service" que usa el mock
        List<Publicacion> top5 = estadisticasServiceMock.obtenerTopPrendas();

        // Comprobaciones
        assertThat(top5).isNotNull();
        assertThat(top5.size()).isLessThanOrEqualTo(5);
        assertThat(top5.get(0).getId()).isEqualTo(p1.getId()); // p1 es la más repetida

        // Verificar interacción con el mock
        verify(transaccionRepository).findAll();
    }
}
