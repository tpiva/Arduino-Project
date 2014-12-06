package com.servApArduino.operacao;

import java.io.IOException;
import java.io.OutputStream;

import com.servApArduino.api.Operacao;
import com.servApArduino.comunicacaoArduino.ComunicacaoArduinoSerial;
import com.servApArduino.constantesEnum.TipoOperacao;

/**
 * Realiza a operação de acender ou apagar uma luz.
 * @author Thiago
 *
 */

/*
 * OBS: Serve tanto para SERIAL quanto REDE. Precisa ser adpatado, mas ambos usam os stream de entrada e saída.
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
				//verifica se existe operação a ser realizada
				if(tipoOperacao != null){
					OutputStream outputStream = comunicacaoArduinoSerial.getOutput();
					//converte o valor da operação para número
					int valorOperacao = Integer.valueOf(tipoOperacao);
					
					//verifica se a operação é acender ou apagar, em seguida realiza a operação
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
