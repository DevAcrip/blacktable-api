package com.ix.servicios;

import java.sql.SQLException;
import java.util.List;

import com.ix.dto.TiposIdentificacionDto;
import com.ix.implementacion.Aplicacion;
import com.ix.implementacion.Services;
import com.ix.interfaces.IRepository;
import com.ix.interfaces.IAplicacion;
import com.ix.utilidades.Constantes;
import com.ix.utilidades.Excepciones;
import com.ix.utilidades.Resultado;

public class TiposIdentificacionServices extends Services<TiposIdentificacionDto>{

	public TiposIdentificacionServices(IAplicacion aplicacion, IRepository<TiposIdentificacionDto> repository) {
		super(aplicacion, repository);
	}

	@SuppressWarnings("unchecked")
	public List<TiposIdentificacionDto> lista() throws Excepciones {
		List<TiposIdentificacionDto> lst = repository.lista(TiposIdentificacionDto.class,
				List.of("S"),aplicacion.getBaseDatos());
		
		return lst; 			
	}

	@SuppressWarnings("unchecked")
	public TiposIdentificacionDto obtenerEntidad(int id) throws Excepciones{

		List<TiposIdentificacionDto> lst = repository.lista(TiposIdentificacionDto.class,
				List.of(id),aplicacion.getBaseDatos());
		TiposIdentificacionDto resultado=lst.get(0);		 
		return resultado;
	}	

	@SuppressWarnings("unchecked")
	public TiposIdentificacionDto crea(TiposIdentificacionDto dto) throws Excepciones, SQLException {		

		int resultado = repository.crear(dto,aplicacion.getBaseDatos());

		Resultado.validar(() -> resultado >= 0,String.format(Constantes.ERRORSERVICIO,resultado));

		return obtenerEntidad(dto.getId());
	}	
	
	@SuppressWarnings("unchecked")
	public TiposIdentificacionDto edita(TiposIdentificacionDto dto) throws Excepciones, SQLException {		
	
		int resultado =  repository.editar(dto,aplicacion.getBaseDatos());	

		Resultado.validar(() -> resultado >= 0,String.format(Constantes.ERRORSERVICIO,resultado));

		return obtenerEntidad(dto.getId());
	}	

	@SuppressWarnings("unchecked")
	public TiposIdentificacionDto elimina(TiposIdentificacionDto dto) throws Excepciones, SQLException {
		int resultado = repository.eliminar(dto,aplicacion.getBaseDatos());	

		Resultado.validar(() -> resultado >= 0,String.format(Constantes.ERRORSERVICIO,resultado));
		return new TiposIdentificacionDto()               ;
	}	
	
}

