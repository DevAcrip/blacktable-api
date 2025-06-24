// CrudRepository.java (Base Genérico CRUD)
package com.ix.implementacion;

import com.ix.bd.ColeccionDatosUtils;
import com.ix.interfaces.IBaseDatos;
import com.ix.utilidades.Excepciones;

import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class CrudRepository<T> {

    private final IBaseDatos baseDatos;

    @Inject
    public CrudRepository(IBaseDatos baseDatos) {
        this.baseDatos = baseDatos;
    }

    protected abstract String sqlInsertar();
    protected abstract String sqlActualizar();
    protected abstract String sqlEliminar();
    protected abstract String sqlObtenerPorId();
    protected abstract String sqlObtenerTodos();

    protected abstract List<Object> paramsInsertar(T entidad);
    protected abstract List<Object> paramsActualizar(T entidad);
    protected abstract List<Object> paramsEliminar(Object id);
    protected abstract List<Object> paramsObtenerPorId(Object id);

    public int insertar(T entidad) throws Excepciones {
        return baseDatos.ejecutar(paramsInsertar(entidad), sqlInsertar(), (params, cs) -> {
            for (int i = 0; i < params.size(); i++) cs.setObject(i + 1, params.get(i));
            cs.registerOutParameter(params.size() + 1, java.sql.Types.INTEGER);
            cs.execute();
            return cs.getInt(params.size() + 1);
        });
    }

    public int actualizar(T entidad) throws Excepciones {
        return baseDatos.ejecutar(paramsActualizar(entidad), sqlActualizar(), (params, cs) -> {
            for (int i = 0; i < params.size(); i++) cs.setObject(i + 1, params.get(i));
            cs.registerOutParameter(params.size() + 1, java.sql.Types.INTEGER);
            cs.execute();
            return cs.getInt(params.size() + 1);
        });
    }

    public int eliminar(Object id) throws Excepciones {
        return baseDatos.ejecutar(paramsEliminar(id), sqlEliminar(), (params, cs) -> {
            for (int i = 0; i < params.size(); i++) cs.setObject(i + 1, params.get(i));
            cs.registerOutParameter(params.size() + 1, java.sql.Types.INTEGER);
            cs.execute();
            return cs.getInt(params.size() + 1);
        });
    }

    public Optional<T> obtenerPorId(Class<T> tipo, Object id) throws Excepciones {
        List<T> resultado = ColeccionDatosUtils.lista(tipo, sqlObtenerPorId(), paramsObtenerPorId(id), baseDatos);
        return resultado.isEmpty() ? Optional.empty() : Optional.of(resultado.get(0));
    }

    public List<T> obtenerTodos(Class<T> tipo) {
        return ColeccionDatosUtils.lista(tipo, sqlObtenerTodos(), Collections.emptyList(), baseDatos);
    }
}
