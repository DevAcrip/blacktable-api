package com.ix.repositorio;


import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ix.bd.BaseDatos;
import com.ix.bd.AuxiliarRepository;
import com.ix.dto.TiposIdentificacionDto;
import com.ix.entidades.TiposIdentificacion;
import com.ix.interfaces.IOperacionSQL;
import com.ix.interfaces.IRepository;
import com.ix.sql.TIPOSIDENTIFICACION;
import com.ix.utilidades.Excepciones;
import static com.ix.auxiliares.ServiciosMapear.*;

public class TiposIdentificacionRepository implements IRepository<TiposIdentificacionDto>{   

	private IOperacionSQL<TiposIdentificacionDto> crear= (TiposIdentificacionDto d,CallableStatement cs) ->{
		int resultado=0;
		List<Object> parametros= new ArrayList<Object>();
		
		AuxiliarRepository<TiposIdentificacion> repositorio = new AuxiliarRepository<TiposIdentificacion>();			
		resultado=repositorio.ejecutar(cs,parametros);
		d.setId(resultado); 
		
		System.out.println("Creando TiposIdentificacion " + d.getId());
		
		return resultado;
	};

	private IOperacionSQL<TiposIdentificacionDto> editar= (TiposIdentificacionDto d,CallableStatement cs) ->{
		int resultado=0;
		List<Object> parametros= new ArrayList<Object>();
			
		AuxiliarRepository<TiposIdentificacion> repositorio = new AuxiliarRepository<TiposIdentificacion>();			
		resultado=repositorio.ejecutar(cs,parametros);
	
		System.out.println("Editando TiposIdentificacion " + d.getId());
		
		return resultado;
	};

	private IOperacionSQL<TiposIdentificacionDto> eliminar= (TiposIdentificacionDto d,CallableStatement cs) ->{
		int resultado=0;
		List<Object> parametros= new ArrayList<Object>();

		parametros.add(d.getPrincipal());		
		parametros.add(d.getId());
			
		AuxiliarRepository<TiposIdentificacion> repositorio = new AuxiliarRepository<TiposIdentificacion>();			
		resultado=repositorio.ejecutar(cs,parametros);
	
		System.out.println("Eliminando TiposIdentificacion " + d.getId());
		
		return resultado;
	};

	@Override
	public int crear(TiposIdentificacionDto dto, BaseDatos baseDatos) throws Excepciones, SQLException {
		int resultado=0;
		Map<String,IOperacionSQL<TiposIdentificacionDto>> lstOperacionesSQL = new LinkedHashMap<>();

		lstOperacionesSQL.put(TIPOSIDENTIFICACION.CREAR,crear);
		
		resultado=baseDatos.ejecutarSQL(dto,lstOperacionesSQL);
		
		return dto.getId();
	}	
	
	@Override
	public int editar(TiposIdentificacionDto dto, BaseDatos baseDatos) throws Excepciones, SQLException {
		int resultado=0;
		Map<String,IOperacionSQL<TiposIdentificacionDto>> lstOperacionesSQL = new LinkedHashMap<>();
		
		lstOperacionesSQL.put(TIPOSIDENTIFICACION.EDITAR,editar);
		
		resultado=baseDatos.ejecutarSQL(dto,lstOperacionesSQL);
		
		return resultado;
	}	

	@Override
	public int eliminar(TiposIdentificacionDto dto, BaseDatos baseDatos) throws Excepciones, SQLException {
		int resultado=0;
		Map<String,IOperacionSQL<TiposIdentificacionDto>> lstOperacionesSQL = new LinkedHashMap<>();
		
		lstOperacionesSQL.put(TIPOSIDENTIFICACION.ELIMINAR,eliminar);
		
		resultado=baseDatos.ejecutarSQL(dto,lstOperacionesSQL);
		
		return resultado;
	}

	@Override
	public List<TiposIdentificacionDto> lista( List<Object> parametrosBD,
			BaseDatos baseDatos) throws Excepciones {
		
			AuxiliarRepository<TiposIdentificacion> repositorio = new AuxiliarRepository<TiposIdentificacion>();
			List<TiposIdentificacion> lst =repositorio.lista(TiposIdentificacion.class,TIPOSIDENTIFICACION.LISTA, parametrosBD, baseDatos);
			return mapearLista(lst);
	}	

}
