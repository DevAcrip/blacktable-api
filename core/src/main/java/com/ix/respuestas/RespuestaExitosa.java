package com.ix.respuestas;

import java.time.LocalDateTime;

public class RespuestaExitosa<T> {
    private LocalDateTime timestamp;
    private String mensaje;
    private T datos;

    public RespuestaExitosa(String mensaje, T datos) {
        this.timestamp = LocalDateTime.now();
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getDatos() {
        return datos;
    }
}
