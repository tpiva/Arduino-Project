package com.servApArduino.operacao;

import java.io.IOException;
import java.io.OutputStream;

import com.servApArduino.api.Operacao;
import com.servApArduino.comunicacaoArduino.ComunicacaoArduinoSerial;
import com.servApArduino.constantesEnum.TipoOperacao;

/**
 * Realiza a opera��o de acender ou apagar uma luz.
 * @author Thiago
 *
 */

/*
 * OBS: Serve tanto para SERIAL quanto REDE. Precisa ser adpatado, mas ambos usam os stream de entrada e sa�da.
 */
public class OperacaoLuz implements Operacao {

	public void realizarOperacao(String tipoOperacao, String porta){
		ComunicacaoArduinoSerial comunicacaoArduinoSerial = ComunicacaoArduinoSerial.getComunicacaoArduinoSerial();
		
		//conecta com o arduino primeiramente
		if(!comunicacaoArduinoSerial.isConectado()){
			comunicacaoArduinoSerial.conectar(porta);			
		} 
		
		if(comunicacaoArduinoSerial.isConectado()){
			try {
				//verifica se existe opera��o a ser realizada
				if(tipoOperacao != null){
					OutputStream outputStream = comunicacaoArduinoSerial.getOutput();
					//converte o valor da opera��o para n�mero
					int valorOperacao = Integer.valueOf(tipoOperacao);
					
					//verifica se a opera��o � acender ou apagar, em seguida realiza a opera��o
					if(TipoOperacao.OPERACAO_ACENDER.getValor() == valorOperacao){
						outputStream.write('a');
					} else if(TipoOperacao.OPERACAO_APAGAR.getValor() == valorOperacao){
						outputStream.write('d');
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}

	@Override
	public void realizarOperacao(String porta) {}

	@Override
	public void realizarOperacao(int tipoOperacao, String porta) {}
	
}
