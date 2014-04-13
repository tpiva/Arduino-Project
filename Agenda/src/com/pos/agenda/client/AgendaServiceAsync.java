package com.pos.agenda.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AgendaServiceAsync {

	void listar(AsyncCallback<ArrayList<Contato>> callback) throws IllegalArgumentException;	
	
	void inserir(Contato contato,AsyncCallback<Void> callback) throws IllegalArgumentException;	
	
	void atualizar(Contato contato,AsyncCallback<Void> callback) throws IllegalArgumentException;	
	
	void remover(Contato contato,AsyncCallback<Void> callback) throws IllegalArgumentException;	
	
	void obter(String nome,AsyncCallback<Contato> callback) throws IllegalArgumentException;	
}
