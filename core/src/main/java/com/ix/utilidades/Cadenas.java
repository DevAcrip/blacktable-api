package com.ix.utilidades;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author
 */
public class Cadenas {

    public static final String EMPTY = "";

    public static String unir(String separador, String... cadenas) {
        return Stream.of(cadenas).filter(value -> value != null).collect(Collectors.joining(separador));
    }

    public static boolean esValida(String valor) {
        return valor != null && !valor.isBlank();
    }
}
