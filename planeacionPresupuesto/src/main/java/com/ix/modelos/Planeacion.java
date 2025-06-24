package modelos;

import genericos.Modelo;

public class Planeacion extends Modelo{
	
	private int catalogo; 
	private int division; 
	private int vigencia; 
	private Double valor;
	private String nombre;
	private String codigo;
	private String vigenciaFutura;
	private String observaciones;
	private Double valorProyectado;
	private int formula;
	private int padre;
	private String auxiliar;
	private int nivel;
	private boolean editable;
		
	public Planeacion(int oid, int catalogo, String codigo, String nombre) {
		this();
		this.oid=oid;
		this.catalogo=catalogo;
		this.nombre=nombre;
		this.codigo=codigo;		
	}
	
	public Planeacion() {
		this.setEditado(false);
	}
	public String getVigenciaFutura() {
		return vigenciaFutura;
	}
	public void setVigenciaFutura(String vigenciaFutura) {
		this.vigenciaFutura = vigenciaFutura;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Double getValorProyectado() {
		return valorProyectado;
	}
	public void setValorProyectado(Double valorProyectado) {
		this.valorProyectado = valorProyectado;
	}
	public int getFormula() {
		return formula;
	}
	public void setFormula(int formula) {
		this.formula = formula;
	}
	public int getCatalogo() {
		return catalogo;
	}
	public void setCatalogo(int catalogo) {
		this.catalogo = catalogo;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
	}
	public int getVigencia() {
		return vigencia;
	}
	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public int getPadre() {
		return padre;
	}
	public void setPadre(int padre) {
		this.padre = padre;
	}
	public String getAuxiliar() {
		return auxiliar;
	}
	public void setAuxiliar(String auxiliar) {
		this.auxiliar = auxiliar;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public boolean isEditable() {
		return  this.auxiliar!=null && this.auxiliar.equals("S");
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	/*@Override
	public String toString(){
		return nombre;
	}*/


}
