package com.ix.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.ix.bd.BaseDatos;
import com.ix.utilidades.Excepciones;

public interface IRepository <T>{
	
	public int crear(T dto, BaseDatos baseDatos) throws Excepciones, SQLException;
	public int editar(T dto, BaseDatos baseDatos) throws Excepciones, SQLException;
	public int eliminar(T dto, BaseDatos baseDatos) throws Excepciones, SQLException;
	
	public List<T> lista(List<Object> parametrosBD,
	 		BaseDatos baseDatos) throws Excepciones;

}
