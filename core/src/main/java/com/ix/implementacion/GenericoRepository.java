// GenericoRepository.java
package com.ix.implementacion;

import com.ix.bd.ColeccionDatosUtils;
import com.ix.interfaces.IBaseDatos;
import com.ix.interfaces.IGenericoRepository;
import com.ix.utilidades.Excepciones;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.sql.Types;
import java.util.List;

@RequestScoped
public class GenericoRepository<T> implements IGenericoRepository<T> {

    private final IBaseDatos baseDatos;

    @Inject
    public GenericoRepository(IBaseDatos baseDatos) {
        this.baseDatos = baseDatos;
    }

    @Override
    public List<T> ejecutarConsulta(Class<T> tipo, String sqlFuncion, List<Object> parametros) throws Excepciones {
        return ColeccionDatosUtils.lista(tipo, sqlFuncion, parametros, baseDatos);
    }

    @Override
    public int ejecutarFuncion(String sqlFuncion, List<Object> parametros) throws Excepciones {
        return baseDatos.ejecutar(parametros, sqlFuncion, (params, cs) -> {
            for (int i = 0; i < params.size(); i++) {
                cs.setObject(i + 1, params.get(i));
            }
            cs.registerOutParameter(params.size() + 1, Types.INTEGER);
            cs.execute();
            return cs.getInt(params.size() + 1);
        });
    }
}
