package modelos;

import genericos.Modelo;

public class Catalogo extends Modelo {
	
	private String codigo; 
	private String nombre; 
	private Integer padre;
	private int nivel;
	private String auxiliar;
	private String mascara;
	
	public String getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(String auxiliar) {
		this.auxiliar = auxiliar;
	}

	public Catalogo() {
		//TODO Auto-generated constructor stub
	}
	
	public Catalogo(int oid, Integer padre, String codigo, String nombre) {
		this.oid=oid;
		this.padre=padre;
		this.codigo=codigo;
		this.nombre=nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPadre() {
		return padre;
	}
	public void setPadre(Integer padre) {
		this.padre = padre;
	}
		
	@Override
	public String toString(){
		return this.codigo+ " " +this.nombre;
	//return this.codigo!=null?this.codigo:""+ " " +this.nombre!=null?this.nombre:"";
	}
	
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	/*public String getCodigoNombre() {
		return this.codigo+ "--" +this.nombre;
	}

	public void setCodigoNombre(String codigoNombre) {
		this.codigoNombre = codigoNombre;
	}*/

}
