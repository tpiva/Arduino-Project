package com.pos.agenda.client;

public class ValidaDados {

	public boolean verificarDados(String nome, String email, String telefone, String categoria) {
		boolean dadosOk = true;
		
		if(nome == null || nome.equals("")) {
			dadosOk = false;
		}

		if(email == null || email.equals("")) {
			dadosOk = false;
		}

		if(telefone == null || telefone.equals("")) {
			dadosOk = false;
		}

		if(categoria == null || categoria.equals("")) {
			dadosOk = false;
		}
		
		return dadosOk;
	}
}
