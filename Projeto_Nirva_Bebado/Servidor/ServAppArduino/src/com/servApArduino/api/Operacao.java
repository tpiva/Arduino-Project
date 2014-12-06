package com.servApArduino.api;

/**
 * API que prov� os m�todos poss�veis para realizar uma opera��o com o arduino.
 * Por opera��o entende-se acender, ligar, desligar e etc.
 * @author Thiago
 *
 */
public interface Operacao {

	/**
	 * Realiza um opera��o, sendo necess�rio especificar a porta 
	 * na qual o dispositivo esta conectado.
	 * @param porta
	 */
	public void realizarOperacao(String porta);
	
	/**
	 * Realiza um opera��o, sendo necess�rio especificar a porta, 
	 * na qual o dispositivo esta conectado e tamb�m o tipo de opera��o .
	 * 
	 * @param tipoOperacao
	 * @param porta
	 */
	public void realizarOperacao(String tipoOperacao,String porta);
	
	/**
	 * Realiza um opera��o, sendo necess�rio especificar a porta, 
	 * na qual o dispositivo esta conectado e tamb�m o tipo de opera��o .
	 * 
	 * @param tipoOperacao
	 * @param porta
	 */
	public void realizarOperacao(int tipoOperacao, String porta);
}
