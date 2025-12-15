package com.example.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "producto_id")
    private Long id;

    private String titulo;

    @Column(length = 200)
    private String descripcion;

    private String categoria;
    private String genero;
    private String estado;
    private String marca;
    private String talla;
    private String color;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private Set<ImagenProducto> imagenes;

    @OneToMany(mappedBy = "producto")
    private Set<Publicacion> publicaciones;
}
