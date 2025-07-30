package com.ix.basicas;

/**
@author
*/

public class Dto {	
	private int id;
	private String nombrePrincipal;
	private int principal;
	private int rol;	
	private String ip;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombrePrincipal() {
		return nombrePrincipal;
	}
	public void setNombrePrincipal(String nombrePrincipal) {
		this.nombrePrincipal = nombrePrincipal;
	}
	public int getPrincipal() {
		return principal;
	}
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
