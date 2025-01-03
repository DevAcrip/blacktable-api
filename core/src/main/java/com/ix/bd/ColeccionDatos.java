package com.ix.bd;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.ix.interfaces.IBaseDatos;
import jakarta.servlet.jsp.jstl.sql.Result;

/**
@author
*/

public class ColeccionDatos<T> {
	
	@SuppressWarnings("unchecked")
	private void datosAColeccion(Class<? extends Object> clase, List<T> coleccion, Result rs)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		Orm<T> orm = new Orm<T>();
		
		orm.propiedadesClase(clase,rs.getColumnNames());
		
		for (SortedMap<String, Object> fila : rs.getRows()) {
								
			T m =orm.crearInstancia(clase);
			
			orm.recuperarValoresFila(m,fila);
			
			coleccion.add((T) m);
		}
	}	
	
	public List<T>  listaObjetos(Class<? extends Object> clase,String sql,
			IBaseDatos basedatos){
		List<T> coleccion= null;		
		
			try {				
				Result rs=basedatos.datos(sql);				
			 
				coleccion= new ArrayList<T>();
				datosAColeccion(clase, coleccion, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return coleccion;
	}
	
	 public List<T>  listaObjetos(Class<? extends Object> clase,
			 		String sql,
			 		List<Object> parametros,
					IBaseDatos basedatos){
		 
		List<T> coleccion=null;		

			try {				
				Result rs=basedatos.datos(sql,parametros);
				
				coleccion= new ArrayList<T>();
				
				datosAColeccion(clase, coleccion, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return coleccion;
		}
		
	public Map<String, Integer> mapDatos(String sql, List<Object> parametros, IBaseDatos basedatos){
		
		Map<String, Integer> coleccion =null;
		try {
			Result rs=basedatos.datos(sql,parametros);
			
			int numeroFilas=rs.getRowCount();
			
			Object[][] filas=rs.getRowsByIndex();
			
			coleccion = new LinkedHashMap<String, Integer>();
				for(int i=0; i<numeroFilas;i++)
				{
					coleccion.put(filas[i][1].toString(),((BigDecimal)filas[i][0]).intValue());
				}
			} catch (Exception e) {						
				e.printStackTrace();
	    	}
	    return coleccion;	
	  }	

}
