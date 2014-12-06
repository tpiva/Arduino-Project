package com.servApArduino.api;

/**
 * API que prov� os metodos poss�veis para realizar
 * uma conex�o com o arduino, seja esta: Serial, Via internet, Radio etc.
 * @author Thiago
 *
 */
public interface ComunicacaoArduino {
	
	/*
	 * Constantes
	 */
	public static final int TAXA_TRANSMISSAO_PADRAO = 9600;
	
	/*
	 * M�todos
	 */
	
	/**
	 * Realiza a conex�o com o arduino.
	 */
	public void conectar();
	
	/**
	 * Realiza a conex�o com o arduino especificando, a qual porta
	 * ser� feita essa conex�o.
	 * @param porta
	 */
	public void conectar(String porta);
	
	public void desconectar();

}
