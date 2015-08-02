package logica;

import java.text.ParseException;

import exceptions.AtualizaPerfilException;
import exceptions.EmailIncorretoException;
import exceptions.EntradaException;
import exceptions.LogicaException;
import exceptions.LoginException;
import exceptions.PostException;
import exceptions.SenhaAcessoException;

public class Facade {
	
	private Controller controller;
	
	
	public Facade() {
		this.controller = new Controller();
	}
	
	public void iniciaSistema() {
		
	}
	
	public void fechaSistema() {
		
	}

	
	public void atualizaRanking() {
		
	}
	public Usuario cadastraUsuario(String nome, String email, String senha, String nascimento, String imagem) throws EntradaException, ParseException, LogicaException {
		return this.controller.cadastraUsuario(nome, email, senha, nascimento, imagem);
	}
	
	public void login(String email, String senha) throws LogicaException, EntradaException {
		this.controller.login(email, senha);
	}
	
	public void logout() throws LoginException {
		this.controller.logout();
		
	}
	
	public String getInfoUsuaio(String atributo, Usuario usuario) throws SenhaAcessoException {
		return this.controller.getInfoUsuario(atributo, usuario);
	}
	
	public String getInfoUsuaio(String atributo, String usuario) throws SenhaAcessoException {
		return this.controller.getInfoUsuario(atributo, usuario);
	}
	
	public String getInfoUsuaio(String atributo) throws SenhaAcessoException {
		return this.controller.getInfoUsuario(atributo);
	}

	
	public void atualizaPerfil(String atributo, String novoValor) throws AtualizaPerfilException, LogicaException, ParseException {
		this.controller.atualizaPerfil(atributo, novoValor);
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws AtualizaPerfilException, LogicaException {
		this.controller.atualizaPerfil(atributo, valor, velhaSenha);
	}
	
	public void criaPost(String mensagem, String data) throws PostException, ParseException {
		this.controller.criaPost(mensagem, data);
	}
	
	public String getPost(int post) {
		return null;
	}
	
	public String getPost(String atributo, int post) {
		return null;
	}	
	
	public String getConteudoPost(int indice, int post) {
		return null;
	}
	
	public String getNextNotificacao() {
		return this.controller.getNextNotificacao();
	}
	 
	public int getNotificacao() {
		return this.controller.getNotificacao();
	}
	
	public void rejeitaAmizade(String email) throws LogicaException {
		this.controller.rejeitaAmizade(email);
	}
	
	public void adicionaAmigo(String usuario) throws EmailIncorretoException {
		this.controller.adicionaAmigo(usuario);
	}
	
	public int getQtdAmigos() {
		return this.controller.getQtdAmigos();
	}
	
	public void aceitaAmizade(String usuario) throws LogicaException {
		this.controller.aceitaAmizade(usuario);
	}
	
	public void curtirPost(String amigo, int post) throws EmailIncorretoException {
		this.controller.curtirPost(amigo, post);
	}
	
	public void removeAmigo(String usuario) throws EmailIncorretoException {
		this.controller.removeAmigo(usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		this.controller.removeUsuario(usuario);
	}
	
	
	
	
	
	
	
	
}
