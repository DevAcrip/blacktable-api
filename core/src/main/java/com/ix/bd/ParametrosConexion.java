package com.ix.bd;

public record ParametrosConexion(String cadenaConexion,String usuario,String clave) {
	
	public ParametrosConexion(String cadenaConexion) {
		 this(cadenaConexion, null, null);
	 }
}
