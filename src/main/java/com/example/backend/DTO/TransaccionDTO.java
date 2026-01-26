package com.example.backend.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransaccionDTO {
    private Long id;
    private PublicacionDTO publicacion; // Información de la publicación asociada
    private UsuarioDTO comprador;
    private UsuarioDTO vendedor;
    private String tipo;
    private BigDecimal precioFinal;
    private String fechaTransaccion; // LocalDateTime -> String
}

