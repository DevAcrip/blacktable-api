package com.ix.basicas;

public class ResultadoDto {
	
	private int resultado;
	private Object instancia;
	
	public ResultadoDto(int resultado, Object instancia) {
		this.resultado=resultado;
		this.instancia=instancia;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public Object getInstancia() {
		return instancia;
	}

	public void setInstancia(Object instancia) {
		this.instancia = instancia;
	}

}
