package com.ix.controladores;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ix.basicas.Dto;
import com.ix.interfaces.IAplicacion;
import com.ix.interfaces.IGenericServices;
import com.ix.respuestas.RespuestaExitosa;
import com.ix.utilidades.Excepciones;

public abstract class BaseController<T extends Dto> {

    protected final IGenericServices<T> service;
    protected final IAplicacion aplicacion;

    public BaseController(IGenericServices<T> service, IAplicacion aplicacion) {
        this.service = service;
        this.aplicacion = aplicacion;
    }

    /**
     * Obtener todos los registros
     */
    public RespuestaExitosa<List<T>> obtenerTodos(Map<String, String> headers) throws Excepciones {
        configurarContextoAplicacion(headers);
        
        List<T> lista = service.lista();
        
        return new RespuestaExitosa<>(getSuccessMessage("obtenidos"), lista);
    }

    /**
     * Obtener registro por ID
     */
    public RespuestaExitosa<T> obtenerPorId(int id, Map<String, String> headers) throws Excepciones {
        configurarContextoAplicacion(headers);

        T entidad = service.obtenerEntidad(id);
        
        return new RespuestaExitosa<>(getSuccessMessage("obtenido"), entidad);
    }

    /**
     * Crear nuevo registro
     */
    public RespuestaExitosa<T> crear(T dto, Map<String, String> headers) throws Excepciones, SQLException {
        configurarContextoAplicacion(headers);

        // Configurar campos de auditoría
        configurarCamposAuditoria(dto, headers, true);

        T entidadCreada = service.crea(dto);
        
        return new RespuestaExitosa<>(getSuccessMessage("creado"), entidadCreada);
    }

    /**
     * Actualizar registro existente
     */
    public RespuestaExitosa<T> actualizar(int id, T dto, Map<String, String> headers) throws Excepciones, SQLException {
        configurarContextoAplicacion(headers);

        // Configurar ID y campos de auditoría
        dto.setId(id);
        configurarCamposAuditoria(dto, headers, false);

        T entidadActualizada = service.edita(dto);
        
        return new RespuestaExitosa<>(getSuccessMessage("actualizado"), entidadActualizada);
    }

    /**
     * Eliminar registro
     */
    public RespuestaExitosa<String> eliminar(int id, Map<String, String> headers) throws Excepciones, SQLException {
        configurarContextoAplicacion(headers);

        T dto = createNewInstance();
        dto.setId(id);
        configurarCamposAuditoria(dto, headers, false);

        service.elimina(dto);
        
        return new RespuestaExitosa<>(
            getSuccessMessage("eliminado"), 
            getEntityName() + " con ID " + id + " eliminado correctamente"
        );
    }

    /**
     * Configurar campos de auditoría en el DTO
     */
    protected void configurarCamposAuditoria(T dto, Map<String, String> headers, boolean esCreacion) {
        Integer usuarioId = parseInteger(headers.get("X-Usuario-Id"));
        Integer empresaId = parseInteger(headers.get("X-Empresa-Id"));
        Integer aplicacionId = parseInteger(headers.get("X-Aplicacion-Id"));
        Integer rolId = parseInteger(headers.get("X-Rol-Id"));
        String ipCliente = headers.get("X-IP-Cliente");

        // Configurar campos base del DTO heredados de Dto
        dto.setPrincipal(usuarioId != null ? usuarioId : 0);  // ID del usuario que ejecuta la acción
        dto.setEmpresa(empresaId != null ? empresaId : 0);    // ID de la empresa
        dto.setAplicacion(aplicacionId != null ? aplicacionId : 0);  // ID de la aplicación
        dto.setRol(rolId != null ? rolId : 0);                // ID del rol
        dto.setIp(ipCliente != null ? ipCliente : "localhost"); // IP del cliente

        // Configurar campos específicos de auditoría si el DTO los tiene
        configurarCamposAuditoriaEspecificos(dto, headers, esCreacion);
    }

    /**
     * Configurar el contexto de aplicación con los parámetros del header
     */
    protected void configurarContextoAplicacion(Map<String, String> headers) {
        // Extraer parámetros del header
        String authToken = headers.get("Authorization");
        Integer usuarioId = parseInteger(headers.get("X-Usuario-Id"));
        Integer empresaId = parseInteger(headers.get("X-Empresa-Id"));
        Integer aplicacionId = parseInteger(headers.get("X-Aplicacion-Id"));
        Integer rolId = parseInteger(headers.get("X-Rol-Id"));
        String ipCliente = headers.get("X-IP-Cliente");

        // Validar JWT
        if (!validarJWT(authToken)) {
            throw new RuntimeException("Token JWT inválido");
        }

        // La configuración del contexto se maneja a través de los DTOs
        // Los parámetros del header se usan directamente en los métodos
        // que configuran los campos de auditoría en los DTOs
    }

    /**
     * Método utilitario para parsear enteros de forma segura
     */
    protected Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Validar token JWT (implementación básica)
     */
    protected boolean validarJWT(String token) {
        // Aquí se implementaría la validación real del JWT
        // Por ahora retorna true para permitir el funcionamiento
        return token != null && token.startsWith("Bearer ");
    }

    // Métodos abstractos que deben ser implementados por las clases hijas

    /**
     * Obtener el nombre de la entidad para mensajes
     */
    protected abstract String getEntityName();

    /**
     * Crear una nueva instancia del DTO
     */
    protected abstract T createNewInstance();

    /**
     * Configurar campos de auditoría específicos del DTO
     */
    protected abstract void configurarCamposAuditoriaEspecificos(T dto, Map<String, String> headers, boolean esCreacion);

    /**
     * Obtener mensaje de éxito personalizado
     */
    protected String getSuccessMessage(String accion) {
        return getEntityName() + " " + accion + " exitosamente";
    }
}