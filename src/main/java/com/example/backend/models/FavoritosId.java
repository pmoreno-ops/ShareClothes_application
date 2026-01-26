package com.example.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FavoritosId implements Serializable {

    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private Long userId;

    @EqualsAndHashCode.Include
    @Column(name = "publicacion_id")
    private Long publicacionId;
}


