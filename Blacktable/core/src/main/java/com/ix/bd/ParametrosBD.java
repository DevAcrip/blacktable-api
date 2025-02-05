package com.ix.bd;

import java.util.ArrayList;
import static com.ix.auxiliares.ServiciosMapear.mapear;
import static com.ix.auxiliares.ServiciosJson.toJson;

import com.ix.basicas.Dto;

/**
@author
*/

public class ParametrosBD {		

	ArrayList<Object> parametros;
	
	public ParametrosBD() {
		this.parametros= new ArrayList<Object>();
	}
	
	public ParametrosBD parametrosBasicos(Dto dto) {
		parametros.add(dto.getRol());
		parametros.add(dto.getPrincipal());
		parametros.add(dto.getIp());
		return this;
	}
	
	public <T> ParametrosBD agregar(Dto dto,Class<T> clase) {	
		T p=mapear(dto,clase);
		parametros.add(toJson(p));	
		return this;
	}
	
	public ParametrosBD agregar(Object parametro) {	
		parametros.add(parametro);	
		return this;
	}
	
	public ArrayList<Object> getParametros() {
		return parametros;
	}
		
}
