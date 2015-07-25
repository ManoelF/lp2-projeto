package logica;

import exceptions.*;

public class FactoryUsuario {
	
	private Usuario usuario;
	
	public FactoryUsuario(){
		
	}
	
	public Usuario criaUsuario(String nome, String email, String senha, 
			String nascimento, String telefone, String imagem) throws StringException {
			usuario = new Usuario(nome, email, senha, nascimento, telefone, imagem);
			return usuario;
	}

}