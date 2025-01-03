package com.ix.bd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import com.ix.interfaces.IBaseDatos;
import com.ix.utilidades.Constantes;
import com.ix.utilidades.Excepciones;

/**
@author
*/

public class AuxiliarRepository<T> {	
	
	public T obtenerEntidad(List<T> lst) {			
		T t= null;		
		t= lst.get(0);	
		return t;
	}	
	
	public T obtenerObjeto(Class<? extends Object> clase,
			String sql,
			List<Object> parametrosBD,	 		
	 		IBaseDatos basedatos) throws Excepciones{
		List<T> lst=lista(clase,
				sql, 
				parametrosBD,
				basedatos);	
		
		
		System.out.println("lst " +lst.size());
		return 	obtenerEntidad(lst);
	}
	
	public <D> List<D> listaEntidades(Class<? extends Object> clase,
	 		String sql,
	 		List<Object> parametrosBD,
	 		IBaseDatos baseDatos) throws Excepciones{
		
		List<D> lst= null;
		System.out.println("sql "+sql);
		
		lst =new ColeccionDatos<D>().listaObjetos(clase,sql, 
					parametrosBD,baseDatos);
		
		 if(lst==null) {
				throw new NullPointerException(Constantes.LISTANULL);
			}

		return lst;		
	}
	
	public List<T> lista(Class<? extends Object> clase,
	 		String sql,
	 		List<Object> parametrosBD,
	 		IBaseDatos baseDatos) throws Excepciones{
		
		List<T> lst= null;
		System.out.println("sql "+sql);
		
		lst =new ColeccionDatos<T>().listaObjetos(clase,sql, 
					parametrosBD,baseDatos);
		
		 if(lst==null) {
				throw new NullPointerException(Constantes.LISTANULL);
			}
			
		return lst;		
	}
	
	public List<T> lista(Class<? extends Object> clase,
	 		String sql,
	 		IBaseDatos baseDatos) throws Excepciones{
		
		List<T> lst= null;
		
		lst =new ColeccionDatos<T>().listaObjetos(clase,sql, 
				baseDatos);
		
		 if(lst==null) {
				throw new NullPointerException(Constantes.LISTANULL);
			}
		return lst;
		
	}
	
	public int ejecutar(CallableStatement cs,List<Object> parametros) throws SQLException {
		cs.registerOutParameter(1, java.sql.Types.INTEGER);

		for(int i=0;i<parametros.size();i++) {
			cs.setObject(i+2,parametros.get(i));
		}
		
		cs.execute();

         int resultado = cs.getInt(1);	
         cs.clearParameters();
         return resultado;
	}
	
}
