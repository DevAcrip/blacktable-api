package com.ix.excepciones;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ix.respuestas.RespuestaError;

import java.util.UUID;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger log = LoggerFactory.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {
        String traceId = UUID.randomUUID().toString();

        log.error("Error inesperado [{}]: {}", traceId, exception.toString(), exception);

        RespuestaError error = new RespuestaError(
            "ERR_INTERNO",
            "Error inesperado en el servidor",
            "Código de seguimiento: " + traceId
        );

        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .type(MediaType.APPLICATION_JSON)
            .entity(error)
            .build();
    }
}
