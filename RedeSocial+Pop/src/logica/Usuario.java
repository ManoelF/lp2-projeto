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
	private List<Post> postes;
	private TipoPopularidade popularidade;

	
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
	}

 	public Post getPost(int indice) {
 		return postes.get(indice);
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
	
	//Caso de Uso3: Pesquisar e alterar informacoes do usuario
	
	public void alterarNome(String novoNome) throws AtualizaInformacaoExcpetion {
		if (novoNome == null || novoNome.equals("")){
			throw new AtualizaInformacaoExcpetion("Nome");
		}
		this.nome = novoNome;
	}
	
	public void alterarEmail(String novoEmail) throws AtualizaInformacaoExcpetion {
		if (novoEmail == null || novoEmail.equals("")) {
			throw new AtualizaInformacaoExcpetion("Email");
		}
		this.email = novoEmail;
	}
	
	public boolean alterarSenha(String senha, String novaSenha) throws AtualizaInformacaoExcpetion {
		if (senha == null || senha.equals("")) {
			throw new AtualizaInformacaoExcpetion("Senha");
		}
		
		if (novaSenha == null || novaSenha.equals("")) {
			throw new AtualizaInformacaoExcpetion("Senha");
		}
		
		if (this.senha.equals(senha)) {
			this.senha = novaSenha;
			return true;
		} else {
			//Lancar exception
			return false;
		}
	}	
	
	public void alterarNascimento(String novoNascimento) throws AtualizaInformacaoExcpetion, ParseException {
		if (novoNascimento == null || novoNascimento.equals("")) {
			throw new AtualizaInformacaoExcpetion("Data de nascimento");
		}
		recebeDataNascimento(novoNascimento);
	}
	

	public void alterarImagem(String novaImagem) throws AtualizaInformacaoExcpetion {
		if (novaImagem == null) {
			throw new AtualizaInformacaoExcpetion("Imagem");
		}
		if (novaImagem.equals("")) {
			this.imagem = "resources/avatarDefaul.jpg";
		} else {
			this.imagem = novaImagem;
		}
	}
			
	public void rejeitaAmizade(String emailUserRecusado) {
		this.solicitacaoAmizade.remove(emailUserRecusado);
		//remover a notificacao
	}
	
	public void aceitaAmizade(Usuario usuarioAceito) {
		this.solicitacaoAmizade.remove(usuarioAceito.getEmail());
		//remover a notificacao
		this.amigos.add(usuarioAceito);
	}
		
	private void verificaEmail(String email, String mensagem) throws CadastroInvalidoException {
		if (email.contains("@") && email.contains(".com")) {
			this.email = email;
		} else {
			throw new CadastroInvalidoException("Email");
		}
			
	}

	/*
	 * public void removeAmigo(Usuario amigo){
	 * 
	 * for(int i; i < amigos.size(); i++){
	 * 	if((amigos.contains(amigo)){
	 * 		amigos.remove(amigo)
	 * 		}
	 * 	}
	 * TANTO FAZ
	 * for(int i; i < amigos.size(); i++){
	 * 		if(amigos.get(i).getEmail().equals(amigo.getEmail())){
	 * 			amigos.remove(amigo);
	 * 		}
	 * }
	 * 
	 */

	// Tratando a data de Nascimento
	// Falta tratar essa excecao
	public void recebeDataNascimento(String dataRecebida) throws ParseException  {
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataRecebida);  // transforma o aquivo recebido para Date()
		this.nascimento = new SimpleDateFormat("yyyy-MM-dd").format(data);
		}	
	
	private void atualizaPops() {
		int pops = 0;
		for (Post post: postes) {
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

	public void curtir(Usuario usuario, int indice) {
		Post post = usuario.getPost(indice);
		this.popularidade.curtir(post);
		usuario.atualizaPopularidade();
	}
	
	public void descurtir(Usuario usuario, int indice) {
		Post post = usuario.getPost(indice);
		this.popularidade.descurtir(post);
		usuario.atualizaPopularidade();
	}
}