package com.pos.agenda.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.pos.agenda.client.AgendaService;
import com.pos.agenda.client.Contato;

@SuppressWarnings("serial")
public class ContatoBusiness extends RemoteServiceServlet implements AgendaService{

	private static Map<String, Contato> contatos = new HashMap<String, Contato>();

	public void inserir(Contato contato) {
		contatos.put(contato.getNome(), contato);
	}

	public void atualizar(Contato contato) {
		contatos.put(contato.getNome(), contato);
	}

	public void remover(Contato contato) {
		contatos.remove(contato.getNome());
	}

	public ArrayList<Contato> listar() {
		return new ArrayList<Contato>(contatos.values());
	}

	public Contato obter(String nome) {
		return contatos.get(nome);
	}
}
