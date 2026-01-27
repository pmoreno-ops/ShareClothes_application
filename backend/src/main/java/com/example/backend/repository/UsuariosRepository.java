package com.example.backend.repository;

import com.example.backend.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{

    Optional<Usuarios> findById(Long id);

    boolean existsByEmail(String email);

    @Query("""
        SELECT t.vendedor 
        FROM Transaccion t 
        WHERE t.tipo = 'aceptado' 
        GROUP BY t.vendedor 
        ORDER BY COUNT(t) DESC
    """)
    Usuarios findUsuarioConMasTransaccionesAceptadas();
}
