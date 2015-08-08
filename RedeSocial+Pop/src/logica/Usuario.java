package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import exceptions.*;

public class Usuario implements Comparable<Usuario> {
	
	private String nome;
	private String email;
	private String nascimento;
	private String senha;
	private String imagem;
	private int pop;
	private List<Usuario> amigos;
	private List<String> solicitacaoAmizade;
	private List<String> notificacoes;
	private TipoPopularidade popularidade;
	private List<Post> posts;
	private List<Post> feed;

	
	// Foi adicionado o throws ParseException, deve ser tratado
 	public Usuario(String nome, String email, String senha, String nascimento, String imagem) throws CadastroInvalidoException, ParseException {

		if (nome == null || nome.trim().length() == 0){
			throw new CadastroNomeException();
		}
		if (email == null || email.trim().length() == 0) {
			throw new CadastroEmailException();
		}
		if (senha == null || senha.trim().length() == 0) {
			throw new CadastroSenhaException();
		}
		if (nascimento == null || nascimento.trim().length() == 0) {
			throw new CadastroDataException();
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
		recebeDataNascimento(nascimento);
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.pop = 0;
		this.amigos = new ArrayList<>();
		this.solicitacaoAmizade = new ArrayList<>();
		this.notificacoes = new ArrayList<>();
		this.posts = new ArrayList<>();
		this.feed = new ArrayList<>();
		this.popularidade = new Normal();
	}
 	
 	public Usuario(String nome, String email, String senha, String nascimento) throws CadastroInvalidoException, ParseException {

		if (nome == null || nome.trim().length() == 0){
			throw new CadastroNomeException();
		}
		if (email == null || email.trim().length() == 0) {
			throw new CadastroEmailException();
		}
		if (senha == null || senha.trim().length() == 0) {
			throw new CadastroSenhaException();
		}
		if (nascimento == null || nascimento.trim().length() == 0) {
			throw new CadastroDataException();
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
		recebeDataNascimento(nascimento);
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.pop = 0;
		this.imagem = "resources/avatarDefaul.jpg";
		this.amigos = new ArrayList<>();
		this.solicitacaoAmizade = new ArrayList<>();
		this.notificacoes = new ArrayList<>();
		this.posts = new ArrayList<>();
		this.feed = new ArrayList<>();
		this.popularidade = new Normal();
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

	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNascimento() {
		return this.nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getImagem() {
		return this.imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String insereSenha(String senha) {
		return senha;
	}

	public String insereEmail(String email) {
		return email;
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
	
	//falta testar os prox codigos
	
	public void alterarNome(String novoNome) throws AtualizaPerfilException {
		if (novoNome == null || novoNome.trim().length() == 0){
			throw new AtualizaNomeException();
		}
		this.nome = novoNome;
	}
	
	// verificar formatos de email incorretos
	public void alterarEmail(String novoEmail) throws AtualizaPerfilException {
		if (novoEmail == null || novoEmail.trim().length() == 0) {
			throw new AtualizaEmailException();
		}
		this.email = novoEmail;
	}
	
	public boolean alterarSenha(String senha, String novaSenha) throws AtualizaPerfilException {
		if (senha == null || senha.trim().length() == 0) {
			throw new AtualizaSenhaException();
		}
		
		if (novaSenha == null || novaSenha.trim().length() == 0) {
			throw new AtualizaSenhaException();
		}
		
		if (this.senha.equals(senha)) {
			this.senha = novaSenha;
			return true;
		} else {
			//Lancar exception
			return false;
		}
	}	

	// controlar as excecoes de formato e data invalidas
	public void alterarNascimento(String novoNascimento) throws AtualizaPerfilException, ParseException {
		if (novoNascimento == null || novoNascimento.trim().length() == 0) {
			throw new ParseException("Data inserida invalida", 0);
		}
		recebeDataNascimento(novoNascimento);
	}
	
	public void alterarImagem(String novaImagem) throws AtualizaPerfilException {
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
			throw new CadastroSenhaException();
		} else if (email.contains("@") && email.contains(".com")) {
			this.email = email;
		} else {
			throw new CadastroEmailException();
		}
	}

	// Tratando a data de Nascimento
	// Falta tratar essa excecao
	private void recebeDataNascimento(String dataRecebida) throws ParseException  {
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataRecebida);  // transforma o aquivo recebido para Date()
		this.nascimento = new SimpleDateFormat("yyyy-MM-dd").format(data);

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
		usuario.notificacoes.add(this.nome + " curtiu seu post de " + post.getDataAtual() + ".");
		usuario.atualizaPopularidade();
	}
	
	public void descurtir(Usuario usuario, int indice) {
		Post post = usuario.getPost(indice);
		this.popularidade.descurtir(post);
		usuario.notificacoes.add(this.nome + " descurtiu seu post de " + post.getDataAtual() + ".");
		usuario.atualizaPopularidade();
	}

	public String getFoto() {
		return this.imagem;
	}
	
	public void criaPost(String mensagem, String data) throws PostException, ParseException {
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
		return this.posts.get(indicePost).getArquivo(indiceArquivo);
		
	}
	
	public void removeAmigo(Usuario usuario) {
		this.amigos.remove(usuario);
	}
	
	public int getQtdPost() {
		return this.posts.size();
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
	
	
}