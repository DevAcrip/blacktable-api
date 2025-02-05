package com.ix.basicas;

import com.ix.interfaces.IRepository;
import com.ix.interfaces.IServices;
import com.ix.interfaces.IAplicacion;

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
	
	public Services(Aplicacion aplicacion) {		
		this.aplicacion=aplicacion;
	}	
	
}
