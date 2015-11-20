/* =========================== Rede Social +Pop ================================= #
 *                                                                                *
 * Projeto obrigatorio para cumprimento de nota da disciplina Programação II      * 	  
 * e Laboratorio de Programacao II.                                               *
 *                                                                                *
 * Departamento de Informatica e Engenharia Eletrica                              *
 * Curso Ciência da Computação (UFCG - 2015.1)                                    *
 * Laboratorio de Programação II                                                  *
 *                                                                                *
 * Discentes envolvidos:                                                          *
 *          Italo Batista                                                         *
 *          Jose Manoel Ferreira                                                  *
 *          Kerilin Chang.                                                        *
 *                                                                                *
 * Orientador:                                                                    *
 *          Francisco Neto.                                                       *
 *                                                                                *
 * ============================================================================== #
 */





package logica;

import java.io.Serializable;
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

/**
 * <code>Controller</code> gerencia todas as informacoes da rede, delegando funcionalidades para as outras classes 
 * do projeto.
 * 
 */
public class Controller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	/**
	 * Construtor de Controller.
	 */
	public Controller() {
		this.fabricaUsuario = new FactoryUsuario();
		this.usuariosCadastrados = new ArrayList<Usuario>();
		this.trendingTopics = new TrendingTopics();
		this.ranking = new Ranking();
		this.util = Util.getInstancia();


	}
	
	/**
	 * Cadastra um usuario na Rede Social. 
	 * Recebe os atributor do usuario necessarios para realizar o cadastro. 
	 * 
	 * @param nome
	 * 			Nome do usuario.
	 * 
	 * @param email
	 * 			E-mail do usuario.
	 * 
	 * @param senha
	 * 			Senha do usuario.
	 * 
	 * @param nascimento
	 *			Data de nascimento do usuario, formatao <b>dd/mm/aaaa</b>. 
	 *
	 * @param imagem
	 * 			Imagem do perfil do usuario
	 * S
	 * @return String
	 * 			Email do usuario cadastrado
	 * 
	 * @throws EntradaException
	 * 			Lanca excecao se algum atributo passado na tentativa de cadastro estiver incorreto.
	 * 				
	 * @throws LogicaException
	 * 			Lanca excecao se ja houver um usuario cadastrado com o mesmo email utilizado na tentativa de cadastro.
	 */
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
	
	/**
	 * Remove um usuario da Rede Social e da lista de amigos de seus amigos.
	 * 
	 * @param emailUsuario
	 * 			E-mail do usuario a ser removido da Rede Social.
	 */
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
	
	/**
	 * Verifica se ja existe na Rede um usuario cadastrado com o e-mail buscado.
	 * 
	 * @param email
	 * 		E-mail a ser verificado se eh igual a outrx com o qual outrx usuarix ja tenha se cadastrado.
	 * @return
	 * 		boolean true ou false se ja houver xm usuarix ja cadastradx com o email ou nao.
	 */
	private boolean hasEmail(String email) {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Loga o usuario na rede.
	 * 
	 * @param EmailInserido
	 * 		E-mail do usuario que quer logar.
	 * 
	 * @param senhaInserida
	 * 		Senha do usuario que quer logar.
	 * 
	 * @throws LogicaException
	 * 		Lanca excecao quando ja ha um usuario logado no sistema ou    quando a senha inserida nao esta correta.
	 * 
	 * @throws EntradaException
	 * 		Lanca excecao quando nao existe usuario cadastrado com o e-mail informado.
	 */
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
	/**
	 * Desloga o usuario da rede.
	 * 
	 * @throws LogicaException
	 * 		Lanca excecao quando nao ha nenhum usuario a ser deslogado.
	 */	
	public void logout() throws LogicaException {

		if (this.usuarioLogado == null) {
			throw new LogoutException("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		} else {
			this.usuarioLogado = null;
		}
	}
	
	/**
	 * Permite usuario a criar um post e inicia o tratamento das hashtags do post.
	 * 
	 * @param mensagem
	 * 		Conteudo do post.
	 * 
	 * @param data
	 * 		Data em que o post foi criado.
	 * 
	 * @throws PostException
	 * 		Lanca excecao se as hashtags do post nao tiverem sido inseridas corretamente.
	 * 
	 * @throws LogicaException
	 * 			Excecoes relacionada a validacao das informacoes.
	 */
	public void criaPost(String mensagem, String data) throws PostException, LogicaException {
		this.usuarioLogado.criaPost(mensagem, data);
		
		List<String> hashtags = this.usuarioLogado.getUltimoPost().getHashtags();
		popularizaTrending(hashtags);
	}
	/**
	 * Metodo usado para salvar todos os <code>Post</code> do <code>Usuario</code>.
	 * 
	 * @exception LogoutException
	 * 			Invalidacao ao tentar salvar os post sem haver algum usuario logado.
	 */
	public void salvaPosts() throws LogoutException {

		if (this.usuarioLogado == null) {
			throw new LogoutException("Nao eh possivel salvar historico. Nenhum usuarix esta logadx no +pop.");
		} else {
			this.usuarioLogado.salvaPosts();
		}
	}
	
	/**
	 * A medida que o <b>Usuario</b> cria <b>Post</b> as hashtags
	 * sao adicionadas ao <b>TrendingTopics</b>.
	 * 
	 * @param hashtags
	 * 			Lista da hashtags do <b>Post</b> criado, para ser adicionada ao trending.
	 */
	private void popularizaTrending(List<String> hashtags){
		List<Tag> tags = util.converteParaTag(hashtags);
		this.trendingTopics.adicionaTag(tags);
	}
	
	/**
	 * Permite ao usuario curtir o post de um amigo da rede.
	 * 
	 * @param amigo
	 * 		E-mail de cadastro do amigo cujo post se quer curtir.
	 * 
	 * @param post
	 * 		Numero do ordenacao do post em relacao aos posts do amigo.
	 * 
	 * @throws LogicaException
	 * 		Lanca excecao se nao houver usuario cadastrado com o e-mail informado ou se essse usuario nao eh amigo do usuario.
	 */
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
	
	/**
	 * Permite ao usuario descurtir um post de um amigo.
	 * 
	 * @param amigo
	 * 		E-mail de cadastro do amigo cujo post se quer descurtir.
	 * 
	 * @param post
	 * 		Numero do ordenacao do post em relacao aos posts do amigo.
	 * 
	 * @throws LogicaException
	 * 		Lanca excecao se nao houver usuario cadastrado com o e-mail informado ou se essse usuario nao eh amigo do usuario.
	 */
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
	
	/**
	 * Permite ao usuario atualizar alguma informacao de seu perfil.
	 * 
	 * @param atributo
	 * 		Tipo da informacao a ser atualizada (nome, e-mail, data de nascimento ou imagem).
	 * 
	 * @param novoValor
	 * 		O novo valor da informacao a ser atualizada.
	 * 
	 * @throws LogicaException
	 * 		Lanca excecao se nao houver usuario logado no sistema.		
	 * 
	 * @throws EntradaException
	 * 		Lanca excecao se o novo valor para e-mail ja estiver cadastrado ou se o tipo de informacao a ser atualizada nao existir.
	 */
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

	/**
	 * Permite ao usuario atualizar sua senha.
	 * 	
	 * @param atributo
	 * 		O atributo "senha" a ser atualizado.
	 * 
	 * @param valor
	 * 		O novo valor para a senha.
	 * 
	 * @param velhaSenha
	 * 		O valor atual da senha.
	 * 
	 * @throws LogicaException
	 * 		Lanca excecao se nao houver usuario logado no sistema.
	 * 
	 * @throws AtualizaPerfilException
	 * 		Lanca excecao se nao existir o atributo a ser atualizado.
	 */
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws LogicaException, AtualizaPerfilException {	
		if(this.usuarioLogado == null) {
			throw new AtualizaPerfilException();
		} else if (atributo.equals(SENHA)) {
			this.usuarioLogado.setSenha(valor, velhaSenha);
		}
	}
	
	/**
	 * Atualiza o feed do usuario logado.
	 * 
	 * @return
	 * 		String com o feed do usuario logaod.
	 * 
	 * @throws LogicaException
	 * 			Excecao na busca das informacoes para o retorno.
	 * 			
	 */
	public String atualizaFeed() throws LogicaException {
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
	
	/**
	 * Retorna um post do feed do usuario logado.
	 * 
	 * @param indice
	 * 		O indice do post dentro do feed.
	 * 
	 * @return
	 * 		String com o Post desejado.
	 * 
	 * @throws LogicaException
	 * 			Possiveis excecoes na busca de informacoes para construcao do feed.
	 */
	public String getFeed(int indice) throws LogicaException {
		Post post = this.usuarioLogado.getFeed().get(indice);
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
		
	/**
	 * Ordena o <b>Feed</b> pela data dos posts.
	 */
	public void ordenaFeedPorData(){
		this.usuarioLogado.ordenaFeedPorData();
	}
	
	/**
	 * Atualiza o Trending Topics da rede social.
	 * 
	 * @return String
	 * 		String com o Trending Topics.
	 */
	public String atualizaTrendingTopics() {
		return this.trendingTopics.atualizaTrendingTopic();
	}
	
	/**
	 * Ordena <b>Feed</b> pela popularidade dos posts.
	 */

	public void ordenaFeedPorPopularidade(){
		this.usuarioLogado.ordenaFeedPorPopularidade();
	}
	
	/**
	 * Permite a um usuario adicionar outro usuario como amigo.
	 * 
	 * @param emailUserDestino
	 * 		E-mail do usuario que se deseja como amigo.	
	 * 
	 * @throws LogicaException
	 * 		Lanca excecao caso nao haja nenhum usuario cadastrado com o e-mail. 
	 */
	public void adicionaAmigo(String emailUserDestino) throws LogicaException {
		Usuario userDestino = pesquisaUsuario(emailUserDestino);
		if (userDestino == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ emailUserDestino +" nao esta cadastradx.");
		} else {
			this.usuarioLogado.adicionaAmigo(userDestino);
		}

	}
	
	/**
	 * Permite o <b>Usuario</b> aceitar uma solicitacao de amizade adicionando o 
	 * usuario que solicitou  em sua lista de amigos.
	 * 
	 * @param emailUserAceito
	 * 			Email do usuario que solicitou a amizade.
	 * 
	 * @throws LogicaException
	 * 			Caso nao exista usuario com o email informado nao sera possivel 
	 * 			dar continuidade ao processo e sera lancado uma excecao.
	 */
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
	
	/**
	 * Permite o <b>Usuario</b> rejeitar uma solicitacao de amizade.
	 * 
	 * @param emailUserRecusado
	 * 			Email do usuario que sera rejeitado.
	 * 
	 * @throws LogicaException
	 * 			Caso nao exista usuario com o email informado nao sera possivel 
	 * 			dar continuidade ao processo e sera lancado uma excecao.
	 */
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
	
	/**
	 * Permite ao <b>Usuario</b> remover um outro usuario da sua lista de amigos.
	 * 
	 * @param emailUsuario
	 * 			Email do usuario a ser removido.
	 * 			
	 * @throws UsuarioNaoCadastradoException
	 * 			Excecao caso o usuario queira remover uma amigo que nao esteja cadastrado na Rede.
	 */
	public void removeAmigo(String emailUsuario) throws UsuarioNaoCadastradoException {
		Usuario usuarioRemover = pesquisaUsuario(emailUsuario);
		
		if (usuarioRemover == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ emailUsuario +" nao esta cadastradx.");
		} else {
			this.usuarioLogado.removeAmigo(usuarioRemover);
			usuarioRemover.recebeNotificacao(this.usuarioLogado.getNome() + " removeu a sua amizade.");
		}
		
		usuarioRemover.removeAmigo(this.usuarioLogado);
	}
	
	/**
	 * Lista dos <b>Usuairos</b> cadastrados no sistema.
	 * 
	 * @return List
	 * 			Lista de usuairos cadastrados.
	 */			
	public List<Usuario> getUsuariosCadastrados(){
		return this.usuariosCadastrados;	
	}

	/**
	 * Retorna o <b>Usuario</b> que esta logado.
	 * 
	 * @return Usuario
	 * 			Usuario logado.
	 */
	public Usuario getUsuarioLogado(){
		return this.usuarioLogado;
	}
			
	/**
	 * Busca de uma informacao do usuario que esta cadastrado, seja (nome, data de nascimento, imagem ou senha).
	 * 
	 * @param atributo
	 * 			Informacao referente a consulta.
	 * 
	 * @return	String
	 * 			Informacao especificada.
	 * 
	 * @throws SenhaProtegidaException
	 * 			Caso seja requisitado a senha do Usuario e lancado uma excecao, pois 
	 * 			a senha eh protegida.
	 * 
	 * @throws EntradaException
	 * 			Se for solicitado uma informacao que nao esteja disponivel ou nao existe uma excecao eh gerada.
	 */
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
	/**
	 * Busca de uma informacao do usuario especificado pelo email, seja (nome, data de nascimento, imagem ou senha).
	 * 
	 * @param atributo
	 * 			Informacao referente a consulta.
	 * 
	 * @param email
	 * 			Email do <b>Usuairo</b> que eh requerido a informacao.
	 * 
	 * @return String
	 * 			Informacao requisitada.
	 * 
	 * @throws LogicaException
	 * 			Caso seja informado um email de um usuario que nao esteja cadastrado uma excecao eh gerada.
	 * 			
	 * @throws EntradaException
	 * 			Se for solicitado uma informacao que nao esteja disponivel ou nao existe uma excecao eh gerada.
	 */
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
	
	/**
	 * Informacao referente a quantidade de notificacoes que o usuario tem ate o momento.
	 * 
	 * @return Int
	 * 			Quantidade de notificacoes.
	 */
	public int getNotificacoes() { 
		return this.usuarioLogado.getNotificacoes();
	}

	/**
	 * Quando o <b>Usuario</b> que ler suas notificaoes esse metodo e chamado,
	 * sempre lendo a notificacoa mais recente.
	 * 
	 * @return String 
	 * 		Notificao recebida.
	 *  
	 * @throws NaoHaNotificacoesException
	 * 			Se o usuario solicitar uma proxima notificacao e nao haver mais notificacoes
	 * 			uma excecao eh gerada.
	 */
	public String getNextNotificacao() throws NaoHaNotificacoesException {
		return this.usuarioLogado.getNextNotificacao();
	}
	
	/**
	 * Quantidade de amigos do usuario cadastrado.
	 * 
	 * @return Int
	 * 		Quantidade de amgos;
	 */
	public int getQtdAmigos() {
		return this.usuarioLogado.getQtdAmigos();
	}
	/**
	 * Informacoes referente ao <b>Post</b> solicitado.
	 * 
	 * @param indice
	 * 			Indice do <b>Post</b> que se quer informacoes.
	 * 
	 * @return String
	 * 			Informacoes do post.
	 */
	public String getPost(int indice) {
		return this.usuarioLogado.getPost(indice).getPost();
	}
		
	/**
	 * Para saber qual a popularidade do <b>Usuario</b> esse metodo e chamado.
	 * 
	 * @return String
	 * 		Atual popularidade do Usuario, seja <i>NormalPOP, CelebridadePOP ou IconePOP</i>.
	 */
 	public String getPopularidade() {
 		return this.usuarioLogado.getPopularidade();
 	}
	
 	/**
 	 * Quatidade de POPs do usuario.
 	 * 
 	 * @return Int
 	 * 			Pops do usuario.
 	 */
	public int getPops() {
		return this.usuarioLogado.getPops();
	}
	
	/**
	 * Quando desejado saber a quantidade de pops de um usuario especifico esse metodo eh chamado.
	 * 
	 * @param email
	 * 			Email do usuario que se quer saber seus pops.
	 * 
	 * @return Int
	 * 			Pops do Usuario.
	 * 
	 * @throws LogoutException
	 * 			Caso seja solicitado as innformacoes de um usuario e nao tenha algum
	 * 			usuario loogado.
	 * 
	 * @throws UsuarioNaoCadastradoException 
	 * 			Impossivel ter informacoes de um usuario que nao esta cadastrado no sistema.
	 */
	public int getPopsUsuario(String email) throws LogoutException, UsuarioNaoCadastradoException {
		Usuario usuario = pesquisaUsuario(email);
		if (this.usuarioLogado != null) {
			throw new LogoutException("Erro na consulta de Pops. Um usuarix ainda esta logadx.");
		} else if (usuario == null) {
			throw new UsuarioNaoCadastradoException("Um usuarix com email "+ email +" nao esta cadastradx.");
		} else {
			return pesquisaUsuario(email).getPops();
		}
	}
	
	/**
	 * Metodo responsavel por retornar informacoes de um post especificado.
	 * 
	 * @param atributo
	 * 			Informcao que deseja saber sobre o post.
	 * 
	 * @param post
	 * 			Post o qual requer as informacoes.
	 * 
	 * @return String
	 * 			Informacao do Post.
	 * 
	 * @throws LogicaException
	 * 			Se for requerido uma informacao inacessivel sera lancado uma excecao.
	 */
	public String getPost(String atributo, int post) throws LogicaException { 
		return this.usuarioLogado.getPost(atributo, post);
	}
	
	/**
	 * Busca a midia especificada como parametro.
	 * 
	 * @param indice
	 * 		Indice indica o posicao da midia requerida.
	 * 
	 * @param post
	 * 		Indice do Post.
	 * 
	 * @return String
	 * 		Uma Midia e retornada (Audio, Imagem, Mensagem).
	 * 
	 * @throws LogicaException
	 * 		Na lista de <code>Post</code> na ha o indice informado.
	 * 
	 * @throws PostException
	 * 		O indice informado menor que zero.
	 */
	public String getConteudoPost(int indice, int post) throws LogicaException, PostException {
		return this.usuarioLogado.getConteudoPost(indice, post);
	}	
	
	/**
	 * Informacoes dos pops do Post indicado.
	 * 
	 * @param indice
	 * 			Indice do post que se requer informacoes.
	 * 
	 * @return Int
	 * 			Pops do post.
	 */
	public int getPopsPost(int indice){
		return this.usuarioLogado.getPopsPost(indice);
	}
	
	/**
	 * Informacoes a respeito da quantidade de vezes que o <b>Post</b> foi curtido.
	 * 
	 * @param indice
	 * 			Indice do Post.
	 * 
	 * @return Int
	 * 			Quantidade de curtidas do Post.
	 * 
	 * @exception LogicaException
	 * 			Se requirido o indice de um Post que nao existe.
	 * 
	 * @exception PostException
	 * 			Se requirido um indice negativo.
	 */
	public int qtdCurtidasDePost(int indice) throws PostException, LogicaException {
		return this.usuarioLogado.qtdCurtidasDePost(indice);
	}
	
	/**
	 * Requer a quantidade de Rejeicoes do <code>Post</code>.
	 * 
	 * @param indice
	 * 			Indice do <code>Post</code> que se quer saber o numero de rejeicoes.
	 * 
	 * @return Int
	 * 			Quantidade de rejeicoes do <code>Post</code>.
	 * 
	 * @exception LogicaException
	 * 			Se requirido o indice de um <code>Post</code> que nao existe.
	 * 
	 * @exception PostException
	 * 			Se requirido um indice negativo.
	 */
	public int qtdRejeicoesDePost(int indice) throws PostException, LogicaException {
		return this.usuarioLogado.qtdRejeicoesDePost(indice);
	}
	
	/**
	 * Metodo usado epecificamete para fazer validacoes em outros metodos, 
	 * sendo assim, um metodo privado que apenas a classe <code>Usuairo</code> tem conhecimento.
	 * 
	 * @param EmailInserido
	 * 			Email do usuario a ser pesquisado no sistema.
	 *
	 * @return Usaurio
	 * 			Caso o haja usuario cadastrado na rede com o email informado, sera retornado
	 * 			o proprio usuario, caso contrario o retorno sera null simbolizando a inexistencia
	 * 			de usuario com o email especificado.
	 */
	private Usuario pesquisaUsuario(String EmailInserido) {
		
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(EmailInserido)) {
				return usuario;
			}
		}
		return null;
	}
	
	/**
	 * Informa se ha usuaio cadastrado na redo com email espcificado.
	 * 
	 * @param EmailInserido
	 * 			Email do usuario.
	 * 
	 * @return Boolean
	 * 			Caso o usuario esteja cadastrado e retornado <b>True</b>, caso contrario um <b>False</b> eh retornado.
	 */
	public boolean pesquisaUsuarioNaRede(String EmailInserido) {
		
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(EmailInserido)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * String com informacoes referente ao ranking de usuarios mais e menos popularese na rede.
	 * 
	 * @return String
	 * 			Ranking de Usuarios.
	 * 
	 */
	public String atualizaRanking()  {
		return ranking.atualizaRanking(getUsuariosCadastrados());
	}
	
	/**
	 * Incrementando mais pops ao usuario.
	 * 
	 * @param pops
	 * 			Quantidade de pops a serem incrementadas.
	 */
	public void adicionaPops(int pops) {
		this.usuarioLogado.adicionaPops(pops);
	}
	
	/**
	 * Mudando os pops do usario para um novo valor.
	 * 
	 * @param pop
	 * 			Nova quantidade de pops do usuario.
	 */
	public void setPops(int pop) {
		this.usuarioLogado.setPops(pop);
	}

}