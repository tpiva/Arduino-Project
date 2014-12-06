package com.servApArduino.api;

/**
 * API que provê os metodos possíveis para realizar
 * uma conexão com o arduino, seja esta: Serial, Via internet, Radio etc.
 * @author Thiago
 *
 */
public interface ComunicacaoArduino {
	
	/*
	 * Constantes
	 */
	public static final int TAXA_TRANSMISSAO_PADRAO = 9600;
	
	/*
	 * Métodos
	 */
	
	/**
	 * Realiza a conexão com o arduino.
	 */
	public void conectar();
	
	/**
	 * Realiza a conexão com o arduino especificando, a qual porta
	 * será feita essa conexão.
	 * @param porta
	 */
	public void conectar(String porta);
	
	public void desconectar();

}
