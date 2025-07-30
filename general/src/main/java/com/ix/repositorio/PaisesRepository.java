package com.ix.repositorio;

import java.util.Arrays;
import java.util.List;

import com.ix.dto.PaisesDto;
import com.ix.implementacion.CrudRepository;
import com.ix.interfaces.IBaseDatos;
import com.ix.interfaces.IPaisesRepository;
import com.ix.sql.PAISES;
import com.ix.utilidades.Excepciones;

public class PaisesRepository extends CrudRepository<PaisesDto> implements IPaisesRepository {

    public PaisesRepository(IBaseDatos baseDatos) {
        super(baseDatos);
    }

    @Override
    protected String sqlInsertar() {
        return PAISES.CREAR;
    }

    @Override
    protected String sqlActualizar() {
        return PAISES.EDITAR;
    }

    @Override
    protected String sqlEliminar() {
        return PAISES.ELIMINAR;
    }

    @Override
    protected String sqlObtenerPorId() {
        return PAISES.OBTENER_POR_ID;
    }

    @Override
    protected String sqlObtenerTodos() {
        return PAISES.CONSULTAR;
    }

    @Override
    protected List<Object> paramsInsertar(PaisesDto entidad) {
        return Arrays.asList(
            entidad.getAplicacion(),     // AAPLICACION (siempre va primero)
            entidad.getEmpresa(),        // AEMPRESA
            entidad.getRol(),            // AROL
            entidad.getPrincipal(),      // AUSUARIO (ID del usuario que ejecuta)
            entidad.getIp(),             // AIP
            entidad.getIsotexto(),       // ISOTEXTO
            entidad.getIsonumerico(),    // ISONUMERICO
            entidad.getNombre()          // NOMBRE
        );
    }

    @Override
    protected List<Object> paramsActualizar(PaisesDto entidad) {
        return Arrays.asList(
            entidad.getAplicacion(),     // AAPLICACION (siempre va primero)
            entidad.getEmpresa(),        // AEMPRESA
            entidad.getRol(),            // AROL
            entidad.getPrincipal(),      // AUSUARIO (ID del usuario que ejecuta)
            entidad.getIp(),             // AIP
            entidad.getId(),             // ID
            entidad.getIsotexto(),       // ISOTEXTO
            entidad.getIsonumerico(),    // ISONUMERICO
            entidad.getNombre()          // NOMBRE
        );
    }

    @Override
    protected List<Object> paramsEliminar(Object id) {
        // Para eliminar necesitamos crear un DTO temporal con los parámetros base
        // Esto se puede mejorar pasando el DTO completo desde el servicio
        return Arrays.asList(
            0,  // AAPLICACION - se debe configurar desde el servicio
            0,  // AEMPRESA - se debe configurar desde el servicio
            0,  // AROL - se debe configurar desde el servicio
            0,  // AUSUARIO - se debe configurar desde el servicio
            "localhost",  // AIP - se debe configurar desde el servicio
            id  // ID
        );
    }

    @Override
    protected List<Object> paramsObtenerPorId(Object id) {
        return Arrays.asList(
            0,  // AAPLICACION - se debe configurar desde el servicio
            0,  // AEMPRESA - se debe configurar desde el servicio
            0,  // AROL - se debe configurar desde el servicio
            0,  // AUSUARIO - se debe configurar desde el servicio
            "localhost",  // AIP - se debe configurar desde el servicio
            id  // ID
        );
    }

    @Override
    public List<PaisesDto> consultarPorNombre(String nombre) throws Excepciones {
        // Implementación específica para búsqueda por nombre
        List<Object> parametros = Arrays.asList(
            0,  // AAPLICACION - se debe configurar desde el servicio
            0,  // AEMPRESA - se debe configurar desde el servicio
            0,  // AROL - se debe configurar desde el servicio
            0,  // AUSUARIO - se debe configurar desde el servicio
            "localhost",  // AIP - se debe configurar desde el servicio
            nombre  // NOMBRE
        );
        return obtenerTodos(PaisesDto.class); // Implementación básica, se puede mejorar
    }
}