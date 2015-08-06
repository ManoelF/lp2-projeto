package logica;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import exceptions.*;

public class Controller {

	private FactoryUsuario fabricaUsuario;
	private List<Usuario> usuariosCadastrados;
	private Usuario usuarioLogado;

	private static final String NOME = "Nome";
	private static final String EMAIL = "E-mail";
	private static final String NASCIMENTO = "Data de Nascimento";
	private static final String SENHA = "Senha";
	private static final String FOTO = "Foto";	
	
	public Controller() {
		this.fabricaUsuario = new FactoryUsuario();
		usuariosCadastrados = new ArrayList<Usuario>();		
	}
	
	public Usuario cadastraUsuario(String nome, String email, String senha, 
								String nascimento, String imagem) 
								throws EntradaException, ParseException, LogicaException {
		Usuario novoUsuario;
		boolean podeCadastrar = verificaEmailJaCadastrado(email);
		if (podeCadastrar == true) {
			novoUsuario = fabricaUsuario.criaUsuario(nome, email, senha, nascimento, imagem);
			usuariosCadastrados.add(novoUsuario);
			return novoUsuario;
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
		
	public String getInfoUsuario(String atributo, Usuario usuario) throws SenhaProtegidaException {
		String atributoRetornado = null;
		switch (atributo) {
		case NOME:
			atributoRetornado = usuario.getNome();
		case NASCIMENTO:
			atributoRetornado = usuario.getNascimento();
		case FOTO:
			atributoRetornado = usuario.getFoto();
		case SENHA:
			throw new SenhaProtegidaException();
		}
		return atributoRetornado;
	}
	
	public String getInfoUsuario(String atributo) throws SenhaProtegidaException {
		String atributoRetornado = null;
		switch (atributo) {
		case NOME:
			atributoRetornado = this.usuarioLogado.getNome();
		case NASCIMENTO:
			atributoRetornado = this.usuarioLogado.getNascimento();
		case FOTO:
			atributoRetornado = this.usuarioLogado.getFoto();
		case SENHA:
			throw new SenhaProtegidaException();
		}
		return atributoRetornado;
	}
	
	public void atualizaPerfil(String atributo, String novoValor) throws LogicaException, ParseException, AtualizaPerfilException {
		
		if(this.usuarioLogado == null) {
			throw new AtualizaPerfilException();
		} else {	
			switch (atributo) {
			case NOME:
				this.usuarioLogado.alterarNome(novoValor);
			case EMAIL:
				this.usuarioLogado.alterarEmail(novoValor);
			case NASCIMENTO:
				this.usuarioLogado.alterarNascimento(novoValor);
			case FOTO:
				this.usuarioLogado.alterarImagem(novoValor);
				//lancar excecao de atibuto errado
			}
		}
	}

	public void atualizaPerfil(String atributo, String novoValor, String senhaAtual) throws LogicaException, AtualizaPerfilException {
		
		if(this.usuarioLogado == null) {
			throw new AtualizaPerfilException();
		} else if (atributo == SENHA) {
			this.usuarioLogado.alterarSenha(senhaAtual, novoValor);
		}
	}

	public void criaPost(String mensagem, String data) throws PostException, ParseException {
		Post novoPost = new Post(mensagem, data);			
	}
	
	public void atualizaRanking() {
		
	}
	
	public String getInfoUsuario(String atributo, String email) {
		return null;
	}
	
	public String getNextNotificacao() {
		return this.usuarioLogado.getNextNotificacao();
	}
	
	public int getNotificacao() { 
		return this.usuarioLogado.getNotificacao();
	}
	
	public int getQtdAmigos() {
		return this.usuarioLogado.getQtdAmigos();
	}
	
	
	public void curtirPost(String amigo, int post) throws EmailIncorretoException {
		Usuario usuario = pesquisaUsuario(amigo);
		this.usuarioLogado.curtir(usuario, post);
	}
	
	
	public void removeUsuario(Usuario usuario) {
		this.usuariosCadastrados.remove(usuario);
	}
	
	
	public void removeAmigo(String usuario) throws EmailIncorretoException {
		Usuario usuarioRemover = pesquisaUsuario(usuario);
		this.usuarioLogado.removeAmigo(usuarioRemover);
	}
	
	
	
	
}
