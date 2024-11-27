package com.ix.bd;

import java.sql.Connection;

/**
@author
*/

public abstract class Conexion {
	
	protected ParametrosConexion parametrosConexion;		
	
	public Conexion(ParametrosConexion parametrosConexion) {
		this.parametrosConexion = parametrosConexion;		
	}
	
	protected Connection connection;
		
	public abstract void conectar();
		
	public void desconectar(){
    	try {
			if(connection!=null) 
				{
					connection.close();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    }    
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection cx) {
		this.connection = cx;
	}
	
	public ParametrosConexion getParametrosConexion() {
		return parametrosConexion;
	}
		
}
