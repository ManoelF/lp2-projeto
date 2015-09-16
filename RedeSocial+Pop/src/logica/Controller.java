package logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.CadastroEmailJaExistenteException;
import exceptions.EntradaException;
import exceptions.LogicaException;
import exceptions.LoginException;
import exceptions.SenhaIncorretaException;
import exceptions.*;


public class Controller {

	private FactoryUsuario fabricaUsuario;
	private List<Usuario> usuariosCadastrados;
	private Usuario usuarioLogado;
	public Util util;
	private static final String NOME = "Nome";
	private static final String EMAIL = "E-mail";
	private static final String NASCIMENTO = "Data de Nascimento";
	private static final String SENHA = "Senha";
	private static final String FOTO = "Foto";	
	
	public Controller() {
		this.fabricaUsuario = new FactoryUsuario();
		usuariosCadastrados = new ArrayList<Usuario>();		
		
	}
	
	public String cadastraUsuario(String nome, String email, String senha, 
								String nascimento, String imagem) 
								throws EntradaException,  LogicaException {
		Usuario novoUsuario;
		boolean podeCadastrar = verificaEmailJaCadastrado(email);
		if (podeCadastrar == true) {
			novoUsuario = fabricaUsuario.criaUsuario(nome, email, senha, nascimento, imagem);
			usuariosCadastrados.add(novoUsuario);
			return novoUsuario.getEmail();
		} else {
			throw new CadastroEmailJaExistenteException();
		}
	}
	
	public void login(String EmailInserido, String senhaInserida) throws LogicaException, EntradaException {
		
		Usuario usuarioLogando;
		
		if (usuarioLogado != null) {
			throw new LoginException("Nao foi possivel realizar login. Um usuarix ja esta logadx: " + usuarioLogado.getNome() + ".");
		} else { 
			usuarioLogando = pesquisaUsuario(EmailInserido);
			
			if (usuarioLogando == null) {
				throw new LoginException("Nao foi possivel realizar login. Um usuarix com email " + EmailInserido + " nao esta cadastradx.");
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
	
	public void logout() throws LogicaException {

		if (this.usuarioLogado == null) {
			throw new LogoutException("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
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
		if (usuarioDestino == null) { throw new UsuarioNaoCadastradoException("Um usuarix com email "+ emailUserDestino +" nao esta cadastradx."); }
		usuarioDestino.getSolicitacaoAmizade().add( this.usuarioLogado.getEmail() );
		usuarioDestino.recebeNotificacao(this.usuarioLogado.getNome() +" quer sua amizade.");
	}
	
	public void rejeitaAmizade(String emailUserRecusado) throws LogicaException  {
		Usuario usuarioRecusado = pesquisaUsuario(emailUserRecusado);
		
		
		if (usuarioRecusado == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ emailUserRecusado +" nao esta cadastradx.");
		} else if (this.usuarioLogado.getSolicitacaoAmizade().contains(usuarioRecusado.getEmail())) {
			usuarioRecusado.recebeNotificacao(this.usuarioLogado.getNome() +" rejeitou sua amizade.");
			this.usuarioLogado.rejeitaAmizade(emailUserRecusado);			
		} else {
			throw new NaoSolicitouAmizadeException(usuarioRecusado.getNome() + " nao lhe enviou solicitacoes de amizade.");
		}
	}
	
	public void aceitaAmizade(String emailUserAceito) throws LogicaException {
		Usuario usuarioAceito = pesquisaUsuario(emailUserAceito);
		
		if (usuarioAceito == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ emailUserAceito +" nao esta cadastradx.");
		} else if (this.usuarioLogado.getSolicitacaoAmizade().contains(emailUserAceito)) {
			this.usuarioLogado.aceitaAmizade(emailUserAceito);
			usuarioAceito.getAmigos().add(this.usuarioLogado.getEmail());	
			usuarioAceito.recebeNotificacao(this.usuarioLogado.getNome() + " aceitou sua amizade.");
		} else {
			throw new NaoSolicitouAmizadeException(usuarioAceito.getNome() + " nao lhe enviou solicitacoes de amizade.");
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
			
	public String getInfoUsuario(String atributo) throws SenhaProtegidaException , EntradaException{
		String atributoRetornado = null;
		switch (atributo) {
		case NOME:
			atributoRetornado = this.usuarioLogado.getNome();
			break;
		case NASCIMENTO:
			atributoRetornado = this.usuarioLogado.getNascimento();
			break;
		case FOTO:
			atributoRetornado = this.usuarioLogado.getImagem();
			break;
		case SENHA:
			throw new SenhaProtegidaException();
		default:
			throw new EntradaException("Este atributo nao existe!");
		}
		return atributoRetornado;
	}
	
	public void atualizaPerfil(String atributo, String novoValor) throws LogicaException, EntradaException {
		
		if(this.usuarioLogado == null) {
			throw new AtualizaPerfilException();
		} else {	
			switch (atributo) {
			case NOME:
				this.usuarioLogado.setNome(novoValor);
				break;
			case EMAIL:
				this.usuarioLogado.setEmail(novoValor);
				break;
			case NASCIMENTO:
				this.usuarioLogado.setNascimento(novoValor);
				break;
			case FOTO:
				this.usuarioLogado.setImagem(novoValor);
				break;
			default:
				throw new EntradaException("Este atributo nao existe!");
				
			}
		}
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws LogicaException, AtualizaPerfilException {	
		if(this.usuarioLogado == null) {
			throw new AtualizaPerfilException();
		} else if (atributo.equals(SENHA)) {
			this.usuarioLogado.setSenha(valor, velhaSenha);
		}
	}

	public void criaPost(String mensagem, String data) throws PostException {
		this.usuarioLogado.criaPost(mensagem, data);;			
	}
	
	
	public String getInfoUsuario(String atributo, String email) throws LogicaException, EntradaException {
		Usuario usuario = pesquisaUsuario(email);
		if (usuario == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ email +" nao esta cadastradx.");
		} else {
			String atributoRetornado = null;
			switch (atributo) {
			case NOME:
				atributoRetornado = usuario.getNome();
				break;
			case NASCIMENTO:
				atributoRetornado = usuario.getNascimento();
				break;
			case FOTO:
				atributoRetornado = usuario.getImagem();
				break;
			case SENHA:
				throw new SenhaProtegidaException();
			default:
				throw new EntradaException("Este atributo nao existe!");
			}
			return atributoRetornado;
		}
	}
	
	public String getNextNotificacao() throws NaoHaNotificacoesException {
		return this.usuarioLogado.getNextNotificacao();
	}
	
	public int getNotificacoes() { 
		return this.usuarioLogado.getNotificacoes();
	}
	
	public int getQtdAmigos() {
		return this.usuarioLogado.getQtdAmigos();
	}
	
	public void curtirPost(String amigo, int post) throws UsuarioNaoCadastradoException {
		Usuario usuario = pesquisaUsuario(amigo);
		
		if (usuario == null) {
			throw new UsuarioNaoCadastradoException(amigo);
		} else {
			if (this.usuarioLogado.temAmigo(amigo)) {
				this.usuarioLogado.curtir(usuario.getPost(post));
				usuario.recebeNotificacao(this.usuarioLogado.getNome() + " curtiu seu post de " + usuario.getPost(post).getDataString() + ".");
				usuario.atualizaPopularidade();
			} else {
				// Lancar excecao que usuario nao tem esse amigo
				System.out.println("Não é seu amigo!!!");
			}
		}
	}
	
	public void descurtirPost(String amigo, int post) throws LogicaException {
		Usuario usuario = pesquisaUsuario(amigo);
		
		if (usuario == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ amigo +" nao esta cadastradx.");
		} else {
			if (this.usuarioLogado.temAmigo(amigo)) {
				this.usuarioLogado.descurtir(usuario.getPost(post));
				usuario.recebeNotificacao(usuario.getNome() + " descurtiu seu post de " + usuario.getPost(post).getDataString() + ".");
				usuario.atualizaPopularidade();
			} else {
				throw new LogicaException("Este usuario nao esta na sua lista de amigos.");
			}
		}
	}
		
	public void removeUsuario(String emailUsuario) {
		
		Usuario usuarioRemovido = pesquisaUsuario(emailUsuario);
		for (Usuario usuario : usuariosCadastrados) {
			
			Iterator<String> iterator = usuario.getAmigos().iterator();
			while (iterator.hasNext()) {
				String amigo = iterator.next();
				if (amigo.equals(emailUsuario)) {
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
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ usuario +" nao esta cadastradx.");
		} else {
			this.usuarioLogado.removeAmigo(usuario);
			usuarioRemover.recebeNotificacao(this.usuarioLogado.getNome() + " removeu a sua amizade.");
		}
		
		usuarioRemover.removeAmigo(usuarioLogado.getEmail());
	}
	
	public String getPost(int indice) {
		return this.usuarioLogado.getPost(indice).getPost();
		
	}
	
	public String getPost(String atributo, int post) { 
		return this.usuarioLogado.getPost(atributo, post);
	}
	
	public String getConteudoPost(int indice, int post) throws LogicaException, PostException {
		return this.usuarioLogado.getConteudoPost(indice, post);
	}
	
	public String getsenha(){
		return this.usuarioLogado.getSenha();
	}
	
	
	/*public String atualizaRanking() {
		String atualiza = "";
		for (int i = 0; i < ranked.comMaisX2p.length; i++) {
			atualiza += ranked.comMaisX2p[i] + "\n";
		}
		for (int i = 0; i < ranked.comMenosX2p.length; i++) {
			atualiza += ranked.comMenosX2p[i] + "\n";
		}
		
		return atualiza;
	} // fecha ranking
*/}
