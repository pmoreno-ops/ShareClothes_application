package com.example.backend.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favoritos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Favoritos {

    @EmbeddedId
    @GeneratedValue
    @EqualsAndHashCode.Include
    private FavoritosId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("publicacionId")
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;

}

