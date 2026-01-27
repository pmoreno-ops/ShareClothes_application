package com.example.backend.repository;

import com.example.backend.models.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {
    List<Publicacion> findByUsuarioId(Long usuarioId);
}
