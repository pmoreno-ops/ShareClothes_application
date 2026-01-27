package com.example.backend.service.TestUnitarios.POST;

import com.example.backend.models.Usuarios;
import com.example.backend.repository.UsuariosRepository;
import com.example.backend.service.UsuariosServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UsuarioServiceTest {

    @Autowired
    private UsuariosServices usuariosServices;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // Función auxiliar para crear usuarios válidos según la tabla
    private Usuarios crearUsuarioValido(String email, String password) {
        Usuarios usuario = new Usuarios();
        usuario.setNombre("TestNombre");
        usuario.setApellido("TestApellido");
        usuario.setEmail(email);
        usuario.setTelefono("123456789");
        usuario.setPassword(password);
        usuario.setFechaRegistro(LocalDateTime.now());
        return usuario;
    }

    //Tests Positivos
    @Test
    void crearUsuario_TodosCampos_DevuelveCorrectamente() {
        Usuarios usuario = crearUsuarioValido("usuario1@test.com", "123456");

        Usuarios guardado = usuariosServices.guardar(usuario);

        assertNotNull(guardado.getId());
        assertEquals("usuario1@test.com", guardado.getEmail());
        assertNotNull(guardado.getPasswordHash());
        assertTrue(passwordEncoder.matches("123456", guardado.getPasswordHash()));
        assertEquals("TestNombre", guardado.getNombre());
        assertEquals("TestApellido", guardado.getApellido());
        assertEquals("123456789", guardado.getTelefono());
        assertNotNull(guardado.getFechaRegistro());
    }

    @Test
    void guardarUsuario_Correctamente_GeneraHashYGuarda() {
        Usuarios usuario = crearUsuarioValido("ok@test.com", "123456");

        Usuarios guardado = usuariosServices.guardar(usuario);

        assertNotNull(guardado.getId());
        assertNotNull(guardado.getPasswordHash());
        assertTrue(passwordEncoder.matches("123456", guardado.getPasswordHash()));
    }

    //Tests Negativos
    @Test
    void crearUsuario_SinEmail_LanzaExcepcion() {
        Usuarios usuario = crearUsuarioValido("", "123456");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> usuariosServices.guardar(usuario)
        );

        assertEquals("El campo email es obligatorio", exception.getMessage());
    }

    @Test
    void crearUsuario_EmailDuplicado_LanzaExcepcion() {
        Usuarios usuario1 = crearUsuarioValido("duplicado@test.com", "123456");
        usuariosServices.guardar(usuario1);

        Usuarios usuario2 = crearUsuarioValido("duplicado@test.com", "abcdef");

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> usuariosServices.guardar(usuario2)
        );

        assertEquals("El email ya está registrado", exception.getMessage());
    }
}
