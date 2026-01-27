package com.example.backend.models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transacciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaccion_id")
    @EqualsAndHashCode.Include
    private Long id;

    // Relación con publicación
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;

    // Comprador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comprador_id")
    private Usuarios comprador;

    // Vendedor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    private Usuarios vendedor;

    private String tipo;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioFinal;

    @Column(name = "fecha_transaccion")
    private Timestamp fechaTransaccion;

}
