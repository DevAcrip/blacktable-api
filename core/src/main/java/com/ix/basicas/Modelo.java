package com.ix.basicas;

/**
@author
*/
public abstract class Modelo implements Cloneable {
	
	protected int id;
	private String estado;
	
	public Modelo clone() throws CloneNotSupportedException {
        return (Modelo) super.clone();
    	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	} }
