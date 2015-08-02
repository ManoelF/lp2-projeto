package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import exceptions.*;

public class Usuario {
	
	private String nome;
	private String email;
	private String nascimento;
	private String senha;
	private String imagem;
	private int pop;
	private List<Usuario> amigos;
	private List<String> solicitacaoAmizade;
	private List<String> notificacoes;
	private List<Post> posts;

	
	// Foi adicionado o throws ParseException, deve ser tratado
 	public Usuario(String nome, String email, String senha, String nascimento, String imagem) throws CadastroInvalidoException, ParseException {
		if (nome == null || nome.equals("")){
			throw new CadastroInvalidoException("Nome");
		}
		if (email == null || email.equals("")) {
			throw new CadastroInvalidoException("Email");
		}
		if (senha == null || senha.equals("")) {
			throw new CadastroInvalidoException("Senha");
		}
		if (nascimento == null || nascimento.equals("")) {
			throw new CadastroInvalidoException("Nascimento");
		}
		if (imagem == null) {
			throw new CadastroInvalidoException("Imagem");
		}
		if (imagem.equals("")) {
			this.imagem = "resources/avatarDefaul.jpg";
		} else {
			this.imagem = imagem;
		}
		recebeDataNascimento(nascimento);
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.pop = 0;
		this.amigos = new ArrayList<>();
		this.solicitacaoAmizade = new ArrayList<>();
		this.notificacoes = new ArrayList<>();
		this.posts = new ArrayList<>();
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
	
	public List<String> getNotificoes() {
		return this.notificacoes;
	}
	
	//falta testar os prox codigos
	
	public void alterarNome(String novoNome) throws AtualizaPerfilException {
		if (novoNome == null || novoNome.equals("")){
			throw new AtualizaNomeException();
		}
		this.nome = novoNome;
	}
	
	// verificar formatos de email incorretos
	public void alterarEmail(String novoEmail) throws AtualizaPerfilException {
		if (novoEmail == null || novoEmail.equals("")) {
			throw new AtualizaEmailException();
		}
		this.email = novoEmail;
	}
	
	public boolean alterarSenha(String senha, String novaSenha) throws AtualizaPerfilException {
		if (senha == null || senha.equals("")) {
			throw new AtualizaSenhaException();
		}
		
		if (novaSenha == null || novaSenha.equals("")) {
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
		if (novoNascimento == null || novoNascimento.equals("")) {
			// lancar excecao
		}
		recebeDataNascimento(novoNascimento);
	}
	
	public void alterarImagem(String novaImagem) throws AtualizaPerfilException {
		if (novaImagem == null) {
			throw new AtualizaPerfilException("");
		}
		if (novaImagem.equals("")) {
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
		
	public void like(Post post) {
		post.ganhaLike();
	}
	
	public void deslike(Post post) {
		post.ganhaDeslike();
	}

	// Tratando a data de Nascimento
	// Falta tratar essa excecao
	public void recebeDataNascimento(String dataRecebida) throws ParseException  {
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataRecebida);  // transforma o aquivo recebido para Date()
		this.nascimento = new SimpleDateFormat("yyyy-MM-dd").format(data);
		}	
	
	public String getFoto() {
		return this.imagem;
	}
	
	public void criaPost(String mensagem, String data) throws PostException, ParseException {
		Post novoPost = new Post(mensagem, data);
	}
	
	public List<Post> getPosts() {
		return this.posts;
	}
	
	
	
}