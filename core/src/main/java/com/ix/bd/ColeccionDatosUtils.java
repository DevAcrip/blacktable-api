package com.ix.bd;


import com.ix.interfaces.IBaseDatos;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.math.BigDecimal;
import java.util.*;

public final class ColeccionDatosUtils {

    private ColeccionDatosUtils() {
      }

    public static <T> List<T> lista(Class<T> clase, String sql, IBaseDatos baseDatos) {
        try {
            Result rs = baseDatos.consultar(sql);
            return mapearLista(clase, rs);
        } catch (Exception e) {
            logError("Error al obtener lista de objetos sin parámetros", e);
            return Collections.emptyList();
        }
    }

    public static <T> List<T> lista(Class<T> clase, String sql, List<Object> parametros, IBaseDatos baseDatos) {
        try {
            Result rs = baseDatos.consultar(sql, parametros);
            return mapearLista(clase, rs);
        } catch (Exception e) {
            logError("Error al obtener lista de objetos con parámetros", e);
            return Collections.emptyList();
        }
    }

    private static <T> List<T> mapearLista(Class<T> clase, Result rs) throws Exception {
        String[] columnas = rs.getColumnNames();
        SortedMap<String, Object>[] filas = rs.getRows(); 
        List<T> lista = new ArrayList<>();

        for (SortedMap<String, Object> fila : filas) {
            T instancia = OrmUtils.mapearInstancia(clase, fila, columnas);
            lista.add(instancia);
        }

        return lista;
    }

    public static Map<String, Integer> mapDatos(String sql, List<Object> parametros, IBaseDatos baseDatos) {
        try {
            Result rs = baseDatos.consultar(sql, parametros);
            Object[][] filas = rs.getRowsByIndex();

            Map<String, Integer> resultado = new LinkedHashMap<>();
            for (Object[] fila : filas) {
                resultado.put(fila[1].toString(), ((BigDecimal) fila[0]).intValue());
            }
            return resultado;

        } catch (Exception e) {
            logError("Error al mapear datos a Map<String, Integer>", e);
            return Collections.emptyMap();
        }
    }

    private static void logError(String mensaje, Exception e) {
        System.err.println(mensaje + ": " + e.getMessage());
        e.printStackTrace();
    }
}
