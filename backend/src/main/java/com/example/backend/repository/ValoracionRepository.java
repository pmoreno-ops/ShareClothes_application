package com.example.backend.repository;

import com.example.backend.models.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion,Long>{

    List<Valoracion> findByEvaluadorId(Long evaluadorId);

    List<Valoracion> findByEvaluadoId(Long evaluadoId);
}
