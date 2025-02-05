package com.ix.entidades;

import com.ix.basicas.Modelo;

public class TiposDocumentos extends Modelo{

	private int tipodocumento; 
	private String descripcion; 
	private String automatico; 
	private int numerador; 
	private String nombretecnico; 
	private int numerodecopias; 

	private int tipocomprobante; 
	private int tipomovimiento; 
	private String conciliable; 
	private String reporte;

	public int getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(int tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAutomatico() {
		return automatico;
	}
	public void setAutomatico(String automatico) {
		this.automatico = automatico;
	}
	public int getNumerador() {
		return numerador;
	}
	public void setNumerador(int numerador) {
		this.numerador = numerador;
	}
	public String getNombretecnico() {
		return nombretecnico;
	}
	public void setNombretecnico(String nombretecnico) {
		this.nombretecnico = nombretecnico;
	}
	public int getNumerodecopias() {
		return numerodecopias;
	}
	public void setNumerodecopias(int numerodecopias) {
		this.numerodecopias = numerodecopias;
	}
	public int getTipocomprobante() {
		return tipocomprobante;
	}
	public void setTipocomprobante(int tipocomprobante) {
		this.tipocomprobante = tipocomprobante;
	}
	public int getTipomovimiento() {
		return tipomovimiento;
	}
	public void setTipomovimiento(int tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}
	
	public String getConciliable() {
		return conciliable;
	}
	public void setConciliable(String conciliable) {
		this.conciliable = conciliable;
	}
	public String getReporte() {
		return reporte;
	}
	public void setReporte(String reporte) {
		this.reporte = reporte;
	} 

}
