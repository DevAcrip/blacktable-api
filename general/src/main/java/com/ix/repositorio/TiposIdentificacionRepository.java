package com.ix.repositorio;

import java.sql.SQLException;
import java.util.List;

import com.ix.bd.BaseDatos;
import com.ix.dto.TiposIdentificacionDto;
import com.ix.implementacion.BaseRepository;
import com.ix.interfaces.IBaseDatos;
import com.ix.utilidades.Excepciones;

public class TiposIdentificacionRepository extends BaseRepository<TiposIdentificacionDto> {

	private static final String CREAR = "INSERT INTO tipos_identificacion (id, nombre, estado) VALUES (?, ?, ?)";
	private static final String EDITAR = "UPDATE tipos_identificacion SET nombre = ?, estado = ? WHERE id = ?";
	private static final String ELIMINAR = "DELETE FROM tipos_identificacion WHERE id = ?";
	private static final String CONSULTAR = "SELECT * FROM tipos_identificacion";

	public TiposIdentificacionRepository() {
		sqlCrear = CREAR;
		sqlEditar = EDITAR;
		sqlElimimar = ELIMINAR;
		sqlConsultar = CONSULTAR;

		setPropiedadesAParametros((d, parametros) -> {
			parametros.add(d.getId());
			parametros.add(d.getNombre());
			parametros.add(d.getEstado());
		});
	}

	public int crear(TiposIdentificacionDto dto, IBaseDatos baseDatos) throws Excepciones, SQLException {
		setPropiedadesAParametros((d, parametros) -> {
			parametros.add(d.getId());
			parametros.add(d.getNombre());
			parametros.add(d.getEstado());
		});
		return super.crear(dto, baseDatos);
	}

	public int editar(TiposIdentificacionDto dto, IBaseDatos baseDatos) throws Excepciones, SQLException {
		setPropiedadesAParametros((d, parametros) -> {
			parametros.add(d.getNombre());
			parametros.add(d.getEstado());
			parametros.add(d.getId());
		});
		return super.editar(dto, baseDatos);
	}

	public int eliminar(TiposIdentificacionDto dto, IBaseDatos baseDatos) throws Excepciones, SQLException {
		setPropiedadesAParametros((d, parametros) -> {
			parametros.add(d.getId());
		});
		return super.eliminar(dto, baseDatos);
	}

	@Override
	public <M> List<M> lista(Class<M> clazz, List<Object> parametros, IBaseDatos baseDatos)
			throws Excepciones {
		return super.lista(clazz, parametros, baseDatos);
	}

}