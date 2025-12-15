package com.example.backend.repository;

import com.example.backend.models.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {

    @Query(value = """
        SELECT 
            p.producto_id,
            p.titulo,
            p.descripcion,
            COUNT(t.transaccion_id) AS total_intercambios
        FROM productos p
        JOIN publicacion_productos pp ON p.producto_id = pp.producto_id
        JOIN transacciones t ON pp.publicacion_id = t.publicacion_id
        GROUP BY p.producto_id, p.titulo, p.descripcion
        ORDER BY total_intercambios DESC
        LIMIT 5
        """, nativeQuery = true)
    List<Object[]> findTop5PrendasPopulares();
}
