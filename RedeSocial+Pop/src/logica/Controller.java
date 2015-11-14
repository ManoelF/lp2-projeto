package logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import exceptions.AtualizaPerfilException;
import exceptions.CadastroEmailJaExistenteException;
import exceptions.EntradaException;
import exceptions.LogicaException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.NaoHaNotificacoesException;
import exceptions.NaoSolicitouAmizadeException;
import exceptions.PostException;
import exceptions.SenhaIncorretaException;
import exceptions.SenhaProtegidaException;
import exceptions.UsuarioNaoCadastradoException;


public class Controller {

	private FactoryUsuario fabricaUsuario;
	private List<Usuario> usuariosCadastrados;
	private Usuario usuarioLogado;
	private TrendingTopics trendingTopics;
	public Util util;
	private Ranking ranking;
	private static final String NOME = "Nome";
	private static final String EMAIL = "E-mail";
	private static final String NASCIMENTO = "Data de Nascimento";
	private static final String SENHA = "Senha";
	private static final String FOTO = "Foto";	
	
	public Controller() {
		this.fabricaUsuario = new FactoryUsuario();
		this.usuariosCadastrados = new ArrayList<Usuario>();
		this.trendingTopics = new TrendingTopics();
		this.ranking = new Ranking();
		this.util = Util.getInstancia();


	}
	
	public String cadastraUsuario(String nome, String email, String senha, 
								String nascimento, String imagem) 
								throws EntradaException,  LogicaException {
		Usuario novoUsuario;
		boolean podeCadastrar = hasEmail(email);
		if (podeCadastrar == true) {
			novoUsuario = fabricaUsuario.criaUsuario(nome, email, senha, nascimento, imagem);
			usuariosCadastrados.add(novoUsuario);
			return novoUsuario.getEmail();
		} else {
			throw new CadastroEmailJaExistenteException();
		}
	}
	
	public void removeUsuario(String emailUsuario) {
		
		Usuario usuarioRemovido = pesquisaUsuario(emailUsuario);
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
	
	private boolean hasEmail(String email) {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
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

	public void logout() throws LogicaException {

		if (this.usuarioLogado == null) {
			throw new LogoutException("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		} else {
			this.usuarioLogado = null;
		}
	}
	
	public void criaPost(String mensagem, String data) throws PostException {
		this.usuarioLogado.criaPost(mensagem, data);
		
		List<String> hashtags = this.usuarioLogado.getUltimoPost().getHashtags();
		popularizaTrending(hashtags);
	}
	
	private void popularizaTrending(List<String> hashtags){
		List<Tag> tags = util.converteParaTag(hashtags);
		this.trendingTopics.adicionaTag(tags);
	}
	
	public void curtirPost(String amigo, int post) throws LogicaException {
		Usuario usuario = pesquisaUsuario(amigo);
		
		if (usuario == null) {
			throw new UsuarioNaoCadastradoException(amigo);
		} else {
			if (this.usuarioLogado.temAmigo(usuario)) {
				
				boolean epicWin = usuario.getPost(post).getHashtags().contains("#epicwin"); 
				boolean epicFail = usuario.getPost(post).getHashtags().contains("#epicfail");
				
				this.usuarioLogado.curtir(usuario.getPost(post));
				usuario.recebeNotificacao(this.usuarioLogado.getNome() + " curtiu seu post de " + usuario.getPost(post).getDataString() + ".");
				usuario.atualizaPops();
				
				// add #epicwin ou #epicfail no trending, se for o caso
				if (usuario.getPost(post).getHashtags().contains("#epicwin") && epicWin == false) {
					this.trendingTopics.adicionaTag("#epicwin");
				} else if (usuario.getPost(post).getHashtags().contains("#epicfail") && epicFail == false) {
					this.trendingTopics.adicionaTag("#epicfail");
				}
				
			} else { // quando os usuarios nao sao amigos
				throw new LogicaException("Este usuario nao esta na sua lista de amigos.");
			}
		}
	}
	
	public void descurtirPost(String amigo, int post) throws LogicaException {
		Usuario usuario = pesquisaUsuario(amigo);
		
		if (usuario == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ amigo +" nao esta cadastradx.");
		} else {
			if (this.usuarioLogado.temAmigo(usuario)) {
				this.usuarioLogado.descurtir(usuario.getPost(post));
				usuario.recebeNotificacao(this.usuarioLogado.getNome() + " descurtiu seu post de " + usuario.getPost(post).getDataString() + ".");
				usuario.atualizaPops();
			} else {
				throw new LogicaException("Este usuario nao esta na sua lista de amigos.");
			}
		}
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
				if (hasEmail(novoValor)) {
					this.usuarioLogado.setEmail(novoValor);
				} else {
					throw new  EntradaException("Ja existe um usuarix com esse email.");
				}
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

	public String atualizaFeed() {
		this.usuarioLogado.atualizaFeed();
		
		StringBuffer feed = new StringBuffer();
		final String endOfLine = " ";
		
		for (Post post : this.usuarioLogado.getFeed()) {
			feed.append(endOfLine);
			feed.append(post.getAutor() +":");
			feed.append(endOfLine);
			
			if (post.getMidias(0) != null || post.getMidias(0) != "") {
				feed.append(post.getMidias(0));
				feed.append(endOfLine);								  }
			if (post.getMidias().size() > 1) {
				for (int j = 1; j < post.getMidias().size(); j++) {
					feed.append(post.getMidias(j));
					feed.append(endOfLine);	
				}							 }
			if (post.getPost("Hashtags").contains("#")) {
				feed.append(post.getPost("Hashtags").replace(",", " "));
				feed.append(endOfLine);
			}
			
			feed.append(post.getDataString() +"   "+ "Curtir("+ post.getLike() +") Rejeitar("+ post.getDeslike()+ ")");
			feed.append(endOfLine);
		}
		feed.append(endOfLine);
		return feed.toString();
	}
	
	public String getFeed(int i) {
		Post post = this.usuarioLogado.getFeed().get(i);
		StringBuffer postBuffer = new StringBuffer();
		final String endOfLine = " ";
		
		postBuffer.append(post.getAutor() +":");
		postBuffer.append(endOfLine);
		
		if (post.getMidias(0) != null || post.getMidias(0) != "") {
			postBuffer.append(post.getMidias(0));
			postBuffer.append(endOfLine);						  }
		if (post.getMidias().size() > 1) 						  {
			for (int j = 1; j < post.getMidias().size(); j++) {
				postBuffer.append(post.getMidias(j));
				postBuffer.append(endOfLine);	
			}													  }
		if (post.getPost("Hashtags").contains("#")) {
			postBuffer.append(post.getPost("Hashtags").replace(",", " "));
			postBuffer.append(endOfLine);
		}
		
		postBuffer.append(post.getDataString() +"   "+ "Curtir("+ post.getLike() +") Rejeitar("+ post.getDeslike()+ ")");
		return postBuffer.toString();
	}
		
	public void ordenaFeedPorData(){
		this.usuarioLogado.ordenaFeedPorData();
	}
	
	public String atualizaTrendingTopics() {
		return this.trendingTopics.atualizaTrendingTopic();
	}
	
	public void ordenaFeedPorPopularidade(){
		this.usuarioLogado.ordenaFeedPorPopularidade();
	}
	
	public void adicionaAmigo(String emailUserDestino) throws LogicaException {
		Usuario userDestino = pesquisaUsuario(emailUserDestino);
		if (userDestino == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ emailUserDestino +" nao esta cadastradx.");
		} else {
			this.usuarioLogado.adicionaAmigo(userDestino);
		}

	}
	
	public void aceitaAmizade(String emailUserAceito) throws LogicaException {
		Usuario userAceito = pesquisaUsuario(emailUserAceito);
		
		if (userAceito == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ emailUserAceito +" nao esta cadastradx.");
		} else if (this.usuarioLogado.getSolicitacaoAmizade().contains(emailUserAceito)) {
			this.usuarioLogado.aceitaAmizade(userAceito);
			userAceito.getAmigos().add(this.usuarioLogado);	
			userAceito.recebeNotificacao(this.usuarioLogado.getNome() + " aceitou sua amizade.");
		} else {
			throw new NaoSolicitouAmizadeException(userAceito.getNome() + " nao lhe enviou solicitacoes de amizade.");
		}
	}

	public void rejeitaAmizade(String emailUserRecusado) throws LogicaException  {
		Usuario userRecusado = pesquisaUsuario(emailUserRecusado);
		
		if (userRecusado == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email " + emailUserRecusado + " nao esta cadastradx.");
		} else if (this.usuarioLogado.getSolicitacaoAmizade().contains(userRecusado.getEmail())) {
			this.usuarioLogado.rejeitaAmizade(userRecusado);
		} else {
			throw new NaoSolicitouAmizadeException(userRecusado.getNome() + " nao lhe enviou solicitacoes de amizade.");
		}
	}
	
	public void removeAmigo(String usuario) throws UsuarioNaoCadastradoException {
		Usuario usuarioRemover = pesquisaUsuario(usuario);
		
		if (usuarioRemover == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ usuario +" nao esta cadastradx.");
		} else {
			this.usuarioLogado.removeAmigo(usuarioRemover);
			usuarioRemover.recebeNotificacao(this.usuarioLogado.getNome() + " removeu a sua amizade.");
		}
		
		usuarioRemover.removeAmigo(this.usuarioLogado);
	}
	
	public List<Usuario> getUsuariosCadastrados(){
		return this.usuariosCadastrados;	
	}

	public Usuario getUsuarioLogado(){
		return this.usuarioLogado;
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
	
	public int getNotificacoes() { 
		return this.usuarioLogado.getNotificacoes();
	}

	public String getNextNotificacao() throws NaoHaNotificacoesException {
		return this.usuarioLogado.getNextNotificacao();
	}
		
	public int getQtdAmigos() {
		return this.usuarioLogado.getQtdAmigos();
	}
		
	public String getPost(int indice) {
		return this.usuarioLogado.getPost(indice).getPost();
	}
		
 	public String getPopularidade() {
 		return this.usuarioLogado.getPopularidade();
 	}
	
	public int getPops() {
		return this.usuarioLogado.getPops();
	}
	
	public int getPopsUsuario(String email) throws LogoutException {
		if (this.usuarioLogado != null) {
			throw new LogoutException("Erro na consulta de Pops. Um usuarix ainda esta logadx.");
		} else {
			return pesquisaUsuario(email).getPops();
		}
	}
	
	public String getPost(String atributo, int post) { 
		return this.usuarioLogado.getPost(atributo, post);
	}
	
	public String getConteudoPost(int indice, int post) throws LogicaException, PostException {
		return this.usuarioLogado.getConteudoPost(indice, post);
	}	
	
	public int getPopsPost(int indice){
		return this.usuarioLogado.getPopsPost(indice);
	}
	
	public int qtdCurtidasDePost(int indice) throws PostException, LogicaException {
		return this.usuarioLogado.qtdCurtidasDePost(indice);
	}
	
	public int qtdRejeicoesDePost(int indice) {
		return this.usuarioLogado.qtdRejeicoesDePost(indice);
	}

	private Usuario pesquisaUsuario(String EmailInserido) {
		
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(EmailInserido)) {
				return usuario;
			}
		}
		return null;
	}
	
	public String getsenha(){
		return this.usuarioLogado.getSenha();
	}
	
	public String atualizaRanking() throws LogicaException {
		return ranking.atualizaRanking(getUsuariosCadastrados());
	}
	
	public void adicionaPops(int pops) {
		this.usuarioLogado.adicionaPops(pops);
	}
	
	public void setPops(int pop) {
		this.usuarioLogado.setPops(pop);
	}

}