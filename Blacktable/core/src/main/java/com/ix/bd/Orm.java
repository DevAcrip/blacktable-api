package com.ix.bd;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.ix.interfaces.IValorPropiedad;

/**
@author
*/

public class Orm<T> {
	
	private HashMap<String, IValorPropiedad> mapValorPropiedad;
	private HashMap<String, Field> mapPropiedades;
	
	public T crearInstancia(Class<? extends Object> clase) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T t = (T) clase.getConstructor().newInstance();		
		return t;
	}
	
	private void agregarPropiedad(Field[] propiedades, List<String> lstColumnas)  {		
		
		for (Field propiedad : propiedades) {
			String nombre=propiedad.getName().toUpperCase();
			
			if(lstColumnas.contains(nombre)) {
				this.mapPropiedades.put(nombre, propiedad);	
				try {
					//Se podira crear un map con las propiedades y los metodos, pero para evitar upper en cada recorrido de valores, se crea esta:
					this.mapValorPropiedad.put(propiedad.getName(), ValoresPropiedad.seleccionar(propiedad)); 
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	private void inicializarMap() {
		this.mapPropiedades= new HashMap<String, Field>();		
		this.mapValorPropiedad = new HashMap<String, IValorPropiedad>();
	}	
	
	public  void propiedadesClase(Class<? extends Object> clase,String[] columnas) {
		
		inicializarMap();
		List<String> lstColumnas=Arrays.asList(columnas);
		
		agregarPropiedad(clase.getDeclaredFields(),lstColumnas);
		agregarPropiedad(clase.getSuperclass().getDeclaredFields(),lstColumnas);
		agregarPropiedad(clase.getSuperclass().getSuperclass().getDeclaredFields(),lstColumnas);		
		
	}
		
	public  void recuperarValoresFila(T instancia,SortedMap<String, Object> fila){			
		try {				
				for(Map.Entry<String, Field> e : this.mapPropiedades.entrySet()) 		
				{
					Object valor=fila.get(e.getKey());	
					if(valor!=null)
						asignarValor(instancia,e.getValue(),valor);
				}
								
			} catch (Exception e) {
				e.printStackTrace();
		} 
	}
	
	private  void asignarValor(T instancia, Field propiedad, Object valor) throws IllegalAccessException {	
		propiedad.setAccessible(true);
		fijarValor(instancia,propiedad,valor);
	}	
	
	private  void fijarValor(T instancia, Field propiedad, Object valor) throws IllegalAccessException {
	   IValorPropiedad i = this.mapValorPropiedad.get(propiedad.getName());
	   propiedad.set(instancia, i.asignar(valor));
	}		

}
