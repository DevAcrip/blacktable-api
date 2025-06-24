package com.ix.bd;

import com.ix.basicas.ResultadoDto;
import com.ix.interfaces.IBaseDatos;
import com.ix.interfaces.ICondicionAbortar;
import com.ix.interfaces.IConexion;
import com.ix.interfaces.IOperacionSQL;
import com.ix.utilidades.Excepciones;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.jsp.jstl.sql.Result;
import jakarta.servlet.jsp.jstl.sql.ResultSupport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class BaseDatos implements IBaseDatos {

    protected final IConexion conexion;

    @Inject
    public BaseDatos(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Result consultar(String sql) throws SQLException {
        try (Connection conn = conexion.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return ResultSupport.toResult(rs);
        }
    }

    @Override
    public Result consultar(String sql, List<Object> parametros) throws SQLException {
        try (Connection conn = conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                return ResultSupport.toResult(rs);
            }
        }
    }

    @Override
    public <D> int ejecutar(D d, String sql, IOperacionSQL<D> operacion) throws Excepciones {
        try (Connection conn = conexion.obtenerConexion();
             CallableStatement cs = conn.prepareCall(sql)) {

            return operacion.ejecutar(d, cs);

        } catch (SQLException e) {
            throw new Excepciones("Error ejecutando operación SQL", e);
        }
    }

    @Override
    public <D> int ejecutar(D d, Map<String, IOperacionSQL<D>> operaciones) throws Excepciones {
        try (Connection conn = conexion.obtenerConexion()) {
            conn.setAutoCommit(false);
            int total = 0;

            for (Map.Entry<String, IOperacionSQL<D>> entry : operaciones.entrySet()) {
                try (CallableStatement cs = conn.prepareCall(entry.getKey())) {
                    total += entry.getValue().ejecutar(d, cs);
                }
            }

            conn.commit();
            return total;

        } catch (SQLException e) {
            throw new Excepciones("Error ejecutando múltiples operaciones SQL", e);
        }
    }

    @Override
    public <D> List<ResultadoDto> ejecutar(D d,
                                              Map<String, IOperacionSQL<D>> operaciones,
                                              ICondicionAbortar condicion) throws Excepciones {

        List<ResultadoDto> resultados = new ArrayList<>();

        try (Connection conn = conexion.obtenerConexion()) {
            conn.setAutoCommit(false);

            for (Map.Entry<String, IOperacionSQL<D>> entry : operaciones.entrySet()) {
                try (CallableStatement cs = conn.prepareCall(entry.getKey())) {
                    
                    int i= entry.getValue().ejecutar(d, cs);
                    
                    ResultadoDto resultadoParcial=new ResultadoDto(i,d);
                    resultados.add(resultadoParcial);

                    for (ResultadoDto r : resultados) {
                        if (!condicion.validar(r.getResultado())) {
                            conn.rollback();
                            return resultados;
                        }
                    }
                }
            }

            conn.commit();
            return resultados;

        } catch (SQLException e) {
            throw new Excepciones("Error ejecutando bloque DML con condición de aborto", e);
        }
    }

	@Override
	public IConexion getConexion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
