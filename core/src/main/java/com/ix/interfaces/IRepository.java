package com.ix.interfaces;

import com.ix.utilidades.Excepciones;

import java.sql.SQLException;
import java.util.List;

public interface IRepository <T>{
    int crear(T dto, IBaseDatos baseDatos) throws Excepciones, SQLException;
    int editar(T dto, IBaseDatos baseDatos) throws Excepciones, SQLException;
    int eliminar(T dto, IBaseDatos baseDatos) throws Excepciones, SQLException;
    <M> List<M> lista(Class<M> clazz,List<Object> parametros,IBaseDatos baseDatos) throws Excepciones;


}
