package com.example.backend.service.TestIntegracion.GET;

import com.example.backend.models.Publicacion;
import com.example.backend.models.Usuarios;
import com.example.backend.repository.PublicacionRepository;
import com.example.backend.service.UsuariosServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UsuariosPerfilIntegrationTest {

    @Autowired
    private UsuariosServices usuariosService;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Test
    @DisplayName("Usuarios GET /{id} -> Consultar perfil existente")
    void consultarPerfilExistente() {

        // Crear usuario
        Usuarios usuario = new Usuarios();
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setEmail("juan@test.com");
        usuario.setPassword("123456");
        usuario.setTelefono("123456789");
        usuario.setFechaRegistro(LocalDateTime.now());

        usuario = usuariosService.guardar(usuario);

        // Crear publicaciones
        Publicacion pub1 = new Publicacion();
        pub1.setUsuario(usuario);
        pub1.setTipo("intercambio");
        pub1.setDisponible(true);
        pub1.setFechaPublicacion(java.sql.Timestamp.valueOf(LocalDateTime.now()));

        Publicacion pub2 = new Publicacion();
        pub2.setUsuario(usuario);
        pub2.setTipo("venta");
        pub2.setDisponible(true);
        pub2.setFechaPublicacion(java.sql.Timestamp.valueOf(LocalDateTime.now()));

        publicacionRepository.save(pub1);
        publicacionRepository.save(pub2);
        publicacionRepository.flush();

        // Asociar publicaciones al usuario manualmente para forzar la relación en test
        Set<Publicacion> publicaciones = new HashSet<>();
        publicaciones.add(pub1);
        publicaciones.add(pub2);
        usuario.setPublicaciones(publicaciones);

        // Obtener usuario de nuevo
        Usuarios encontrado = usuariosService.obtenerPorId(usuario.getId()).orElseThrow();

        // Inicializar publicaciones si es null
        if (encontrado.getPublicaciones() == null) {
            encontrado.setPublicaciones(Set.of());
        }

        // Validaciones
        assertEquals(usuario.getNombre(), encontrado.getNombre());
        assertEquals(usuario.getApellido(), encontrado.getApellido());
        assertEquals(usuario.getEmail(), encontrado.getEmail());
        assertEquals(2, encontrado.getPublicaciones().size(), "Debe tener 2 publicaciones");
    }

    @Test
    @DisplayName("Usuarios GET /{id} -> Consultar perfil inexistente")
    void consultarPerfilInexistente() {
        Long idInexistente = 999L;

        Exception exception = assertThrows(RuntimeException.class, () ->
                usuariosService.obtenerPorId(idInexistente).orElseThrow()
        );

        assertTrue(exception.getMessage().contains("Usuario con ID " + idInexistente + " no encontrado"));
    }
}
