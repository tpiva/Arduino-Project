package com.servApArduino.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import com.servApArduino.operacao.OperacaoLuz;

/**
 * 
 * Bean que contém as informações dos botões, os quais são utilizados para acender
 * ou apagar uma luz.
 * @author Thiago
 *
 */
@ManagedBean(name="luzBean")
@RequestScoped
public class LuzBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static OperacaoLuz operacaoLuz = new OperacaoLuz();
	
	/**
	 * opcao:
	 * 		1 - Acender
	 * 		0 - Apagar
	 */
	private String opcao;

	/**
	 * Retorna opcao
	 * @return opcao
	 */
	public String getOpcao() {
		return opcao;
	}

	/**
	 * Define o valor de opcao, usado "automaticamente" pelo MVC JSF.
	 * @param opcao
	 */
	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
	
	/**
	 * Chama a classe que realiza a operação de acender a luz.
	 * @param event
	 */
	public void realizarAcaoAcenderApagar(ActionEvent event){
		opcao = (String)event.getComponent().getAttributes().get("opcao");
		operacaoLuz.realizarOperacao(this.getOpcao(), "COM3");
	}
	
}
