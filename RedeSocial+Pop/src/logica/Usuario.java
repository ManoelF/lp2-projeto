package logica;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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

public class Usuario implements Comparable<Usuario> {
	
	private String nome;
	private String email;
	private LocalDate nascimento;
	private String senha;
	private String imagem;
	private int pop;
	private List<Usuario> amigos;
	private List<String> solicitacaoAmizade;
	private Deque<String> notificacoes;
	private TipoPopularidade popularidade;
	private List<Post> posts;
	private Feed feed;
	private Util util;
	
	// Foi adicionado o throws ParseException, deve ser tratado
 	public Usuario(String nome, String email, String senha, String nascimento, String imagem) throws CadastroInvalidoException {
 		this.util = Util.getInstancia();

		if (nome == null || !util.verificaAtributo(nome)){
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
		}

		if (email == null || !util.verificaEmail(email)) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
		}
		if (senha == null || !util.verificaSenha(senha)) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Senha dx usuarix nao pode ser vazio.");
		}
		if (nascimento == null || nascimento.trim().length() == 0) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de data esta invalida.");
		}
		if (util.verificaFormatoData(nascimento) == false) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de data esta invalida.");
		}
		if (util.verificaDataValida(nascimento) == false) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Data nao existe.");
		}
		
		if (imagem == null || imagem.trim().length() == 0) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Imagem invalida.");
		} else {
			this.imagem = imagem;
		}
		
		verificaEmail(email);
		this.nascimento = util.recebeData(nascimento);
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.pop = 0;
		this.amigos = new ArrayList<>();
		this.solicitacaoAmizade = new ArrayList<>();
		this.notificacoes = new ArrayDeque<>();
		this.posts = new ArrayList<>();
		this.feed = new Feed(this.amigos);
		this.popularidade = new Normal();
	}
 	
	private void verificaEmail(String email) throws CadastroInvalidoException {
		if (email == null || email.equals("")) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
		} else if (email.contains("@") && email.contains(".com")) {
			this.email = email;
		} else {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
		}
	}
 	
	public boolean temAmigo(Usuario usuario) {
		return this.amigos.contains(usuario);	
	}
	
	public void adicionaAmigo(Usuario userDestino) throws LogicaException {
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
	
	public void criaPost(String mensagem, String data) throws PostException {
		Post novoPost = new Post(mensagem, data);
		this.posts.add(novoPost);
	}
	
	public void curtir(Post post) {
		this.popularidade.curtir(post);
	}
	
	public void descurtir(Post post) {
		this.popularidade.descurtir(post);
	}
								
	private void atualizaPops() {
		int pops = 0;
		for (Post post: posts) {
			pops += post.getPopularidade();
		}
		
		this.pop = pops;
	}
		
	public void atualizaPopularidade() {
		atualizaPops();
		if( this.pop < 500) {
			this.popularidade = new Normal();
		} else if (this.pop > 500 && this.pop < 1000) {
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
	
 	public int getPop() {
 		return this.pop;
 	}

	public Post getPost(int indice) {
 		return posts.get(indice);
 	}
	
	public Post getUltimoPost() {
		return this.posts.get(posts.size() - 1);
	}
	
	public String getPost(String atributo, int post) {
		return this.getPost(post).getPost(atributo);
	}
	
	public int getQtdPost() {
		return this.posts.size();
	}
		
	public List<Post> getPosts() {
		return this.posts;
	}
	
	public String getConteudo(String atributo, int indicePost) {
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

	// controlar as excecoes de formato e data invalidas
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
			throw new AtualizaPerfilException("Erro na atualizacao de perfil.");
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
		if (this.pop > outroUsuario.getPop()) {
			return 1;
		} else if (this.pop == outroUsuario.getPop()) {
			return 0;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
}