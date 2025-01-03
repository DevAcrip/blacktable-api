package com.ix.interfaces;

import com.ix.bd.ParametrosConexion;

import java.sql.Connection;

public interface IConexion {

    void conectar();

    void desconectar();

    Connection getConnection();

    void setConnection(Connection connection);

    ParametrosConexion getParametrosConexion();
}