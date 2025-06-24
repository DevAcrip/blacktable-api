package com.ix.excepciones;

import org.slf4j.LoggerFactory;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.UUID;

import org.slf4j.Logger;
import jakarta.ws.rs.core.MediaType;
import com.ix.respuestas.RespuestaError;

@Provider
public class ExcepcionAplicacionMapper implements ExceptionMapper<ExcepcionAplicacion> {

    private static final Logger log = LoggerFactory.getLogger(ExcepcionAplicacionMapper.class);

    @Override
    public Response toResponse(ExcepcionAplicacion e) {
        String traceId = UUID.randomUUID().toString();
        log.warn("Excepción de aplicación [{}]: {}", traceId, e.getMessage(), e);

        RespuestaError error = new RespuestaError(
            "ERR_VALIDACION",
            "Error de validación",
            "Código de seguimiento: " + traceId
        );

        return Response
            .status(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON)
            .entity(error)
            .build();
    }
}
