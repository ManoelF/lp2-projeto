package logica;

import java.util.List;
import java.util.ArrayList;
import exceptions.*;

public class Usuario {
	
	private String nome;
	private String email;
	private String telefone;
	private String nascimento;
	private String senha;
	private String imagem;
	private int pop;
	private List<Usuario> amigos;
	
	public Usuario(String nome, String email, String senha, String nascimento, String telefone, String imagem) throws StringException{
		if (nome == null || nome.equals("")){
			throw new StringException("Nome");
		}
		if (email == null || email.equals("")) {
			throw new StringException("Email");
		}
		if (senha == null || senha.equals("")) {
			throw new StringException("Senha");
		}
		if (nascimento == null || nascimento.equals("")) {
			throw new StringException("Nascimento");
		}
		if (telefone == null || telefone.equals("")) {
			throw new StringException("Telefone");
		}
		if (imagem == null) {
			throw new StringException("Imagem");
		}
		if (imagem.equals("")) {
			this.imagem = "resources/avatarDefaul.jpg";
		} else {
			this.imagem = imagem;
		}
		
		this.nome = nome;
		this.email = email;
		this.nascimento = nascimento;
		this.telefone = telefone;
		this.senha = senha;
		this.pop = 0;
		this.amigos = new ArrayList<>();
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
		return this.nascimento;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
	
	public String getImagem() {
		return this.imagem;
	}
}
