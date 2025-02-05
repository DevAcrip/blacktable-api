package com.ix.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ix.utilidades.Excepciones;

import jakarta.servlet.jsp.jstl.sql.Result;

public interface IBaseDatos {

    IConexion getConexion();

    void setConexion(IConexion conexion);

    Result datos(String sql) throws SQLException;

    Result datos(String sql, List<Object> parametros) throws SQLException;

    <D> int ejecutarSql(D d, String sql, IOperacionSQL<D> operacionSQL) throws Excepciones;

    <D> int ejecutarSQL(D d, Map<String, IOperacionSQL<D>> mapOperacionesSQL) throws Excepciones, SQLException;
}