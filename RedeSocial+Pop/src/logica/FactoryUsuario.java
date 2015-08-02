package logica;

import java.text.ParseException;

import exceptions.*;

public class FactoryUsuario {

	private Usuario usuario;

	public FactoryUsuario() {

	}

	public Usuario criaUsuario(String nome, String email, String senha,
			String nascimento, String imagem)
			throws CadastroInvalidoException, ParseException {

		usuario = new Usuario(nome, email, senha, nascimento, imagem);
		return usuario;
	}

}