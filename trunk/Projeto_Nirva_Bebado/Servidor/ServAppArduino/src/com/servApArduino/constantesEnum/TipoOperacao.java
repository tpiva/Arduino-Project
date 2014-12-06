package com.servApArduino.constantesEnum;

/**
 * Enum para tipo de operação.
 * @author Thiago
 *
 */
public enum TipoOperacao {
	
	OPERACAO_APAGAR(0),OPERACAO_ACENDER(1);
	private int valor;
	
	private TipoOperacao(int valor){
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}
