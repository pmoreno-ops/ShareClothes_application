package com.example.backend.service.TestIntegracion.POST;

import com.example.backend.models.Usuarios;
import com.example.backend.service.UsuariosServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // asegura un rollback automático de la BDD al terminar
public class UsuariosIntegrationTest {

    @Autowired
    private UsuariosServices usuariosService;

    @Test
    @DisplayName("Test Integración -> Registrar Usuario Exitoso")
    void registrarUsuarioExitoso() {
        // GIVEN
        Usuarios nuevo = new Usuarios();
        nuevo.setNombre("Juan");
        nuevo.setApellido("Pérez");
        nuevo.setEmail("juan@test.com");
        nuevo.setPassword("123456"); // se va a codificar con el PasswordEncoder real
        nuevo.setTelefono("555123456");
        nuevo.setFechaRegistro(LocalDateTime.now());

        // WHEN
        Usuarios guardado = usuariosService.guardar(nuevo);

        // THEN
        assertNotNull(guardado.getId());
        assertEquals("Juan", guardado.getNombre());
        assertNotNull(guardado.getPasswordHash(), "El hash de la contraseña no debe ser null");
    }

    @Test
    @DisplayName("Test Integración -> Registrar Usuario Email Duplicado")
    void registrarUsuarioEmailDuplicado() {
        // GIVEN
        Usuarios existente = new Usuarios();
        existente.setNombre("Ana");
        existente.setApellido("Gomez");
        existente.setEmail("ana@test.com");
        existente.setPassword("654321");
        existente.setTelefono("555987654");
        existente.setFechaRegistro(LocalDateTime.now());

        // Primero guardamos un usuario para crear duplicado
        usuariosService.guardar(existente);

        Usuarios duplicado = new Usuarios();
        duplicado.setNombre("Ana2");
        duplicado.setApellido("Gomez2");
        duplicado.setEmail("ana@test.com");
        duplicado.setPassword("000000");
        duplicado.setTelefono("555000000");
        duplicado.setFechaRegistro(LocalDateTime.now());

        // WHEN + THEN
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuariosService.guardar(duplicado));
        assertTrue(exception.getMessage().contains("email ya está registrado"));
    }
}
