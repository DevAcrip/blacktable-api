package com.ix.interfaces;

public interface IBaseDatos extends IEjecutorSQL, IConsultorSQL {
	    IConexion getConexion();
	    
}