package com.example.backend.service;

import com.example.backend.models.Valoracion;
import com.example.backend.repository.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValoracionService {

    private final ValoracionRepository valoracionesRepository;

    @Autowired
    public ValoracionService(ValoracionRepository valoracionesRepository) {
        this.valoracionesRepository = valoracionesRepository;
    }

    // Obtener todas las valoraciones
    public List<Valoracion> obtenerTodas() {
        return valoracionesRepository.findAll();
    }

    // Obtener valoracion por ID
    public Optional<Valoracion> obtenerPorId(Long id) {
        return valoracionesRepository.findById(id);
    }

    public Valoracion guardar(Valoracion valoracion) {
        return valoracionesRepository.save(valoracion);
    }

    public List<Valoracion> obtenerPorUsuario(Long usuarioId) {
        return valoracionesRepository.findByEvaluadoId(usuarioId);
    }

    // Actualizar una valoracion
    public Valoracion actualizar(Long id, Valoracion valoracionActualizada) {
        return valoracionesRepository.findById(id)
                .map(valoracion -> {
                    valoracion.setEvaluador(valoracionActualizada.getEvaluador());
                    valoracion.setEvaluado(valoracionActualizada.getEvaluado());
                    valoracion.setTransaccion(valoracionActualizada.getTransaccion());
                    valoracion.setPuntuacion(valoracionActualizada.getPuntuacion());
                    valoracion.setComentario(valoracionActualizada.getComentario());
                    valoracion.setFecha(valoracionActualizada.getFecha());
                    return valoracionesRepository.save(valoracion);
                })
                .orElseThrow(() -> new RuntimeException("Valoración no encontrada con ID: " + id));
    }

    // Eliminar una valoracion
    public void eliminar(Long id) {
        if (valoracionesRepository.existsById(id)) {
            valoracionesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Valoración no encontrada con ID: " + id);
        }
    }

    // Métodos adicionales según necesidades, ej. por evaluador o evaluado
    public List<Valoracion> obtenerPorEvaluador(Long evaluadorId) {
        return valoracionesRepository.findByEvaluadorId(evaluadorId);
    }

    public List<Valoracion> obtenerPorEvaluado(Long evaluadoId) {
        return valoracionesRepository.findByEvaluadoId(evaluadoId);
    }
}
