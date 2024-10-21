package com.ix.bd;
/**
@author
*/
public record ParametrosConexion(String cadenaConexion,String usuario,String clave) {
	
	public ParametrosConexion(String cadenaConexion) {
		 this(cadenaConexion, null, null);
	 }
}
