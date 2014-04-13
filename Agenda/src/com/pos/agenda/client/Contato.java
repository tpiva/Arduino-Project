package com.pos.agenda.client;

import java.io.Serializable;

public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String email;
	private String categoria;
	private String telefone;
	
	public Contato(){}
	
	public Contato(String nome, String email, String categoria, String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.categoria = categoria;
		this.telefone = telefone;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
