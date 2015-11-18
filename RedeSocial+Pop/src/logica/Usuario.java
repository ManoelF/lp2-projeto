package logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import exceptions.AtualizaPerfilException;
import exceptions.CadastroInvalidoException;
import exceptions.EntradaException;
import exceptions.LogicaException;
import exceptions.NaoHaNotificacoesException;
import exceptions.PostException;
import logica.tipopopularidade.CelebridadePOP;
import logica.tipopopularidade.IconePOP;
import logica.tipopopularidade.Normal;
import logica.tipopopularidade.TipoPopularidade;

public class Usuario implements Comparable<Usuario>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -178302649978495087L;
	private String nome;
	private String email;
	private LocalDate nascimento;
	private String senha;
	private String imagem;
	private int pops;
	private int popsExtra;
	private List<Usuario> amigos;
	private List<String> solicitacaoAmizade;
	private Deque<String> notificacoes;
	private TipoPopularidade popularidade;
	private FactoryPost fabricaPost;
	private List<Post> posts;
	private Feed feed;
	private Util util;
	
	/**
	 * Construtor de Usario, nele sera inserido todas as informacoes
	 * necessaria para criacao de um novo Usuario.
	 * 
	 * @param nome
	 * 		Nome do Usuario.
	 * 
	 * @param email
	 * 		Email do Usuario.
	 * 
	 * @param senha
	 * 		Senha de acesso do Usuario.
	 * 
	 * @param nascimento
	 * 		Data de nascimento so Usuario.
	 * 
	 * @param imagem
	 * 		Imagem do Usuario.
	 * 
	 * @throws CadastroInvalidoException
	 * 		Se algum dos parametro requerido para criacao do Usuario nao estiver
	 * 		de acordo com as especificacoes eh lancado essa excecao.		
	 */
	public Usuario(String nome, String email, String senha, String nascimento, String imagem) throws CadastroInvalidoException {

 		this.util = Util.getInstancia();
		this.nascimento = util.recebeData(nascimento);
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.imagem = imagem;
		this.pops = 0;
		this.popsExtra = 0;
		this.amigos = new ArrayList<>();
		this.solicitacaoAmizade = new ArrayList<>();
		this.notificacoes = new ArrayDeque<>();
		this.posts = new ArrayList<>();
		this.feed = new Feed(this.amigos);
		this.popularidade = new Normal();
		this.fabricaPost = new FactoryPost();
	
	}
	
	/**
 	 * Verificacao se o Usuario dado como parametro ja esta na lista de amigos.
 	 * 
 	 * @param usuario
 	 * 		Usuario a ser pesquisado na lista de amigos.
 	 * 
 	 * @return	Boolean
 	 * 		Se o Usuario ja estiver na lista retorna true, caso contrario o retorno e falso.
 	 */
	public boolean temAmigo(Usuario usuario) {
		return this.amigos.contains(usuario);	
	}
	
	/**
	 * Enviando uma solicitacao de amizade, juntamente com uma notificacao.
	 * 
	 * @param userDestino
	 * 		Usuario que recebera o convite de amizade.
	 */
	public void adicionaAmigo(Usuario userDestino) {
		userDestino.getSolicitacaoAmizade().add( this.email );
		userDestino.recebeNotificacao(this.nome +" quer sua amizade.");
	}
	
	/**
	 * Rejeita uma solicitacao de amizade recebida, juntamente inserindo a notificacao
	 * da rejeicao.
	 * 
	 * @param userRecusado
	 * 		Usuario que tera o pedido de amizade recusado.
	 */
	public void rejeitaAmizade(Usuario userRecusado) {
		userRecusado.recebeNotificacao(this.nome +" rejeitou sua amizade.");
		this.solicitacaoAmizade.remove(userRecusado);
		this.notificacoes.remove( this.notificacoes.size() - 1 );
	}
	
	/**
	 * Aceita uma solicitacao de amizade, adiciona o Usuario na lista de amigos,
	 * remove da lista de pendentes e tambem a notificacao.
	 * 
	 * @param usuarioAceito
	 * 		Usaurio aceito como amigo.
	 */
	public void aceitaAmizade(Usuario usuarioAceito) {
		this.solicitacaoAmizade.remove(usuarioAceito);
		this.notificacoes.remove( this.notificacoes.size() - 1 );
		this.amigos.add(usuarioAceito);
	}
	
	/**
	 * Remover um Usuario da lista de amigos.
	 * 
	 * @param usuario
	 * 		Usuaro a ser removido.
	 */
	public void removeAmigo(Usuario usuario) {
		this.amigos.remove(usuario);
	}
	/**
	 * Criacao de um Post.
	 * 
	 * @param mensagem
	 * 		Conteudo do poster.
	 * 
	 * @param data
	 * 		Data de criacao do poster.
	 * 
	 * @throws PostException
	 * 		Excecao lancada caso os parametros nao satisfacam as epecificacoes de criacao 
	 * 		de um post
	 */
	public void criaPost(String mensagem, String data) throws PostException, LogicaException {
		Post novoPost = fabricaPost.criaPost(mensagem, data);
		novoPost.setAutor(this.nome);
		this.posts.add(novoPost);
	}
	
	/**
	 * Curtir um Post.
	 * 
	 * @param post
	 * 		Post a ser curtido.
	 */
	public void curtir(Post post) {
		this.popularidade.curtir(post);
	}
	
	/**
	 * Desurtir um Post.
	 * 
	 * @param post
	 * 		Post a ser descurtido.
	 */
	public void descurtir(Post post) {
		this.popularidade.descurtir(post);
	}
	
	/**
	 * Itera sob todos os postes que o Usuario tem, 
	 * faz uma soma da quantidade de pops e atualiza.
	 * Metodo chamado apenas em {@code atualizaPopularidade()}.							
	 */
	public void atualizaPops() {
		
		this.pops = 0;
		int cont = 0;
		for (Post post: posts) {
			cont += post.getPopularidade();
		}
		this.pops = cont ;
		this.pops += this.popsExtra;
		atualizaPopularidade();
	}

	/**
	 * Verifica a quantidade de pops do Usuario e faz a mudanca
	 * de tipo caso o Usuario esteja apto.
	 */
	public void atualizaPopularidade() {
		if( this.pops < 500) {
			this.popularidade = new Normal();
		} else if (this.pops >= 500 && this.pops < 1000) {
			this.popularidade = new CelebridadePOP();
		} else {
			this.popularidade = new IconePOP();
		}
		
	}
	
	/**
	 * Atualizacao do Feed de noticias do Usuario
	 */
	public void atualizaFeed() {
		this.feed.atualizaFeed();
	}
	
	/**
	 * Conta a quantidade de Posts do Feed
	 * 
	 * @return int
	 * 		Quantidade de posts.
	 */
	public int qntPostsFeed() {
		return this.popularidade.qntPostFeed();
	}
	
	/**
	 * Ordenacao da lista de <code>Post</code> no <code>Feed</code> por data de criacao.
	 */
	public void ordenaFeedPorData(){
		this.feed.ordenaPorData();
	}
	
	/**
	 * Ordenacao da lista de <code>Post</code> no <code>Feed</code> por sua popularidade.
	 */
	public void ordenaFeedPorPopularidade(){
		this.feed.ordenaPorPopularidade();
	}
	
	/**
	 * Usuario tem a notificaçao inserida em sua lista de notificaoes.
	 * 
	 * @param notificacao
	 * 		Notificacao do Usuario.
	 */
	public void recebeNotificacao(String notificacao) {
		this.notificacoes.addFirst(notificacao);
	}
	
	/**
	 * Mostrarar as notificacoes recebidas.
	 * 
	 * @return String
	 * 		Notificacao recebida na sequencia.
	 * 
	 * @throws NaoHaNotificacoesException
	 * 		Excecao caso  a notificacao seja {@code null}
	 */
	public String getNextNotificacao() throws NaoHaNotificacoesException {
 		String notificacaoAtual = this.notificacoes.pollLast(); 
 		if (notificacaoAtual == null) {
 			throw new NaoHaNotificacoesException();
 		} else {
 			return notificacaoAtual;
 		}
	}
	
	/**
	 * Responsavel por retornar o nome do <code>Usuario</code>.
	 * 
	 * @return String 
	 * 			Atual nome do usuario.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Responsavel por retornar o email do <code>Usuario</code>.
	 * 
	 * @return String 
	 * 			Atual email do usuario.
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Responsavel por retornar a senha do <code>Usuario</code>.
	 * 
	 * @return String 
	 * 			Atual a senha do usuario.
	 */
	public String getSenha() {
		return this.senha;
	}
	
	/**
	 * Responsavel por retornar a data de nascimento do <code>Usuario</code>.
	 * 
	 * @return String 
	 * 			Atuala data de nascimento do usuario.
	 */
	public String getNascimento() {
		return this.nascimento.toString();
	}
	
	/**
	 * Responsavel por retornar a imagem do <code>Usuario</code>.
	 * 
	 * @return String 
	 * 			Atual a imagem do usuario.
	 */
	public String getImagem() {
		return this.imagem;
	}
	
	/**
	 * Responsavel por retornar os POPs do <code>Usuario</code>.
	 * 
	 * @return String 
	 * 			Atual os POPs do usuario.
	 */
 	public int getPops() {
 		return this.pops;
 	}
 	
 	/**
	 * Responsavel por retornar a popularidade do <code>Usuario</code>.
	 * 
	 * @return String 
	 * 			Atual a popularidade do usuario.
	 */
 	public String getPopularidade() {
 		return this.popularidade.popularidade();
 	}
 	
 	/**
 	 * Retorna um <code>Post</code> de acordo com o indice epecificado.
 	 * 
 	 * @param indice
 	 * 			Indice atual do <code>Post</code>.
 	 * @return
 	 */
	public Post getPost(int indice) {
 		return posts.get(indice);
 	}
	
	/**
	 * Retrorna o ultimo post que o Usuario criou.
	 * 
	 * @return Post
	 * 			Ultimo <code>Post</code> do usuario.
	 */
	public Post getUltimoPost() {
		return this.posts.get(posts.size() - 1);
	}
	
	/**
	 * Busca de um conteudo especifado
	 * 
	 * @param atributo
	 * 		Palavra chave, que apartir dela retorna um 
	 * 		conteudo especifico.(Hashtag, Data, Mensagem e Conteudo)
	 * 
	 * @param post
	 * 		Posicao do Post que onde esta a informacao do atributo
	 * 
	 * @return String
	 * 		Retorna Hashtag, Data, Mensagem ou Conteudo do Post especificado.
	 * 
	 * @exception 
	 * 		Excecao lancada devido a nao existir o atributo especificado 
	 * 		e/ou nao existir o indce na lista de Posts
	 */
	public String getPost(String atributo, int post) throws LogicaException {
		return this.getPost(post).getPost(atributo);
	}
	
	/**
	 * Quantidade de Posts do Usuario
	 * 
	 * @return Int
	 * 		Quantidade de Posts
	 */
	public int getQtdPost() {
		return this.posts.size();
	}
	
	/**
	 * {@code getPostsToFeed} tem por responsablididade retornar a quantidade
	 * de Posts, segundo sua Popularidade, para popular o Feed de seus amigos. 

	 * @return List<Post> 
	 * 			Lista dos postes disponiveis para o Feed de seus amigos.
	 */
	public List<Post> getPosts() {
		return this.posts;
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
	 * 		Na lista de Post na ha o indice informado.
	 * 
	 * @throws PostException
	 * 		O indice informado menor que zero.
	 */
	public String getConteudo(String atributo, int indicePost) throws LogicaException {
		return this.posts.get(indicePost).getPost(atributo);
	}
	
	public String getConteudoPost(int indice, int post) throws LogicaException, PostException {
		if (post > this.posts.size()) {
			throw new LogicaException("Post #" + post + " nao existe na lista posts.");
		} else if (indice < 0) {
			throw new PostException("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		} else {
			return this.posts.get(post).getConteudoPost(indice);
		}
	}
	
	public int getPopsPost(int indice){
		return this.posts.get(indice).getPopularidade();
	}
	
	public int qtdCurtidasDePost(int indice) throws LogicaException, PostException {
		if (indice> this.posts.size()) {
			throw new LogicaException("Post #" + indice + " nao existe. Usuarix possui apenas "+ this.posts.size() +" post(s).");
		} else if (indice < 0) {
			throw new PostException("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		} else {
			return this.posts.get(indice).getLike();
		}
		
	}
	
	public int qtdRejeicoesDePost(int indice) throws LogicaException, PostException {
		if (indice> this.posts.size()) {
			throw new LogicaException("Post #" + indice + " nao existe. Usuarix possui apenas "+ this.posts.size() +" post(s).");
		} else if (indice < 0) {
			throw new PostException("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		} else {
			return this.posts.get(indice).getDeslike();
		}
		
	}
	
	public List<Post> getPostsToFeed() {
		List<Post> postsToFeed = new ArrayList<>();
		Iterator<Post> iterator = this.posts.iterator();
		
		int contaPost = 0; 
		while (iterator.hasNext() && contaPost < qntPostsFeed() && getQtdPost() != contaPost ) {
			postsToFeed.add( this.posts.get( getQtdPost() - contaPost - 1 ) );
			contaPost++;
		}
		return postsToFeed;
	}
	
	public int getNotificacoes() {
		return this.notificacoes.size();
	}
	
	public int getQtdAmigos() {
		return this.amigos.size();
	}
	
	public String getArquivo(int indiceArquivo, int indicePost) {
		return this.posts.get(indicePost).getMidias(indiceArquivo);	
	}
	
	public List<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}
	
	public List<String> getSolicitacaoAmizade(){
		return this.solicitacaoAmizade;
	}
	
	public void adicionaPops(int pops) {
		this.popsExtra += pops;
		atualizaPops();
	}

	public void setPops(int pops) {
		this.pops = pops;
		atualizaPopularidade();
	}

	public void setNome(String novoNome) throws AtualizaPerfilException {
		if (novoNome == null || !util.verificaAtributo(novoNome)){
			throw new AtualizaPerfilException("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
		}
		this.nome = novoNome;
	}
	
	public void setEmail(String novoEmail) throws EntradaException {
		if (novoEmail == null ||!util.verificaEmail(novoEmail)) {
			throw new AtualizaPerfilException("Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
		}
		this.email = novoEmail;
	}
	
	
	public boolean setSenha(String valor, String velhaSenha) throws AtualizaPerfilException {
		if (this.senha.equals(velhaSenha)) {
			
			if (valor == null || !util.verificaSenha(valor)) {
				throw new AtualizaPerfilException("Erro na atualizacao de perfil. Senha invalida.");
			} else {
				this.senha = valor;
				return true;
			}
			
		} else {
			throw new AtualizaPerfilException("Erro na atualizacao de perfil. A senha fornecida esta incorreta.");	
		} 
	}	

	public void setNascimento(String novoNascimento) throws AtualizaPerfilException {
		if (novoNascimento == null || novoNascimento.trim().length() == 0) {
			throw new AtualizaPerfilException("Erro na atualizacao de perfil. Formato de data esta invalida.");
		}
		if (util.verificaFormatoData(novoNascimento) == false) {
			throw new AtualizaPerfilException("Erro na atualizacao de perfil. Formato de data esta invalida.");
		}
		if (util.verificaDataValida(novoNascimento) == false) {
			throw new AtualizaPerfilException("Erro na atualizacao de perfil. Data nao existe.");
		}
		this.nascimento = util.recebeData(novoNascimento);;
	}
	
	public void setImagem(String novaImagem) throws AtualizaPerfilException {
		if (novaImagem == null) {
			throw new AtualizaPerfilException("Erro na atualizacao de prefil. Imagem inserida esta invalida.");
		}
		if (novaImagem.trim().length() == 0) {
			this.imagem = "resources/avatarDefaul.jpg";
		} else {
			this.imagem = novaImagem;
		}
	}
			
	public List<Post> getFeed() {
		return this.feed.getFeed();
	}
	
	@Override
	public int compareTo(Usuario outroUsuario) {
		if (this.pops > outroUsuario.getPops()) {
			return 1;
		} else if (this.pops == outroUsuario.getPops()) {
			return this.getEmail().compareToIgnoreCase(outroUsuario.getEmail());
		} else {
			return -1;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object objeto){
		if(!(objeto instanceof Usuario)){
			return false;
		}
		Usuario usuario = (Usuario) objeto;
		if(usuario.getEmail().equals(this.getEmail())){
			return true;
		}
		return false;
	}
	
	public void salvaPosts() {
		String diretorio = "postsUsuarios/" + getNomeFile() + ".txt";
		File arquivo = null;
		FileWriter fluxoSaida = null;
		
		try {
			arquivo = new File(diretorio);
			
			
			if (arquivo.exists()) {
				fluxoSaida = new FileWriter(arquivo);
				
			} else {
				fluxoSaida = new FileWriter(arquivo);
			}
			
			BufferedWriter stream = new BufferedWriter(fluxoSaida);
			for(int i = 0; i < this.posts.size(); i++) {
				stream.append(this.posts.get(i).toString(i + 1));
			}
			stream.close();
			
		} catch (FileNotFoundException erro) {
			System.out.println(erro.getMessage() + "FlNF");
		} catch (IOException erro) {
			System.out.println(erro.getMessage() + "IOE");
		}
		
		
	}
	
	private String getNomeFile() {
		int indice = this.email.indexOf("@");
		String nome = this.email.substring(0, indice);
		return nome;
	}
}