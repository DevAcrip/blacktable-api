package com.ix.basicas;


import com.ix.interfaces.IAplicacion;
import com.ix.interfaces.IRepository;
import com.ix.interfaces.IServices;

/**
@author
*/
public class Services<T> implements IServices{
	
	protected IAplicacion aplicacion;
	protected IRepository repository;
	
	public Services(IAplicacion aplicacion, IRepository repository) {		
		this.aplicacion=aplicacion;
		this.repository=repository;
	}	
	
	public Services(IAplicacion aplicacion) {		
		this.aplicacion=aplicacion;
	}	
	
}
