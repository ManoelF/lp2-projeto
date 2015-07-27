package logica;

import java.util.ArrayList;
import java.util.List;

import exceptions.*;

public class Controller {

	private FactoryUsuario fabricaUsuario;
	private List<Usuario> usuariosCadastrados;
	private Usuario usuarioLogado;
	
	public Controller() {
		this.fabricaUsuario = new FactoryUsuario();
		usuariosCadastrados = new ArrayList<Usuario>();		
	}
	
	public void cadastraUsuario(String nome, String email, String senha, 
								String nascimento, String telefone, String imagem) 
								throws CadastroInvalidoException {
		Usuario novoUsuario;
		
		novoUsuario = fabricaUsuario.criaUsuario(nome, email, senha, nascimento, telefone, imagem);
		usuariosCadastrados.add(novoUsuario);
	}
	
	public void logarUsuario(String senhaInserida, String EmailInserido ) throws LoginException {
		
		Usuario usuarioLogando;
		
		if (usuarioLogado != null) {
			//throw new LoginException(usuario);
		} else { 
			usuarioLogando = pesquisaUsuario(EmailInserido);
			
			if (usuarioLogando.getSenha().equals(senhaInserida)){
				usuarioLogado = usuarioLogando;
			} else {
				throw new SenhaIncorretaException();
			}
			
		}	
	}

	private Usuario pesquisaUsuario(String EmailInserido) throws EmailIncorretoException {
		
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(EmailInserido)) {
				return usuario;
			}
		}
		throw new EmailIncorretoException();
	}
	
	public void deslogarUsuario(Usuario usuario) throws LoginException {
		this.usuarioLogado = null;
	}
	
}