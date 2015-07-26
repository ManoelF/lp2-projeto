package logica;

import java.util.ArrayList;
import java.util.List;

import exceptions.*;

public class Controller {

	private FactoryUsuario fabricaUsuario;
	private List<Usuario> usuariosCadastrados;
	
	public Controller() {
		this.fabricaUsuario = new FactoryUsuario();
		usuariosCadastrados = new ArrayList<Usuario>();
	}
	
	public void cadastraUsuario(String nome, String email, String senha, 
			String nascimento, String telefone, String imagem) throws CadastroInvalidoException {
		Usuario novoUsuario;
		
		novoUsuario = fabricaUsuario.criaUsuario(nome, email, senha, nascimento, telefone, imagem);
		usuariosCadastrados.add(novoUsuario);
	}
	
	public void logarUsuario(Usuario usuario, String senhaInserida, String EmailInserido ) throws LoginException {
		
		for (Usuario user : usuariosCadastrados) {
			if (user.getEstaLogado() == true) {
				throw new LoginException();
			}
		}	
		
		verificaEmail(usuario, EmailInserido);
		verificaSenha(usuario, senhaInserida);	
		usuario.login();
	}

	public void deslogarUsuario(Usuario usuario) throws LoginException {
		usuario.logout();
	}
	
	public boolean verificaSenha(Usuario usuario, String senhaInserida) throws LoginException {
		if (usuario.getSenha().equals(senhaInserida)){
			return true;
		} else {
			throw new SenhaIncorretaException();
		}
	}
	
	public boolean verificaEmail(Usuario usuario, String EmailInserido) throws LoginException {
		if (usuario.getEmail().equals(EmailInserido)){
			return true;
		} else {
			throw new EmailIncorretoException();
		}
	}
	
}
