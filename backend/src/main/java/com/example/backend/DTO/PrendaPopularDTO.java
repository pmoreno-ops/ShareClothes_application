package com.example.backend.DTO;

public record PrendaPopularDTO(
        Long productoId,
        String titulo,
        String descripcion,
        Long totalIntercambios
) {}

