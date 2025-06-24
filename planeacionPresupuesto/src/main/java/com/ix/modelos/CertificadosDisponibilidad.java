package modelos;

public class CertificadosDisponibilidad extends Maestros {
	
	
	private int autoriza; 
	private int digitador; 
	private int division; 
	private String documentocontrol;

	private int mes; 
	private int saldo; 
	private int solicita; 
	private int tipodocumento; 
	private int tipodocumentosoporte; 
	 
	private int vigencia; 
	
	public String getDocumentocontrol(){ return documentocontrol;} 
	
	public int getAutoriza(){ return autoriza;} 
	public int getDigitador(){ return digitador;} 
	public int getDivision(){ return division;} 
	 
	public int getMes(){ return mes;} 
	public int getSaldo(){ return saldo;} 
	public int getSolicita(){ return solicita;} 
	public int getTipodocumento(){ return tipodocumento;} 
	public int getTipodocumentosoporte(){ return tipodocumentosoporte;} 

	
	public int getVigencia(){ return vigencia;} 
	public void setAutoriza(int autoriza){  this.autoriza=autoriza;} 
	public void setDigitador(int digitador){  this.digitador=digitador;} 
	public void setDivision(int division){  this.division=division;} 
	public void setDocumentocontrol(String documentocontrol){  this.documentocontrol=documentocontrol;} 
	
	public void setMes(int mes){  this.mes=mes;} 	
	
	public void setSaldo(int saldo){  this.saldo=saldo;} 
	public void setSolicita(int solicita){  this.solicita=solicita;} 
	
	public void setTipodocumento(int tipodocumento){  this.tipodocumento=tipodocumento;} 
	public void setTipodocumentosoporte(int tipodocumentosoporte){  this.tipodocumentosoporte=tipodocumentosoporte;} 	
	
	public void setVigencia(int vigencia){  this.vigencia=vigencia;}
	
}
