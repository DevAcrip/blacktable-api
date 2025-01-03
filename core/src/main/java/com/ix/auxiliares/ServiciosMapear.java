package com.ix.auxiliares;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

/**
@author
Juan Carlos Oidor González
10 abr. 2024
*/
public class ServiciosMapear {
	
	public static <D> D mapear(Object origen,Class<D> claseDestino) {
		ModelMapper m =new ModelMapper();	
		
		m.getConfiguration().setSkipNullEnabled(true);
		 return m.map(origen,claseDestino);
	}

	public static <D> List<D> mapearLista(Object origen) {
		ModelMapper m =new ModelMapper();
		Type listType = new TypeToken<List<D>>() {}.getType();
		m.getConfiguration().setSkipNullEnabled(true);
		 return m.map(origen,listType);
	}

	
}
