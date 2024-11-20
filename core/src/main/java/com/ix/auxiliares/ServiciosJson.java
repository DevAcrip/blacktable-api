package com.ix.auxiliares;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ix.utilidades.Cadenas;

/**
@author
*/
public class ServiciosJson {	
	
	private static ObjectMapper objectMapper;
	
	static {
		objectMapper = new ObjectMapper();		
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
		objectMapper.registerModule(new JavaTimeModule());		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		objectMapper.setDateFormat(dateFormat);
	}

	@Deprecated
	private static void formatos(ObjectMapper objectMapper) {
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		objectMapper.setDateFormat(dateFormat);
	}

	public static <T> String toJson(T t) {
		String dato =Cadenas.EMPTY;		
		try {
			dato = objectMapper.writeValueAsString(t);	
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}		
		return dato;		
	}

	public static <T> String toJson(List<T> coleccion) throws JsonProcessingException {
		String datos =Cadenas.EMPTY;	
		datos = objectMapper.writeValueAsString(coleccion);		
		return datos;		
	}
	
	public static <T> T fromJson(String data,Class<? extends Object> clase) {
		String dato =Cadenas.EMPTY;
		 T r=null;
		try {			
			 r= (T) objectMapper.readValue(data,clase);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return r;		
	}

	public static Map<String, Object> toMap(String json) {
		Map<String, Object> resultado= new LinkedHashMap<String, Object>();
		
		try {
			resultado= objectMapper.readValue(json, Map.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resultado;
	}
		
	public static <T> List<T> toList(String json, Class<T> tipo) {
        List<T> listaDtos = new ArrayList<>();
        try {
            listaDtos = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, tipo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listaDtos;
    }
	
}
