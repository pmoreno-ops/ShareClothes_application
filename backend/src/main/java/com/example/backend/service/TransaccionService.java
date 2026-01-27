package com.example.backend.service;

import com.example.backend.models.Transaccion;
import com.example.backend.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    @Autowired
    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    // Obtener todas las transacciones
    public List<Transaccion> obtenerTodas() {
        return transaccionRepository.findAll();
    }

    // Obtener transacción por ID
    public Optional<Transaccion> obtenerPorId(Long id) {
        return transaccionRepository.findById(id);
    }

    // Guardar una transacción
    public Transaccion guardar(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    // Actualizar una transacción existente
    public Transaccion actualizar(Long id, Transaccion transaccionActualizada) {
        return transaccionRepository.findById(id)
                .map(transaccion -> {
                    transaccion.setPublicacion(transaccionActualizada.getPublicacion());
                    transaccion.setComprador(transaccionActualizada.getComprador());
                    transaccion.setVendedor(transaccionActualizada.getVendedor());
                    transaccion.setTipo(transaccionActualizada.getTipo());
                    transaccion.setPrecioFinal(transaccionActualizada.getPrecioFinal());
                    transaccion.setFechaTransaccion(transaccionActualizada.getFechaTransaccion());
                    return transaccionRepository.save(transaccion);
                })
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada con ID: " + id));
    }

    // Eliminar una transacción
    public void eliminar(Long id) {
        if (transaccionRepository.existsById(id)) {
            transaccionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Transacción no encontrada con ID: " + id);
        }
    }

    // Obtener transacciones por comprador
    public List<Transaccion> obtenerPorComprador(Long compradorId) {
        return transaccionRepository.findByCompradorId(compradorId);
    }

    // Obtener transacciones por vendedor
    public List<Transaccion> obtenerPorVendedor(Long vendedorId) {
        return transaccionRepository.findByVendedorId(vendedorId);
    }
}
