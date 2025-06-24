package com.ix.respuestas;

import java.time.LocalDateTime;

	public class RespuestaError {
	    private LocalDateTime timestamp;
	    private String codigo;
	    private String mensaje;
	    private String detalle;

	    public RespuestaError(String codigo, String mensaje, String detalle) {
	        this.timestamp = LocalDateTime.now();
	        this.codigo = codigo;
	        this.mensaje = mensaje;
	        this.detalle = detalle;
	    }

	    // Getters y Setters
	    public LocalDateTime getTimestamp() {
	        return timestamp;
	    }

	    public String getCodigo() {
	        return codigo;
	    }

	    public String getMensaje() {
	        return mensaje;
	    }

	    public String getDetalle() {
	        return detalle;
	    }
	}
