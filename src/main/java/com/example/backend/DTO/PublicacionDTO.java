package com.example.backend.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PublicacionDTO {
    private Long id;
    private Long productoId;
    private Long usuarioId;
    private ProductoDTO producto;
    private String tipo;
    private BigDecimal precio;
    private String descripcionExtra;
    private Boolean disponible;
    private String fechaPublicacion;
}
