package com.example.backend.service;

import com.example.backend.exception.UsuarioNoEncontradoException;
import com.example.backend.models.Usuarios;
import com.example.backend.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServices {

    private final UsuariosRepository usuariosRepository;
    

    @Autowired
    public UsuariosServices(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuarios getUsuarioConMasTransaccionesAceptadas() {
        return usuariosRepository.findUsuarioConMasTransaccionesAceptadas();
    }

    // Obtener todos los usuarios
    public List<Usuarios> obtenerTodos() {
        return usuariosRepository.findAll();
    }


    public Usuarios guardar(Usuarios usuario) {

        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new IllegalArgumentException("El campo password es obligatorio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El campo email es obligatorio");
        }

        //Validar campos obligatorios
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            usuario.setNombre("");
            usuario.setApellido("");
            usuario.setTelefono("");
            usuario.setFechaRegistro(null);
            return usuario;
        }

        //Validar email duplicado
        if (usuariosRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Generar hash de la contraseña
        usuario.setPasswordHash(passwordEncoder.encode(usuario.getPassword()));

        // Guardar usuario en la base de datos
        return usuariosRepository.save(usuario);
    }

    // Obtener usuario por ID
    public Optional<Usuarios> obtenerPorId(Long id) {
        return Optional.ofNullable(usuariosRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con ID " + id + " no encontrado")));
    }

    // Actualizar un usuario existente
    public Usuarios actualizar(Long id, Usuarios usuarioActualizado) {
        return usuariosRepository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(usuarioActualizado.getNombre());
                    usuario.setApellido(usuarioActualizado.getApellido());
                    usuario.setEmail(usuarioActualizado.getEmail());
                    usuario.setTelefono(usuarioActualizado.getTelefono());
                    usuario.setFechaRegistro(usuarioActualizado.getFechaRegistro());
                    return usuariosRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    // Eliminar un usuario por ID
    public void eliminar(Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}
