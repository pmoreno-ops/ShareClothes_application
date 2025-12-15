package com.example.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Transient
    private String password; // <-- solo para recibir la contraseña temporalmente

    @Column(nullable = false)
    private String telefono;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // RELACIONES

    @OneToMany(mappedBy = "usuario")
    private Set<Publicacion> publicaciones;

    @ManyToMany
    @JoinTable(
            name = "favoritos",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "publicacion_id")
    )
    private Set<Publicacion> favoritos;

    @OneToMany(mappedBy = "destinatario")
    private Set<Mensaje> mensajesRecibidos;

    @OneToMany(mappedBy = "remitente")
    private Set<Mensaje> mensajesEnviados;

    @OneToMany(mappedBy = "comprador")
    private Set<Transaccion> compras;

    @OneToMany(mappedBy = "vendedor")
    private Set<Transaccion> ventas;

    @OneToMany(mappedBy = "evaluador")
    private Set<Valoracion> valoracionesRealizadas;

    @OneToMany(mappedBy = "evaluado")
    private Set<Valoracion> valoracionesRecibidas;
}
