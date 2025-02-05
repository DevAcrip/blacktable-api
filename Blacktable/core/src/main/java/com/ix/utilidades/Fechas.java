package com.ix.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
/**
@author
*/
public class Fechas {
	
	public final static String ddMMyyyy="dd/MM/yyyy";
	public final static String ddmmyyyy="dd/mm/yyyy";
	public final static String FECHAHORA="dd/MM/yyyy HH:mm:ss";
	public final static String yyyyMMdd="yyyyMMdd";
	
	public static Map<Integer, String> lstMeses;
	
	public static SortedMap<Integer, String> lstDiasSemana;
	
	static {
		lstMeses=new HashMap<Integer, String>();
		lstMeses.put(1, "Enero");
		lstMeses.put(2, "Febrero");
		lstMeses.put(3, "Marzo");
		lstMeses.put(4, "Abril");
		lstMeses.put(5, "Mayo");		
		lstMeses.put(6, "Junio");
		lstMeses.put(7, "Julio");
		lstMeses.put(8, "Agosto");
		lstMeses.put(9, "Septiembre");
		lstMeses.put(10, "Octubre");
		lstMeses.put(11, "Noviembre");
		lstMeses.put(12, "Diciembre");
		
	}
		
	static {
		lstDiasSemana = new TreeMap<> (Map.of(1,"Lunes", 2, "Martes",3,"Miércoles",4,"Jueves",5,"Viernes",6,"Sábado",7,"Domingo"));
	}
	
	public static String ahora()  {	
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FECHAHORA)); 
	} 	
	
	public static Integer vigenciaActual() {		
		return Year.now().getValue();
	}	
	
	public static java.sql.Date sqlDateDMY(String valor){
		try {
			return new java.sql.Date(new SimpleDateFormat(ddMMyyyy).parse(valor).getTime()) ;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}    
     
    public static java.sql.Date sqlDateDMY(LocalDate valor) {
    	java.sql.Date sqlDate = java.sql.Date.valueOf(valor);
    	return sqlDate;

    }
	
}
