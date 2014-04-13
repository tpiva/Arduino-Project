package com.pos.agenda.client;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

//@RemoteServiceRelativePath("listar")
public interface AgendaService extends RemoteService{

	ArrayList<Contato> listar() throws IllegalArgumentException;

	void inserir(Contato contato) throws IllegalArgumentException;

	void atualizar(Contato contato) throws IllegalArgumentException;

	void remover(Contato contato) throws IllegalArgumentException;

}
