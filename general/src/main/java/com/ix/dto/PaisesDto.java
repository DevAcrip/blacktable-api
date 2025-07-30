package com.ix.dto;

import com.ix.basicas.Dto;
import java.util.Date;

public class PaisesDto extends Dto {

    private String isotexto;
    private String isonumerico;
    private String nombre;
    private int idUsuarioCrea;
    private Date fechaCrea;
    private String ipCrea;
    private Integer idUsuarioEdita;
    private Date fechaEdita;
    private String ipEdita;

    public String getIsotexto() {
        return isotexto;
    }

    public void setIsotexto(String isotexto) {
        this.isotexto = isotexto;
    }

    public String getIsonumerico() {
        return isonumerico;
    }

    public void setIsonumerico(String isonumerico) {
        this.isonumerico = isonumerico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsuarioCrea() {
        return idUsuarioCrea;
    }

    public void setIdUsuarioCrea(int idUsuarioCrea) {
        this.idUsuarioCrea = idUsuarioCrea;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getIpCrea() {
        return ipCrea;
    }

    public void setIpCrea(String ipCrea) {
        this.ipCrea = ipCrea;
    }

    public Integer getIdUsuarioEdita() {
        return idUsuarioEdita;
    }

    public void setIdUsuarioEdita(Integer idUsuarioEdita) {
        this.idUsuarioEdita = idUsuarioEdita;
    }

    public Date getFechaEdita() {
        return fechaEdita;
    }

    public void setFechaEdita(Date fechaEdita) {
        this.fechaEdita = fechaEdita;
    }

    public String getIpEdita() {
        return ipEdita;
    }

    public void setIpEdita(String ipEdita) {
        this.ipEdita = ipEdita;
    }
}