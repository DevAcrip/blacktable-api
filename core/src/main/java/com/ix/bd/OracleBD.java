// OracleBD.java
package com.ix.bd;

import com.ix.interfaces.IConexion;
import com.ix.interfaces.IOperacionSQL;
import com.ix.utilidades.Excepciones;

import jakarta.servlet.jsp.jstl.sql.Result;
import jakarta.servlet.jsp.jstl.sql.ResultSupport;
import java.sql.*;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

public class OracleBD extends BaseDatos {

    public OracleBD(IConexion cx) {
        super(cx);
    }

    @Override
    public Result consultar(String sql) throws SQLException {
        try (OracleTransactionManager tx = new OracleTransactionManager(conexion)) {
            try (CallableStatement cs = tx.getConexion().prepareCall(sql)) {
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                return ResultSupport.toResult((ResultSet) cs.getObject(1));
            }
        }
    }

    @Override
    public Result consultar(String sql, List<Object> parametros) throws SQLException {
        try (OracleTransactionManager tx = new OracleTransactionManager(conexion)) {
            try (CallableStatement cs = tx.getConexion().prepareCall(sql)) {
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                for (int i = 0; i < parametros.size(); i++) {
                    cs.setObject(i + 2, parametros.get(i));
                }
                cs.execute();
                return ResultSupport.toResult((ResultSet) cs.getObject(1));
            }
        }
    }

    @Override
    public <D> int ejecutar(D d, String sql, IOperacionSQL<D> operacionSQL) throws Excepciones {
        try (OracleTransactionManager tx = new OracleTransactionManager(conexion)) {
            return tx.ejecutarConTransaccion(conn -> {
                try (CallableStatement cs = conn.prepareCall(sql)) {
                    return operacionSQL.ejecutar(d, cs);
                } catch (SQLException e) {
                    throw new RuntimeException("Error en ejecutarSql", e);
                }
            });
        } catch (SQLException e) {
            throw new Excepciones("Error ejecutando SQL", e);
        }
    }

    @Override
    public <D> int ejecutar(D d, Map<String, IOperacionSQL<D>> mapOperacionesSQL) throws Excepciones {
        try (OracleTransactionManager tx = new OracleTransactionManager(conexion)) {
            return tx.ejecutarConTransaccion(conn -> {
                int total = 0;
                try {
                    for (Map.Entry<String, IOperacionSQL<D>> entry : mapOperacionesSQL.entrySet()) {
                        try (CallableStatement cs = conn.prepareCall(entry.getKey())) {
                            total += entry.getValue().ejecutar(d, cs);
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Error en ejecutarSQL", e);
                }
                return total;
            });
        } catch (SQLException e) {
            throw new Excepciones("Error ejecutando múltiples SQL", e);
        }
    }
}
