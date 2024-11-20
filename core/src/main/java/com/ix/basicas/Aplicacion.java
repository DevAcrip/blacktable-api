package com.ix.basicas;

import com.ix.bd.BaseDatos;
import com.ix.bd.ConexionContext;
import com.ix.bd.ParametrosConexion;
import com.ix.interfaces.IAplicacion;

import jakarta.servlet.ServletContext;

public class Aplicacion implements IAplicacion {
	
	private ServletContext context;
	
	private String cadenaConexion;
	private BaseDatos baseDatos;
	private Integer id;
	
	
	private static Aplicacion aplicacion;
	
	public static Aplicacion getAplicacion(BaseDatos bd,ServletContext context) {
		
		aplicacion = aplicacion!=null?aplicacion:new Aplicacion();	
		aplicacion.setContext(context);
		
		aplicacion.setCadenaConexion(context.getInitParameter("POOLCX"));
		
		aplicacion.setId(Integer.parseInt(context.getInitParameter("IDAPLICACION")));
		
		System.out.print("aplicacion.setId " +aplicacion.getId());
		
		bd.setConexion(new ConexionContext(	new ParametrosConexion(			
								aplicacion.getCadenaConexion())
								));
		aplicacion.setBaseDatos(bd);
		
		return aplicacion;			
	}
	
	public static Aplicacion getAplicacion() {

		return aplicacion!=null?aplicacion:new Aplicacion();		
	}	
	
	private Aplicacion() {
		super();
	}
	
	/**
	 * @return the context
	 */
	public ServletContext getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(ServletContext context) {
		this.context = context;
	}

	/**
	 * @return the cadenaConexion
	 */
	public String getCadenaConexion() {
		return cadenaConexion;
	}

	/**
	 * @param cadenaConexion the cadenaConexion to set
	 */
	public void setCadenaConexion(String cadenaConexion) {
		this.cadenaConexion = cadenaConexion;
	}

	/**
	 * @return the baseDatos
	 */
	public BaseDatos getBaseDatos() {
		return baseDatos;
	}

	/**
	 * @param baseDatos the baseDatos to set
	 */
	public void setBaseDatos(BaseDatos baseDatos) {
		this.baseDatos = baseDatos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
