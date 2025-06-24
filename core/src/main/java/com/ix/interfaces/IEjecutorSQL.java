package com.ix.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ix.basicas.ResultadoDto;
import com.ix.utilidades.Excepciones;

public interface IEjecutorSQL {
	<D> int ejecutar(D d, String sql, IOperacionSQL<D> operacionSQL) throws Excepciones;
    <D> int ejecutar(D d, Map<String, IOperacionSQL<D>> operaciones) throws Excepciones, SQLException;
    
    <D> List<ResultadoDto> ejecutar(D d,
            Map<String, IOperacionSQL<D>> operaciones,
            ICondicionAbortar condicion) throws Excepciones;
}
