package com.ix.interfaces;

public interface IServices<T> {
	    IRepository<T> getRepository();
	    IAplicacion getAplicacion();
	}
