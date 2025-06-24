package com.ix.utilidades;

import java.sql.SQLException;

public class Excepciones extends Exception{

	private static final long serialVersionUID = -614427005909535868L;

	public Excepciones(String mensaje) {
		super(mensaje);		
	}

	public Excepciones(String string, SQLException e) {
		// TODO Auto-generated constructor stub
	}
	
}
