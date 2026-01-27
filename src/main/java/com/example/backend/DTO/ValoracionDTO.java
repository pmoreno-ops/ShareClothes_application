package com.example.backend.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValoracionDTO {
    private Long id;
    private UsuarioDTO evaluador;
    private UsuarioDTO evaluado;
    private TransaccionDTO transaccion;
    private int puntuacion;
    private String comentario;
    private String fecha; // LocalDateTime -> String
}
