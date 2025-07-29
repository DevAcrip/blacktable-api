package com.ix.interfaces;

import java.util.List;
import java.util.Optional;

import com.ix.utilidades.Excepciones;

public interface ICrudRepository <T> {
    int insertar(T entidad) throws Excepciones;
    int actualizar(T entidad) throws Excepciones;
    int eliminar(Object id) throws Excepciones;
    Optional<T> obtenerPorId(Class<T> tipo, Object id) throws Excepciones;
    List<T> obtenerTodos(Class<T> tipo);
}
