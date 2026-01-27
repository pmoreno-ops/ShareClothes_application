package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El campo 'titulo' es obligatorio")
    private String titulo;

    private String descripcion;

    private String categoria;

    private String genero;

    private String estado;

    private String marca;

    private String talla;

    private String color;

    private String imagenPrincipal;

    private LocalDateTime fechaCreacion;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String titulo, String descripcion, String categoria,
                       String marca, String imagenPrincipal, LocalDateTime fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.marca = marca;
        this.imagenPrincipal = imagenPrincipal;
        this.fechaCreacion = fechaCreacion;
    }

}
