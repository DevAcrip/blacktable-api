package com.ix.interfaces;

import com.ix.bd.BaseDatos;
import jakarta.servlet.ServletContext;

public interface IAplicacion {

    IAplicacion getAplicacion(IBaseDatos bd, ServletContext context);

    IAplicacion getAplicacion();

    ServletContext getContext();

    void setContext(ServletContext context);

    String getCadenaConexion();

    void setCadenaConexion(String cadenaConexion);

    IBaseDatos getBaseDatos();

    void setBaseDatos(IBaseDatos baseDatos);

    Integer getId();

    void setId(Integer id);
}
