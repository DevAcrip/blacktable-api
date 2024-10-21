package com.ix.bd;

import java.sql.SQLException;

import com.ix.utilidades.Log;

import java.sql.DriverManager;


/**
@author
*/

public class ConexionDriver extends Conexion{
	
	public ConexionDriver(ParametrosConexion parametrosConexion) {
		super(parametrosConexion);
		//TODO Auto-generated constructor stub
	}

	@Override
	public void conectar(){        	
      	
    	try{  
    		Log.registrar(parametrosConexion.cadenaConexion());
      
    		connection = DriverManager.getConnection(parametrosConexion.cadenaConexion(), 
								    				parametrosConexion.usuario(), 
								    				parametrosConexion.clave());   		
    	}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}	
    }  	 

}
