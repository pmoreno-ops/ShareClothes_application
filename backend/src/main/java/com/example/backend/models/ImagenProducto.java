package com.example.backend.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagenes_producto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ImagenProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagen_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Productos producto; // NO incluir en equals/hashCode

    @Column(nullable = false)
    private String url;
}


