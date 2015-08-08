package logica;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
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
			throw new CadastroEmailJaExistenteException();
		}
	}
	
	public void login(String EmailInserido, String senhaInserida) throws LogicaException, EntradaException {
		
		Usuario usuarioLogando;
		
		if (usuarioLogado != null) {
			throw new UsuarioLogadoException(usuarioLogado.getNome());
		} else { 
			usuarioLogando = pesquisaUsuario(EmailInserido);
			
			if (usuarioLogando == null) {
				throw new LoginEmailException(EmailInserido);
			} else if (usuarioLogando.getSenha().equals(senhaInserida)){
				usuarioLogado = usuarioLogando;
			} else {
				throw new SenhaIncorretaException();
			}			
		}	
	}

	private Usuario pesquisaUsuario(String EmailInserido) {
		
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(EmailInserido)) {
				return usuario;
			}
		}
		return null;
	}
	
	public void logout() throws UsuarioDeslogadoException {

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

	public void adicionaAmigo(String emailUserDestino) throws LogicaException {
		Usuario usuarioDestino = pesquisaUsuario(emailUserDestino);
		if (usuarioDestino == null) { throw new UsuarioNaoCadastradoException(emailUserDestino); }
		usuarioDestino.getSolicitacaoAmizade().add( this.usuarioLogado.getEmail() );
		usuarioDestino.getNotificoes().add(this.usuarioLogado.getNome() +" quer sua amizade.");
	}
	
	public void rejeitaAmizade(String emailUserRecusado) throws LogicaException  {
		Usuario usuarioRecusado = pesquisaUsuario(emailUserRecusado);
		
		if (usuarioRecusado == null) {
			throw new UsuarioNaoCadastradoException(emailUserRecusado);
		} else if (this.usuarioLogado.getSolicitacaoAmizade().contains(usuarioRecusado.getEmail())) {
			usuarioRecusado.getNotificoes().add(this.usuarioLogado.getNome() +" rejeitou sua amizade.");
			this.usuarioLogado.rejeitaAmizade(emailUserRecusado);			
		} else {
			throw new NaoSolicitouAmizadeException(usuarioRecusado.getNome());
		}
	}
	
	public void aceitaAmizade(String emailUserAceito) throws LogicaException {
		Usuario usuarioAceito = pesquisaUsuario(emailUserAceito);
		
		if (usuarioAceito == null) {
			throw new UsuarioNaoCadastradoException(emailUserAceito);
		} else if (this.usuarioLogado.getSolicitacaoAmizade().contains(usuarioAceito.getEmail())) {
			this.usuarioLogado.aceitaAmizade(usuarioAceito);
			usuarioAceito.getAmigos().add(this.usuarioLogado);			
		} else {
			throw new NaoSolicitouAmizadeException(usuarioAceito.getNome());
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
			break;
		case NASCIMENTO:
			atributoRetornado = usuario.getNascimento();
			break;
		case FOTO:
			atributoRetornado = usuario.getFoto();
			break;
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
			break;
		case NASCIMENTO:
			atributoRetornado = this.usuarioLogado.getNascimento();
			break;
		case FOTO:
			atributoRetornado = this.usuarioLogado.getFoto();
			break;
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
				break;
			case EMAIL:
				this.usuarioLogado.alterarEmail(novoValor);
				break;
			case NASCIMENTO:
				this.usuarioLogado.alterarNascimento(novoValor);
				break;
			case FOTO:
				this.usuarioLogado.alterarImagem(novoValor);
				//break;
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
	
	//falta testar
	public void atualizaRanking() {
		
	}
	
	public String getInfoUsuario(String atributo, String email) throws LogicaException {
		Usuario usuario = pesquisaUsuario(email);
		if (usuario == null) {
			throw new UsuarioNaoCadastradoException(email);
		} else {
			return getInfoUsuario(atributo, usuario); 
		}
	}
	
	public String getNextNotificacao() throws NaoHaNotificacoesException {
		return this.usuarioLogado.getNextNotificacao();
	}
	
	public int getNotificacoes() { 
		return this.usuarioLogado.getNotificacoes().size();
	}
	
	public int getQtdAmigos() {
		return this.usuarioLogado.getQtdAmigos();
	}
	
	public void curtirPost(String amigo, int post) throws UsuarioNaoCadastradoException {
		Usuario usuario = pesquisaUsuario(amigo);
		
		if (usuario == null) {
			throw new UsuarioNaoCadastradoException(amigo);
		} else {
			this.usuarioLogado.curtir(usuario, post);
		}
	}
		
	public void removeUsuario(Usuario usuarioRemovido) {
		
		for (Usuario usuario : usuariosCadastrados) {
			
			Iterator<Usuario> iterator = usuario.getAmigos().iterator();
			while (iterator.hasNext()) {
				Usuario amigo = iterator.next();
				if (amigo.equals(usuarioRemovido)) {
					usuario.removeAmigo(amigo);
					break;
				}
			} // fecha while
		} // fecha for
		
		this.usuariosCadastrados.remove(usuarioRemovido);
		
	} //fecha metodo
	
	public void removeAmigo(String usuario) throws UsuarioNaoCadastradoException {
		Usuario usuarioRemover = pesquisaUsuario(usuario);
		
		if (usuarioRemover == null) {
			throw new UsuarioNaoCadastradoException(usuario);
		} else {
			this.usuarioLogado.removeAmigo(usuarioRemover);
		}
		
		usuarioRemover.removeAmigo(usuarioLogado);
	}
		
}
