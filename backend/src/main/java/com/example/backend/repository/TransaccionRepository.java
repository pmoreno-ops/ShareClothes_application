package com.example.backend.repository;

import com.example.backend.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByCompradorId(Long compradorId);

    List<Transaccion> findByVendedorId(Long vendedorId);
}
