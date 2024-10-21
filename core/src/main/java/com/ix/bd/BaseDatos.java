package com.ix.bd;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ix.interfaces.IOperacionSQL;
import com.ix.utilidades.Excepciones;

import jakarta.servlet.jsp.jstl.sql.Result;

/**
@author
*/

public abstract class BaseDatos {
	
	protected Conexion conexion;
	
	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

	public BaseDatos() {		
	}
	
	public BaseDatos(Conexion conexion) {		
		this.conexion=conexion;
	}
	
	public abstract Result datos(String sql)throws SQLException;
	
	public abstract Result datos(String sql,List<Object> parametros) throws SQLException;
	
	public abstract <D> int ejecutarSql(D d,
	 		String sql, IOperacionSQL<D> operacionSQL) throws Excepciones;
	
	public abstract <D> int ejecutarSQL(D d, Map<String,IOperacionSQL<D>> mapOperacionesSQL) throws Excepciones, SQLException;


}
