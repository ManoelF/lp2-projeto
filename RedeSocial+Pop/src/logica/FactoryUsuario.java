package logica;

import exceptions.CadastroInvalidoException;

public class FactoryUsuario {

	private Usuario usuario;

	public FactoryUsuario() {

	}

	public Usuario criaUsuario(String nome, String email, String senha,
			String nascimento, String imagem)
			throws CadastroInvalidoException{

		usuario = new Usuario(nome, email, senha, nascimento, imagem);
		return usuario;
	}
	
	

}