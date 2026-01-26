package com.example.backend.service;

import com.example.backend.models.Mensaje;
import com.example.backend.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajesService {

    private final MensajeRepository mensajesRepository;

    @Autowired
    public MensajesService(MensajeRepository mensajesRepository) {
        this.mensajesRepository = mensajesRepository;
    }

    // Obtener todos los mensajes
    public List<Mensaje> obtenerTodos() {
        return mensajesRepository.findAll();
    }

    // Obtener mensaje por ID
    public Optional<Mensaje> obtenerPorId(Long id) {
        return mensajesRepository.findById(id);
    }

    // Guardar un mensaje
    public Mensaje guardar(Mensaje mensaje) {
        return mensajesRepository.save(mensaje);
    }

    // Actualizar un mensaje existente
    public Mensaje actualizar(Long id, Mensaje mensajeActualizado) {
        return mensajesRepository.findById(id)
                .map(mensaje -> {
                    mensaje.setRemitente(mensajeActualizado.getRemitente());
                    mensaje.setDestinatario(mensajeActualizado.getDestinatario());
                    mensaje.setPublicacion(mensajeActualizado.getPublicacion());
                    mensaje.setContenido(mensajeActualizado.getContenido());
                    mensaje.setFechaEnvio(mensajeActualizado.getFechaEnvio());
                    mensaje.setLeido(mensajeActualizado.getLeido());
                    return mensajesRepository.save(mensaje);
                })
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con ID: " + id));
    }

    // Eliminar un mensaje
    public void eliminar(Long id) {
        if (mensajesRepository.existsById(id)) {
            mensajesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Mensaje no encontrado con ID: " + id);
        }
    }

    // Obtener mensajes enviados por un usuario
    public List<Mensaje> obtenerPorRemitente(Long remitenteId) {
        return mensajesRepository.findByRemitenteId(remitenteId);
    }

    // Obtener mensajes recibidos por un usuario
    public List<Mensaje> obtenerPorDestinatario(Long destinatarioId) {
        return mensajesRepository.findByDestinatarioId(destinatarioId);
    }

    // Marcar un mensaje como leído
    public Mensaje marcarComoLeido(Long id) {
        return mensajesRepository.findById(id)
                .map(mensaje -> {
                    mensaje.setLeido(true);
                    return mensajesRepository.save(mensaje);
                })
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con ID: " + id));
    }

    public List<Mensaje> obtenerPorUsuario(Long usuarioId) {
        return List.of();
    }
}
