package com.ix.bd;

import java.sql.SQLException;

import javax.naming.NamingException;

/**
@author
*/


public class ConexionContext extends Conexion{
	
	 public ConexionContext(ParametrosConexion parametrosConexion) {
		super(parametrosConexion);
		//TODO Auto-generated constructor stub
	}

	 @Override
	 public void conectar(){  
			
	    	try{  
	    		System.out.println("0.Antes de conectar... "+this.parametrosConexion.cadenaConexion());
	    		javax.naming.InitialContext ctx = new javax.naming.InitialContext();

	    		javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup(this.parametrosConexion.cadenaConexion());

	    		connection = ds.getConnection();

	    		System.out.println("4.Despues de conectar... "+this.parametrosConexion.cadenaConexion());
	    	}
			catch(SQLException ex) {
				ex.printStackTrace();
			} catch (NamingException e) {

				e.printStackTrace();
			}    		
	        	
	    }  	 

}
