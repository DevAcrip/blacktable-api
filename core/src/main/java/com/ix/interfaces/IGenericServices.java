package com.ix.interfaces;

import com.ix.basicas.Dto;
import com.ix.utilidades.Excepciones;

import java.sql.SQLException;
import java.util.List;

public interface IGenericServices<T extends Dto> {
    
    List<T> lista() throws Excepciones;
    
    T obtenerEntidad(int id) throws Excepciones;
    
    T crea(T dto) throws Excepciones, SQLException;
    
    T edita(T dto) throws Excepciones, SQLException;
    
    T elimina(T dto) throws Excepciones, SQLException;
}