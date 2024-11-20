package com.ix.bd;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.ix.interfaces.IValorPropiedad;

/**
@author
*/

public class ValoresPropiedad {
	
	public static IValorPropiedad asignarDouble= (valor) ->{
		return Double.parseDouble(valor.toString());		
	};
	
	public static IValorPropiedad asignarLocalDate= (valor) ->{
		return LocalDate.ofInstant(((Date) valor).toInstant(), ZoneId.systemDefault());		
	};
	
	public static IValorPropiedad asignarDate= (valor) ->{
		return  (Date)valor;		
	};
	
	public static IValorPropiedad asignarInt= (valor) ->{
		return  new java.math.BigDecimal(valor.toString()).intValue();		
	};
	
	public static IValorPropiedad asignarInteger= (valor) ->{
		return Integer.parseInt(valor.toString());		
	};
	
	public static IValorPropiedad asignarObject= (valor) ->{
		return  valor;		
	};
	
	public static IValorPropiedad seleccionar(Field campo) throws IllegalAccessException {
		Type cType = campo.getGenericType();
	
		return switch(cType.getTypeName()) {
			case "java.lang.Integer" -> asignarInteger;
			case "int" -> asignarInt;
			case "double" -> asignarDouble;
			case "java.lang.Double" -> asignarDouble;
			case "java.util.Date" -> asignarDate;
			case "java.time.LocalDate" -> asignarLocalDate;
			default -> asignarObject;
			
		};
	}		

}
