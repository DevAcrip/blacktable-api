package com.ix.servicios;

import com.ix.interfaces.IAplicacion;
import com.ix.interfaces.IBaseDatos;

public class AplicacionMock implements IAplicacion {
    @Override
    public IAplicacion getAplicacion(IBaseDatos bd, jakarta.servlet.ServletContext context) {
        return null;
    }

    @Override
    public IAplicacion getAplicacion() {
        return null;
    }

    @Override
    public jakarta.servlet.ServletContext getContext() {
        return null;
    }

    @Override
    public String getCadenaConexion() {
        return "";
    }

    @Override
    public void setCadenaConexion(String cadenaConexion) {

    }

    @Override
    public void setContext(jakarta.servlet.ServletContext context) {

    }

    // Implement the methods of IAplicacion as needed for your tests
    @Override
    public IBaseDatos getBaseDatos() {
        return null;
    }

    @Override
    public void setBaseDatos(IBaseDatos baseDatos) {

    }

    @Override
    public Integer getId() {
        return 0;
    }

    @Override
    public void setId(Integer id) {

    }

    // Add other methods if necessary
}