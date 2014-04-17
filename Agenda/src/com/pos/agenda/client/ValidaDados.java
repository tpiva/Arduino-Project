package com.pos.agenda.client;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class ValidaDados {
	
	private static final String PADRAO_EMAIL = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PADRAO_NUMEROS = "^[0-9]";
	private static final String PADRAO_LETRAS = "[a-zA-Z\\s]+";
	
	private RegExp padrao;
	private MatchResult validador;

	public boolean verificarDados(String nome, String email, String telefone, String categoria) {
		List<Boolean> dadosOk = new LinkedList<Boolean>();
		
		if(nome == null || nome.equals("")) {
			dadosOk.add(false);
		} else {
			dadosOk.add(validarPadraoLetras(nome));
		}

		if(email == null || email.equals("")) {
			dadosOk.add(false);
		} else {
			dadosOk.add(validarPadraoEmail(email));
		}

		if(telefone == null || telefone.equals("")) {
			dadosOk.add(false);
		} else {
			dadosOk.add(validarPadraoNumeros(telefone));
		}

		if(categoria == null || categoria.equals("")) {
			dadosOk.add(false);
		}
		
		return dadosOk.contains(false) ? false : true;
	}
	
	private boolean validarPadraoEmail(String email) {
		padrao = RegExp.compile(PADRAO_EMAIL);
		validador = padrao.exec(email);
		return (validador == null ? false : true);
	}
	
	private boolean validarPadraoNumeros(String stringNumerica) {
		padrao = RegExp.compile(PADRAO_NUMEROS);
		validador = padrao.exec(stringNumerica);
		return (validador == null ? false : true);
	}
	
	private boolean validarPadraoLetras(String stringLetras) {
		padrao = RegExp.compile(PADRAO_LETRAS);
		validador = padrao.exec(stringLetras);
		return (validador == null ? false : true);
	}
}
