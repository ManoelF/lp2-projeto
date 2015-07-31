package logica;

import java.text.ParseException;
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
								throws EntradaException, ParseException, LogicaException {
		Usuario novoUsuario;
		boolean podeCadastrar = verificaEmailJaCadastrado(email);
		if (podeCadastrar == true) {
			novoUsuario = fabricaUsuario.criaUsuario(nome, email, senha, nascimento, telefone, imagem);
			usuariosCadastrados.add(novoUsuario);
		} else {
			throw new EmailJaCadastradoException();
		}
	}
	
	public void login(String EmailInserido, String senhaInserida) throws LogicaException, EntradaException {
		
		Usuario usuarioLogando;
		
		if (usuarioLogado != null) {
			throw new UsuarioLogadoException(usuarioLogado.getNome());
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
		throw new EmailIncorretoException(EmailInserido);
	}
	
	public void logout() throws LoginException {

		if (this.usuarioLogado == null) {
			throw new UsuarioDeslogadoException();
		} else {
			this.usuarioLogado = null;
		}
	}

	public List<Usuario> getUsuariosCadastrados(){
		return this.usuariosCadastrados;	
	}

	public Usuario getUsuarioLogado(){
		return this.usuarioLogado;
	}

	//falta testar os prox codigos
	
	public void adicionaAmigo(String emailUserDestino) throws EmailIncorretoException {
		Usuario usuarioDestino = pesquisaUsuario(emailUserDestino);
		usuarioDestino.getSolicitacaoAmizade().add(emailUserDestino);
		usuarioDestino.getNotificoes().add(this.usuarioLogado.getNome() +" quer sua amizade.");
	}
	
	public void rejeitaAmizade(String emailUserRecusado) throws LogicaException  {
		Usuario usuarioRecusado = pesquisaUsuario(emailUserRecusado);
		if (this.usuarioLogado.getSolicitacaoAmizade().contains(usuarioRecusado.getEmail())) {
			usuarioRecusado.getNotificoes().add(this.usuarioLogado.getNome() +" rejeitou sua amizade.");
			this.usuarioLogado.rejeitaAmizade(emailUserRecusado);			
		} else if (!this.usuariosCadastrados.contains(usuarioRecusado)) {
			throw new EmailNaoCadastradoException(emailUserRecusado);
		} else {
			throw new UsuarioNaoSolicitouAmizadeException(usuarioRecusado.getNome());
		}
	}
	
	public void aceitaAmizade(String emailUserAceito) throws LogicaException {
		Usuario usuarioAceito = pesquisaUsuario(emailUserAceito);
		if (this.usuarioLogado.getSolicitacaoAmizade().contains(usuarioAceito.getEmail())) {
			this.usuarioLogado.aceitaAmizade(usuarioAceito);
			usuarioAceito.getAmigos().add(this.usuarioLogado);			
		} else if (!this.usuariosCadastrados.contains(usuarioAceito)) {
			throw new EmailNaoCadastradoException(emailUserAceito);
		} else {
			throw new UsuarioNaoSolicitouAmizadeException(usuarioAceito.getNome());
		}
	}
	
	public boolean verificaEmailJaCadastrado(String email) {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
			return false;
			}
		}
		return true;
	}
		
}