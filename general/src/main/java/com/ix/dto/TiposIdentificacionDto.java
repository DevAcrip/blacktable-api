package com.ix.dto;

import com.ix.basicas.Dto;

public class TiposIdentificacionDto extends Dto{

    private String nombre;
    private String estado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
