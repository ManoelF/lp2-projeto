package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import exceptions.*;

public class Usuario implements Comparable<Usuario> {
	
	private String nome;
	private String email;
	private LocalDate nascimento;
	private String senha;
	private String imagem;
	private int pop;
	private List<Usuario> amigos;
	private List<String> solicitacaoAmizade;
	private List<String> notificacoes;
	private TipoPopularidade popularidade;
	private List<Post> posts;
	private Feed feed;

	
	// Foi adicionado o throws ParseException, deve ser tratado
 	public Usuario(String nome, String email, String senha, String nascimento, String imagem) throws CadastroInvalidoException {

		if (nome == null || nome.trim().length() == 0){
			throw new CadastroInvalidoException(" Nome dx usuarix nao pode ser vazio.");
		}
		if (email == null || email.trim().length() == 0) {
			throw new CadastroInvalidoException(" Formato de e-mail esta invalido.");
		}
		if (senha == null || senha.trim().length() == 0) {
			throw new CadastroInvalidoException(" Senha dx usuarix nao pode ser vazio.");
		}
		if (nascimento == null || nascimento.trim().length() == 0) {
			throw new CadastroInvalidoException(" Formato de data esta invalida.");
		}
		if (Util.getInstancia().verificaFormatoData(nascimento) == false) {
			throw new CadastroInvalidoException(" Formato de data esta invalida.");
		}
		if (Util.getInstancia().verificaDataValida(nascimento) == false) {
			throw new CadastroInvalidoException(" Data nao existe.");
		}
		
		if (imagem== null){
			throw new CadastroInvalidoException(" Imagem invalida.");
		}
		if (imagem.trim().length() == 0) {
			this.imagem = "resources/default.jpg";
		} else {
			this.imagem = imagem;
		}
		
		verificaEmail(email);
		this.nascimento = Util.getInstancia().recebeData(nascimento);
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.pop = 0;
		this.amigos = new ArrayList<>();
		this.solicitacaoAmizade = new ArrayList<>();
		this.notificacoes = new ArrayList<>();
		this.posts = new ArrayList<>();
		this.feed = new Feed();
		this.popularidade = new Normal();
	}
 	
 	public Usuario(String nome, String email, String senha, String nascimento) throws CadastroInvalidoException {
 		this(nome, email, senha, nascimento, "resources/avatarDefaul.jpg");//eu num disse que dava!
	}
 	
 	public String getNextNotificacao() throws NaoHaNotificacoesException {
 		if (this.notificacoes.size() == 0) {
 			throw new NaoHaNotificacoesException();
 		} else {
 			String notifi = this.notificacoes.get(0); 
 			this.notificacoes.remove(0);
 			return notifi;
 		}
	}

 	public int getPop() {
 		return this.pop;
 	}

	public Post getPost(int indice) {
 		return posts.get(indice);
 	}
	
/*	public String getPost(int indice) {
		return posts.get(indice).getPost();
	}*/

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
		if (novoNome == null || novoNome.trim().length() == 0){
			throw new AtualizaPerfilException(" Nome dx usuarix nao pode ser vazio.");
		}
		this.nome = novoNome;
	}
	
	public void setEmail(String novoEmail) throws EntradaException {
		if (novoEmail == null || novoEmail.trim().length() == 0
		   || !novoEmail.contains("@") || !novoEmail.contains(".com")) {		
				throw new AtualizaPerfilException(" Formato de e-mail esta invalido.");
		}
		this.email = novoEmail;
	}
	
	public boolean setSenha(String valor, String velhaSenha) throws AtualizaPerfilException {
		if (this.senha.equals(velhaSenha)) {
			
			if (valor == null || valor.trim().length() == 0) {
				throw new AtualizaPerfilException(" Senha invalida.");
			} else {
				this.senha = valor;
				return true;
			}
			
		} else {
			throw new AtualizaPerfilException(" A senha fornecida esta incorreta.");	
		} 
	}	

	// controlar as excecoes de formato e data invalidas
	public void setNascimento(String novoNascimento) throws AtualizaPerfilException {
		if (novoNascimento == null || novoNascimento.trim().length() == 0) {
			throw new AtualizaPerfilException(" Formato de data esta invalida.");
		}
		if (Util.getInstancia().verificaFormatoData(novoNascimento) == false) {
			throw new AtualizaPerfilException(" Formato de data esta invalida.");
		}
		if (Util.getInstancia().verificaDataValida(novoNascimento) == false) {
			throw new AtualizaPerfilException(" Data nao existe.");
		}
		this.nascimento = Util.getInstancia().recebeData(novoNascimento);;
	}
	
	public void setImagem(String novaImagem) throws AtualizaPerfilException {
		if (novaImagem == null) {
			throw new AtualizaPerfilException("");
		}
		if (novaImagem.trim().length() == 0) {
			this.imagem = "resources/avatarDefaul.jpg";
		} else {
			this.imagem = novaImagem;
		}
	}
			
	public void rejeitaAmizade(String emailUserRecusado) {
		this.solicitacaoAmizade.remove(emailUserRecusado);
		this.notificacoes.remove( this.notificacoes.size() - 1 );
	}
	
	public void aceitaAmizade(Usuario usuarioAceito) {
		this.solicitacaoAmizade.remove(usuarioAceito.getEmail());
		this.notificacoes.remove( this.notificacoes.size() - 1 );
		this.amigos.add(usuarioAceito);
	}
		
	private void verificaEmail(String email) throws CadastroInvalidoException {
		if (email == null || email.equals("")) {
			throw new CadastroInvalidoException(" Formato de e-mail esta invalido.");
		} else if (email.contains("@") && email.contains(".com")) {
			this.email = email;
		} else {
			throw new CadastroInvalidoException(" Formato de e-mail esta invalido.");
		}
	}

	private void atualizaPops() {
		int pops = 0;
		for (Post post: posts) {
			pops += post.getPopularidade();
		}
		
		this.pop = pops;
	}
		
	private void atualizaPopularidade() {
		atualizaPops();
		if( this.pop < 500) {
			this.popularidade = new Normal();
		} else if (this.pop > 500 && this.pop < 1000) {
			this.popularidade = new CelebridadePOP();
		} else {
			this.popularidade = new IconePOP();
		}
		
	}

	public void curtir(Usuario usuario, int indice) {
		Post post = usuario.getPost(indice);
		this.popularidade.curtir(post);
		usuario.notificacoes.add(this.nome + " curtiu seu post de " + post.getDataString() + ".");
		usuario.atualizaPopularidade();
	}
	
	public void descurtir(Usuario usuario, int indice) {
		Post post = usuario.getPost(indice);
		this.popularidade.descurtir(post);
		usuario.notificacoes.add(this.nome + " descurtiu seu post de " + post.getDataString() + ".");
		usuario.atualizaPopularidade();
	}

	public String getFoto() {
		return this.imagem;
	}
	
	public void criaPost(String mensagem, String data) throws PostException {
		Post novoPost = new Post(mensagem, data);
		this.posts.add(novoPost);
	}
	
	public List<Post> getPosts() {
		return this.posts;
	}
	
	public String getInfoUsuario(String atributo) {
		return null;
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
	
	public int getNotificacoes() {
		return this.notificacoes.size();
	}
	
	public int getQtdAmigos() {
		return this.amigos.size();
	}
	
	public String getArquivo(int indiceArquivo, int indicePost) {
		return this.posts.get(indicePost).getMidias(indiceArquivo);
		
	}
	
	public void removeAmigo(Usuario usuario) {
		this.amigos.remove(usuario);
	}
	
	public int getQtdPost() {
		return this.posts.size();
	}
	
	public String getPost(String atributo, int post) {
		return this.getPost(post).getPost(atributo);
	}
	
	public String getConteudo(String atributo, int indicePost) {
		return this.posts.get(indicePost).getPost(atributo);
	}

	public void recebeNotificao(String notificao) {
		this.notificacoes.add(notificao);
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
	
	public TipoPopularidade getPopularidade() {
		return this.popularidade;
	}

	public void atualizaFeed() {
		this.feed.atualizaFeed(this.amigos);
	}
	
	public String getConteudoPost(int indice, int post) {
		return this.posts.get(post).getMidias(indice);
	}
}