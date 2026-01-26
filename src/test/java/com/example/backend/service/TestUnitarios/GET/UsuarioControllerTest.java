package com.example.backend.service.TestUnitarios.GET;

import com.example.backend.exception.UsuarioNoEncontradoException;
import com.example.backend.models.Usuarios;
import com.example.backend.repository.UsuariosRepository;
import com.example.backend.service.UsuariosServices;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UsuarioControllerTest {

    @Autowired
    private UsuariosServices usuariosServices;

    private Long usuarioConDatosId;
    private Long usuarioConDatosMinimosId;

    @BeforeEach
    void cargarDatos() {
        // Usuario con todos los datos
        Usuarios usuarioCompleto = new Usuarios();
        usuarioCompleto.setNombre("TestNombre");
        usuarioCompleto.setApellido("TestApellido");
        usuarioCompleto.setEmail("perfil@test.com");
        usuarioCompleto.setTelefono("123456789");
        usuarioCompleto.setPassword("123456");

        usuarioConDatosId = usuariosServices.guardar(usuarioCompleto).getId();

        // Usuario con SOLO datos obligatorios
        Usuarios usuarioMinimo = new Usuarios();
        usuarioMinimo.setNombre("UsuarioMinimo");
        usuarioMinimo.setEmail("minimo@test.com");
        usuarioMinimo.setPassword("123456");

        usuarioConDatosMinimosId = usuariosServices.guardar(usuarioMinimo).getId();
    }

    //Test POSITIVO
    @Test
    void obtenerUsuarioConDatos() {
        var usuario = usuariosServices.obtenerPorId(usuarioConDatosId);

        assertTrue(usuario.isPresent());
        assertEquals("TestNombre", usuario.get().getNombre());
        assertEquals("TestApellido", usuario.get().getApellido());
        assertEquals("perfil@test.com", usuario.get().getEmail());
    }

    //Test NEGATIVOS
    //USUARIO CON DATOS MÍNIMOS
    @Test
    void obtenerUsuarioConDatosMinimos() {
        var usuario = usuariosServices.obtenerPorId(usuarioConDatosMinimosId);

        assertTrue(usuario.isPresent());
        assertEquals("UsuarioMinimo", usuario.get().getNombre());
        assertNull(usuario.get().getApellido());
        assertNull(usuario.get().getTelefono());
    }

    //NO EXISTE
    @Test
    void obtenerUsuarioInexistente() {
        assertThrows(
                UsuarioNoEncontradoException.class,
                () -> usuariosServices.obtenerPorId(99999L)
        );
    }
}

