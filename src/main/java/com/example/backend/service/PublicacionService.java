package com.example.backend.service;

import com.example.backend.models.Publicacion;
import com.example.backend.models.Valoracion;
import com.example.backend.repository.PublicacionRepository;
import com.example.backend.repository.UsuariosRepository;
import org.hibernate.mapping.Set;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {
    private final PublicacionRepository publicacionRepository;

    @Autowired
    public PublicacionService(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    public List<Publicacion> obtenerTodas() {
        return publicacionRepository.findAll();
    }

    // Obtener una publicación por ID
    public Optional<Publicacion> obtenerPorId(Long id) {
        return publicacionRepository.findById(id);
    }

    // Crear o actualizar una publicación
    public Publicacion guardar(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    // Eliminar una publicación
    public void eliminar(Long id) {
        publicacionRepository.deleteById(id);
    }

    // Buscar publicaciones por usuario
    public List<Publicacion> obtenerPorUsuario(Long usuarioId) {
        return publicacionRepository.findByUsuarioId(usuarioId);
    }

    public Publicacion actualizar(Long id, Publicacion publicacionActualizada) {
        return publicacionActualizada;
    }
}
