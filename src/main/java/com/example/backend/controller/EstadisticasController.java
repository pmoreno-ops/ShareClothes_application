package com.example.backend.controller;

import com.example.backend.models.Usuarios;
import com.example.backend.service.UsuariosServices;
import com.example.backend.service.EstadisticasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
public class EstadisticasController {

    private final UsuariosServices usuariosService;
    private final EstadisticasService estadisticasService;

    public EstadisticasController(UsuariosServices usuariosService, EstadisticasService estadisticasService) {
        this.usuariosService = usuariosService;
        this.estadisticasService = estadisticasService;
    }

    // Método existente: usuario con más transacciones aceptadas
    @GetMapping("/estadisticas/usuarioActivo")
    public Usuarios getUsuarioActivo() {
        return usuariosService.getUsuarioConMasTransaccionesAceptadas();
    }

    // Nuevo método: top 5 prendas más populares
    @GetMapping("/estadisticas/prendaPopular")
    public List<Map<String, Object>> top5PrendasPopulares() {
        List<Object[]> results = estadisticasService.top5PrendasPopulares();
        return results.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("prenda_id", r[0]);
            map.put("nombre", r[1]);
            map.put("descripcion", r[2]);
            map.put("total_intercambios", r[3]);
            return map;
        }).toList();
    }
}
