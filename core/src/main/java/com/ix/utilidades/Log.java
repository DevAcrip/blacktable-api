package com.ix.utilidades;

import static com.ix.auxiliares.ServiciosJson.toJson;
/**
*/
public class Log {
	
	private static String getCallerMethodName() {
	    return StackWalker.getInstance()
	                      .walk(s -> s.skip(2).findFirst())
	                      .get()
	                      .getMethodName();
	}
	
	private static String Json(Object instancia) {
		String s= instancia.toString();		
		s= toJson(instancia);		
		return s;
	}
	
	public static void registrar(String texto) {		
		String m=getCallerMethodName();		 
		System.out.println(m +" : " + texto);			
	}	
	
	public static void registrar(Object instancia) {		
		String m=getCallerMethodName();		 
		System.out.println(m +" : " +Json(instancia));
				
	}	

}
