package com.ix.interfaces;

import com.ix.dto.PaisesDto;
import com.ix.utilidades.Excepciones;

import java.sql.SQLException;
import java.util.List;

public interface IPaisesServices extends IServices<PaisesDto>, IGenericServices<PaisesDto> {
    
    List<PaisesDto> consultarPorNombre(String nombre) throws Excepciones;
}