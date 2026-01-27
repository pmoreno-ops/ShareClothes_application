package com.example.backend.service;

import com.example.backend.models.Favoritos;
import com.example.backend.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritosService {

    private final FavoritoRepository favoritosRepository;

    @Autowired
    public FavoritosService(FavoritoRepository favoritosRepository) {
        this.favoritosRepository = favoritosRepository;
    }

    // Obtener todos los favoritos
    public List<Favoritos> obtenerTodos() {
        return favoritosRepository.findAll();
    }

    // Obtener favoritos de un usuario
    public List<Favoritos> obtenerPorUsuario(Long userId) {
        return favoritosRepository.findByUsuario_Id(userId);
    }

    // Guardar un favorito
    public Favoritos guardar(Favoritos favorito) {
        return favoritosRepository.save(favorito);
    }

    // Eliminar un favorito por userId y publicacionId
    public void eliminar(Long userId, Long publicacionId) {
        Optional<Favoritos> favorito = Optional.ofNullable(favoritosRepository.findByUsuario_IdAndPublicacion_Id(userId, publicacionId));
        if (favorito.isPresent()) {
            favoritosRepository.delete(favorito.get());
        } else {
            throw new RuntimeException("Favorito no encontrado para userId: " + userId + " y publicacionId: " + publicacionId);
        }
    }
}
