package com.example.backend.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoritosDTO {
    private Long userId;
    private Long publicacionId;
}

