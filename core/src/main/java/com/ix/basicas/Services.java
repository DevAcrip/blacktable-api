package com.ix.basicas;

import com.ix.interfaces.IRepository;
import com.ix.interfaces.IServices;

/**
@author
*/
public class Services<T> implements IServices{
	
	protected Aplicacion aplicacion;
	protected IRepository repository;
	
	public Services(Aplicacion aplicacion, IRepository repository) {		
		this.aplicacion=aplicacion;
		this.repository=repository;
	}	
	
	public Services(Aplicacion aplicacion) {		
		this.aplicacion=aplicacion;
	}	
	
}
