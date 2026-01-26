package com.example.backend.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException() {
        super("Usuario no encontrado");
    }

    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
