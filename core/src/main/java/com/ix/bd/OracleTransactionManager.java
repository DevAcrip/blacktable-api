package com.ix.bd;

import com.ix.interfaces.IConexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public class OracleTransactionManager implements AutoCloseable {

    private final IConexion conexionProveedor;
    private Connection conexion;
    private boolean autoCommitOriginal;

    public OracleTransactionManager(IConexion conexionProveedor) throws SQLException {
        this.conexionProveedor = conexionProveedor;
        this.conexion = conexionProveedor.obtenerConexion();
        this.autoCommitOriginal = conexion.getAutoCommit();
        conexion.setAutoCommit(false);
    }

    public Connection getConexion() {
        return this.conexion;
    }

    public CallableStatement prepararCall(String sql) throws SQLException {
        return conexion.prepareCall(sql);
    }

    public <T> T ejecutarConTransaccion(Function<Connection, T> bloque) throws SQLException {
        try {
            T resultado = bloque.apply(conexion);
            conexion.commit();
            return resultado;
        } catch (Exception e) {
            try {
                conexion.rollback();
            } catch (SQLException rollbackEx) {
                e.addSuppressed(rollbackEx);
            }
            throw new SQLException("Error en transacción Oracle", e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.setAutoCommit(autoCommitOriginal);
            conexion.close();
        }
    }
}
