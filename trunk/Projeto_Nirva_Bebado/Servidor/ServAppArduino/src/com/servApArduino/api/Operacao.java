package com.servApArduino.api;

/**
 * API que provê os métodos possíveis para realizar uma operação com o arduino.
 * Por operação entende-se acender, ligar, desligar e etc.
 * @author Thiago
 *
 */
public interface Operacao {

	/**
	 * Realiza um operação, sendo necessário especificar a porta 
	 * na qual o dispositivo esta conectado.
	 * @param porta
	 */
	public void realizarOperacao(String porta);
	
	/**
	 * Realiza um operação, sendo necessário especificar a porta, 
	 * na qual o dispositivo esta conectado e também o tipo de operação .
	 * 
	 * @param tipoOperacao
	 * @param porta
	 */
	public void realizarOperacao(String tipoOperacao,String porta);
	
	/**
	 * Realiza um operação, sendo necessário especificar a porta, 
	 * na qual o dispositivo esta conectado e também o tipo de operação .
	 * 
	 * @param tipoOperacao
	 * @param porta
	 */
	public void realizarOperacao(int tipoOperacao, String porta);
}
