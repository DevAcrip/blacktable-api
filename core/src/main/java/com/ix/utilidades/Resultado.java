package com.ix.utilidades;

import java.util.function.BooleanSupplier;

public class Resultado {
	
	public static void validar(final BooleanSupplier condicion, final String textoError) throws Excepciones {
		
	    if (!condicion.getAsBoolean()) {
	        throw new Excepciones(textoError);
	    }
	   // validar(() -> i >= 0, "El valor de i debe ser mayor o igual a 0");
	}

}
