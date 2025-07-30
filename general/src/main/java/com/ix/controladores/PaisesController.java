package com.ix.controladores;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ix.controladores.BaseController;
import com.ix.dto.PaisesDto;
import com.ix.interfaces.IAplicacion;
import com.ix.interfaces.IGenericServices;
import com.ix.interfaces.IPaisesServices;
import com.ix.respuestas.RespuestaExitosa;
import com.ix.utilidades.Excepciones;

public class PaisesController extends BaseController<PaisesDto> {

    private final IPaisesServices paisesServices;

    public PaisesController(IPaisesServices paisesServices, IAplicacion aplicacion) {
        super(paisesServices, aplicacion);
        this.paisesServices = paisesServices;
    }

    // Los métodos CRUD básicos se heredan de BaseController
    // obtenerTodos(), obtenerPorId(), crear(), actualizar(), eliminar()

    /**
     * Buscar países por nombre (método específico de países)
     */
    public RespuestaExitosa<List<PaisesDto>> buscarPorNombre(String nombre, Map<String, String> headers) throws Excepciones {
        configurarContextoAplicacion(headers);

        List<PaisesDto> paises = paisesServices.consultarPorNombre(nombre);
        
        return new RespuestaExitosa<>("Búsqueda completada exitosamente", paises);
    }

    // Implementación de métodos abstractos de BaseController

    @Override
    protected String getEntityName() {
        return "País";
    }

    @Override
    protected PaisesDto createNewInstance() {
        return new PaisesDto();
    }

    @Override
    protected void configurarCamposAuditoriaEspecificos(PaisesDto dto, Map<String, String> headers, boolean esCreacion) {
        Integer usuarioId = parseInteger(headers.get("X-Usuario-Id"));
        String ipCliente = headers.get("X-IP-Cliente");

        // Configurar campos específicos de auditoría de PaisesDto
        // Los campos base (empresa, aplicacion, principal, rol, ip) ya se configuran en BaseController
        if (esCreacion) {
            // Campos para creación
            dto.setIdUsuarioCrea(usuarioId != null ? usuarioId : 0);
            dto.setIpCrea(ipCliente != null ? ipCliente : "localhost");
            dto.setFechaCrea(new Date());
        } else {
            // Campos para edición
            dto.setIdUsuarioEdita(usuarioId != null ? usuarioId : 0);
            dto.setIpEdita(ipCliente != null ? ipCliente : "localhost");
            dto.setFechaEdita(new Date());
        }
    }
}