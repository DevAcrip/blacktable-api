package com.ix.bd;

import jakarta.enterprise.context.Dependent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ix.interfaces.IConexion;

import java.sql.Connection;
import java.sql.SQLException;

@Dependent
public class Conexion implements IConexion {

    private final String jndiDataSource;

    public Conexion(String jndiDataSource) {
        this.jndiDataSource = jndiDataSource;
    }

    @Override
    public Connection obtenerConexion() throws SQLException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(jndiDataSource);
            return ds.getConnection();
        } catch (NamingException e) {
            throw new SQLException("No se pudo obtener el DataSource desde JNDI: " + jndiDataSource, e);
        }
    }
}
