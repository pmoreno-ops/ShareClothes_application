package com.example.backend.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImagenProductoDTO {
    private Long imagenId;
    private ProductoDTO producto;
    private String url;
}
