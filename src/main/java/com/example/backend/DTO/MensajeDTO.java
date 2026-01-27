package com.example.backend.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensajeDTO {
    private Long id;
    private UsuarioDTO remitente;
    private UsuarioDTO destinatario;
    private PublicacionDTO publicacion;
    private String contenido;
    private String fechaEnvio; // LocalDateTime -> String
    private Boolean leido;
}

