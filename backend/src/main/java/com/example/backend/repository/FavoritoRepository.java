package com.example.backend.repository;

import com.example.backend.models.Favoritos;
import com.example.backend.models.FavoritosId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favoritos, FavoritosId> {

    // Buscar favoritos por usuario (a través de la relación)
    List<Favoritos> findByUsuario_Id(Long userId);

    // Opcional: buscar favoritos por publicación
    List<Favoritos> findByPublicacion_Id(Long publicacionId);

    // Opcional: buscar un favorito específico de un usuario y publicación
    Favoritos findByUsuario_IdAndPublicacion_Id(Long userId, Long publicacionId);
}
