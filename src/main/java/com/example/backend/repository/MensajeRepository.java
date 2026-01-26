package com.example.backend.repository;

import com.example.backend.models.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Long> {
    List<Mensaje> findByRemitenteId(Long remitenteId);

    List<Mensaje> findByDestinatarioId(Long destinatarioId);
}
