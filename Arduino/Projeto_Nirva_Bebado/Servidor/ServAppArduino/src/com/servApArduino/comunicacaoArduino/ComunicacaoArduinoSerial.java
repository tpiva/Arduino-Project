package com.servApArduino.comunicacaoArduino;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.servApArduino.api.ComunicacaoArduino;

/**
 * Realiza a comunica��o com o arduino atrav�s de porta serial.
 * @author Thiago_1
 *
 */
public class ComunicacaoArduinoSerial implements ComunicacaoArduino {

	/*
	 * Vari�veis de inst�ncia.
	 */
	private InputStream input;
	private OutputStream output;
	private boolean conectado;
	private static ComunicacaoArduinoSerial comunicacaoArduinoSerial;
	
	private ComunicacaoArduinoSerial(){}
	
	@Override
	public void conectar() {}

	@Override
	public void conectar(String porta) {
		try {
			//obt�m a identifica��o da porta (COM3,COM4,COM5 ...)
			CommPortIdentifier numPorta = CommPortIdentifier.getPortIdentifier(porta);
			//de fato estabelece a conex�o com o arduino atrav�s da porta acima.
			SerialPort serialPorta = (SerialPort) numPorta.open(this.getClass().getName(), 2000);  
			serialPorta.setSerialPortParams(ComunicacaoArduino.TAXA_TRANSMISSAO_PADRAO, SerialPort.DATABITS_8,  
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			
			//obt�m os stream de entrada e sa�da para enviar e receber dados.
			this.setInput(serialPorta.getInputStream());
			this.setOutput(serialPorta.getOutputStream());
			
			//define conectado = "true", para que a conex�o seja realizada apena uma vez, evitando desta forma erro.
			this.setConectado(true);
		} catch (NoSuchPortException e) {
			e.printStackTrace();
			this.setConectado(false);
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
			this.setConectado(false);
		} catch (PortInUseException e) {
			e.printStackTrace();
			this.setConectado(false);
		} catch (IOException e) {
			e.printStackTrace();
			this.setConectado(false);
		}  
	}
	
	/**
	 * Cria uma inst�ncia da classe, neste caso ser� um Singleton, para evitar de realizar 
	 * duas conex�es com a mesma porta, evitando desta forma eventuais erros e aquisi��es indevidas da
	 * porta na qual o arduino esta conectado.
	 * @return ComunicacaoArduinoSerial
	 */
	public static ComunicacaoArduinoSerial getComunicacaoArduinoSerial() {
		if(comunicacaoArduinoSerial == null){
			comunicacaoArduinoSerial =  new ComunicacaoArduinoSerial();
		}
		
		return comunicacaoArduinoSerial;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public OutputStream getOutput() {
		return output;
	}

	public void setOutput(OutputStream output) {
		this.output = output;
	}
	
	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	@Override
	public void desconectar() {
	}

}
