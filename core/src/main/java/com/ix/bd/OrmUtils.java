package com.ix.bd;

import com.ix.interfaces.IValorPropiedad;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Stream;

public class OrmUtils {

    private OrmUtils() {} 

    public static <T> T mapearInstancia(Class<T> clase, SortedMap<String, Object> fila, String[] columnas)
            throws Exception {

        T instancia = clase.getDeclaredConstructor().newInstance();

        Map<String, Field> propiedades = obtenerPropiedades(clase, columnas);
        Map<String, IValorPropiedad> mapeadores = obtenerMapeadores(propiedades);

        for (Map.Entry<String, Field> entry : propiedades.entrySet()) {
            String columna = entry.getKey();
            Field propiedad = entry.getValue();
            Object valor = fila.get(columna);

            if (valor != null) {
                propiedad.setAccessible(true);
                IValorPropiedad mapeador = mapeadores.get(propiedad.getName().toUpperCase());
                propiedad.set(instancia, mapeador.asignar(valor));
            }
        }

        return instancia;
    }

    private static Map<String, Field> obtenerPropiedades(Class<?> clase, String[] columnas) {
        Map<String, Field> map = new HashMap<>();
        List<String> columnasUpper = Arrays.stream(columnas).map(String::toUpperCase).toList();

        Stream.of(clase, clase.getSuperclass(), clase.getSuperclass().getSuperclass())
                .filter(Objects::nonNull)
                .flatMap(c -> Arrays.stream(c.getDeclaredFields()))
                .filter(f -> columnasUpper.contains(f.getName().toUpperCase()))
                .forEach(f -> map.put(f.getName().toUpperCase(), f));

        return map;
    }

    private static Map<String, IValorPropiedad> obtenerMapeadores(Map<String, Field> campos) throws IllegalAccessException {
        Map<String, IValorPropiedad> map = new HashMap<>();
        for (Field f : campos.values()) {
            map.put(f.getName().toUpperCase(), ValoresPropiedad.seleccionar(f));
        }
        return map;
    }
}
