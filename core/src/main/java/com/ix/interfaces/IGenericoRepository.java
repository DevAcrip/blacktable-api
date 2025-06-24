package com.ix.interfaces;

import java.util.List;

import com.ix.utilidades.Excepciones;

public interface IGenericoRepository<T> {
	 List<T> ejecutarConsulta(Class<T> tipo,String sqlFuncion, List<Object> parametros) throws Excepciones;
	 
	 int ejecutarFuncion(String sqlFuncion, List<Object> parametros) throws Excepciones;
}
