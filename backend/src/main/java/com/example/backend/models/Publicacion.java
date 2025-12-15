package com.example.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "publicaciones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publicacion_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Productos producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    private String tipo;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "descripcion_extra")
    private String descripcionExtra;

    private Boolean disponible;

    @Column(name = "fecha_publicacion")
    private Timestamp fechaPublicacion;

    @ManyToMany(mappedBy = "favoritos")
    private Set<Usuarios> usuariosFavoritos;
}
