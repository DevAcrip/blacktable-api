package com.ix.implementacion;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ix.bd.AuxiliarRepository;
import com.ix.bd.BaseDatos;
import com.ix.interfaces.IBaseDatos;
import com.ix.interfaces.IOperacionSQL;
import com.ix.interfaces.IRepository;
import com.ix.interfaces.IParametrosFunction;
import com.ix.utilidades.Excepciones;

import static com.ix.auxiliares.ServiciosMapear.mapearLista;

public abstract class BaseRepository<T> implements IRepository<T> {

    protected String sqlCrear;
    protected String sqlEditar;
    protected String sqlElimimar;
    protected String sqlConsultar;

    protected List<Object> parametros = new ArrayList<>();

    private IParametrosFunction<T> propiedadesAParametros;

    public void setPropiedadesAParametros(IParametrosFunction<T> propiedadesAParametros) {
        this.propiedadesAParametros = propiedadesAParametros;
    }

    private IOperacionSQL<T> crear = (T d, CallableStatement cs) -> {
        int resultado = 0;
        propiedadesAParametros.apply(d, parametros);
        AuxiliarRepository<T> repositorio = new AuxiliarRepository<>();
        resultado = repositorio.ejecutar(cs, parametros);
        return resultado;
    };

    private IOperacionSQL<T> editar = (T d, CallableStatement cs) -> {
        int resultado = 0;
        propiedadesAParametros.apply(d, parametros);
        AuxiliarRepository<T> repositorio = new AuxiliarRepository<>();
        resultado = repositorio.ejecutar(cs, parametros);
        return resultado;
    };

    private IOperacionSQL<T> eliminar = (T d, CallableStatement cs) -> {
        int resultado = 0;
        propiedadesAParametros.apply(d, parametros);
        AuxiliarRepository<T> repositorio = new AuxiliarRepository<>();
        resultado = repositorio.ejecutar(cs, parametros);
        return resultado;
    };

    public int crear(T dto, IBaseDatos baseDatos) throws Excepciones, SQLException {
        int resultado = 0;
        Map<String, IOperacionSQL<T>> lstOperacionesSQL = new LinkedHashMap<>();
        lstOperacionesSQL.put(sqlCrear, crear);
        resultado = baseDatos.ejecutarSQL(dto, lstOperacionesSQL);
        return resultado;
    }

    public int editar(T dto, IBaseDatos baseDatos) throws Excepciones, SQLException {
        int resultado = 0;
        Map<String, IOperacionSQL<T>> lstOperacionesSQL = new LinkedHashMap<>();
        lstOperacionesSQL.put(sqlEditar, editar);
        resultado = baseDatos.ejecutarSQL(dto, lstOperacionesSQL);
        return resultado;
    }

    public int eliminar(T dto, IBaseDatos baseDatos) throws Excepciones, SQLException {
        int resultado = 0;
        Map<String, IOperacionSQL<T>> lstOperacionesSQL = new LinkedHashMap<>();
        lstOperacionesSQL.put(sqlElimimar, eliminar);
        resultado = baseDatos.ejecutarSQL(dto, lstOperacionesSQL);
        return resultado;
    }

    public <M> List<M> lista(Class<M> clazz,List<Object> parametros,IBaseDatos baseDatos) throws Excepciones {
        AuxiliarRepository<M> repositorio = new AuxiliarRepository<>();
        return repositorio.lista(clazz, sqlConsultar, parametros, baseDatos);
    }
}