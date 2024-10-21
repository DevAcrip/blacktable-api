package com.ix.bd;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import com.ix.interfaces.IRepository;
import com.ix.utilidades.Constantes;
import com.ix.utilidades.Excepciones;

/**
@author
*/

public abstract class Repository<T> implements IRepository{	
	
	protected String sqlLista;
	protected String sqlConsultar;
	protected String sqlEjecutar;

	public T obtenerObjeto(List<T> lst) {			
		T t= null;		
		t= lst.get(0);	
		return t;
	}	
	
	public T consultar(Class<? extends Object> clase,List<Object> parametrosBD,	 		
	 		BaseDatos basedatos) throws Excepciones{
		List<T> lst=lista(clase,
				sqlConsultar, 
				parametrosBD,
				basedatos);	
		
		
		System.out.println("lst " +lst.size());
		return 	obtenerObjeto(lst);
	}
	
	public <D> List<D> consultar(Class<? extends Object> clase,
	 		String sql,
	 		List<Object> parametrosBD,
	 		BaseDatos baseDatos) throws Excepciones{
		
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
	 		List<Object> parametrosBD,
	 		BaseDatos baseDatos) throws Excepciones{

		return this.lista(clase,this.sqlLista, 
				parametrosBD,baseDatos);	
		
	}	
	
	public List<T> lista(Class<? extends Object> clase,	 		
	 		BaseDatos baseDatos) throws Excepciones{

		return this.lista(clase,this.sqlLista, 
				baseDatos);		
	}

	public List<T> lista(Class<? extends Object> clase,
	 		String sql,
	 		List<Object> parametrosBD,
	 		BaseDatos baseDatos) throws Excepciones{
		
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
	 		BaseDatos baseDatos) throws Excepciones{
		
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
