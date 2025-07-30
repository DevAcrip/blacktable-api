package com.ix.interfaces;

import com.ix.dto.PaisesDto;
import com.ix.utilidades.Excepciones;

import java.util.List;

public interface IPaisesRepository extends ICrudRepository<PaisesDto> {
    
    List<PaisesDto> consultarPorNombre(String nombre) throws Excepciones;
}