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
 	
	public boolean temAmigo(Usuario usuario) {
		return this.amigos.contains(usuario);	
	}

	public void adicionaAmigo(Usuario userDestino) {
		userDestino.getSolicitacaoAmizade().add( this.email );
		userDestino.recebeNotificacao(this.nome +" quer sua amizade.");
	}

	public void rejeitaAmizade(Usuario userRecusado) {
		userRecusado.recebeNotificacao(this.nome +" rejeitou sua amizade.");
		this.solicitacaoAmizade.remove(userRecusado);
		this.notificacoes.remove( this.notificacoes.size() - 1 );
	}

	public void aceitaAmizade(Usuario usuarioAceito) {
		this.solicitacaoAmizade.remove(usuarioAceito);
		this.notificacoes.remove( this.notificacoes.size() - 1 );
		this.amigos.add(usuarioAceito);
	}

	public void removeAmigo(Usuario usuario) {
		this.amigos.remove(usuario);
	}

	public void criaPost(String mensagem, String data) throws PostException, LogicaException {
		Post novoPost = fabricaPost.criaPost(mensagem, data);
		novoPost.setAutor(this.nome);
		this.posts.add(novoPost);
	}

	public void curtir(Post post) {
		this.popularidade.curtir(post);
	}
	
	public void descurtir(Post post) {
		this.popularidade.descurtir(post);
	}
		
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
	
	public void atualizaPopularidade() {
		if( this.pops < 500) {
			this.popularidade = new Normal();
		} else if (this.pops >= 500 && this.pops < 1000) {
			this.popularidade = new CelebridadePOP();
		} else {
			this.popularidade = new IconePOP();
		}
		
	}

	public void atualizaFeed() {
		this.feed.atualizaFeed();
	}

	public int qntPostsFeed() {
		return this.popularidade.qntPostFeed();
	}
	
	public void ordenaFeedPorData(){
		this.feed.ordenaPorData();
	}

	public void ordenaFeedPorPopularidade(){
		this.feed.ordenaPorPopularidade();
	}
	
	public void recebeNotificacao(String notificacao) {
		this.notificacoes.addFirst(notificacao);
	}
	
	public String getNextNotificacao() throws NaoHaNotificacoesException {
 		String notificacaoAtual = this.notificacoes.pollLast(); 
 		if (notificacaoAtual == null) {
 			throw new NaoHaNotificacoesException();
 		} else {
 			return notificacaoAtual;
 		}
	}
//FALTA DOCUMENTAR OS GETTS E SETTS
	public String getNome() {
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getSenha() {
		return this.senha;
	}

	public String getNascimento() {
		return this.nascimento.toString();
	}
	
	public String getImagem() {
		return this.imagem;
	}
	
 	public int getPops() {
 		return this.pops;
 	}
 	
 	public String getPopularidade() {
 		return this.popularidade.popularidade();
 	}

	public Post getPost(int indice) {
 		return posts.get(indice);
 	}
	
	public Post getUltimoPost() {
		return this.posts.get(posts.size() - 1);
	}

	public String getPost(String atributo, int post) throws LogicaException {
		return this.getPost(post).getPost(atributo);
	}
	
	public int getQtdPost() {
		return this.posts.size();
	}
		
	public List<Post> getPosts() {
		return this.posts;
	}

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