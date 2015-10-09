package logica;

import easyaccept.EasyAccept;
import exceptions.AtualizaPerfilException;
import exceptions.EntradaException;
import exceptions.FechaSistemaException;
import exceptions.LogicaException;
import exceptions.NaoHaNotificacoesException;
import exceptions.PostException;
import exceptions.SenhaProtegidaException;
import exceptions.UsuarioNaoCadastradoException;

public class Facade {
	
	private Controller controller;
		
	public Facade() {
		this.controller = new Controller();
	}
	
	public void iniciaSistema() {
		
	}
	
	public void fechaSistema() throws FechaSistemaException {
		if (controller.getUsuarioLogado() != null) {
			throw new FechaSistemaException();
		} else {
			// FechaSistema
		}
	}
	
	public String cadastraUsuario(String nome, String email, String senha, String nascimento, String imagem) throws EntradaException,  LogicaException {
		return this.controller.cadastraUsuario(nome, email, senha, nascimento, imagem);
	}

	public String cadastraUsuario(String nome, String email, String senha, String nascimento) throws EntradaException, LogicaException {
		return cadastraUsuario(nome, email, senha, nascimento, "resources/default.jpg");
	}
	
	public void atualizaPerfil(String atributo, String novoValor) throws LogicaException, EntradaException {
		this.controller.atualizaPerfil(atributo, novoValor);
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws AtualizaPerfilException, LogicaException {
		this.controller.atualizaPerfil(atributo, valor, velhaSenha);
	}
	
	public void login(String email, String senha) throws LogicaException, EntradaException {
		this.controller.login(email, senha);
	}
	
	public void logout() throws LogicaException {
		this.controller.logout();	
	}
	
	public void adicionaAmigo(String usuario) throws LogicaException  {
		this.controller.adicionaAmigo(usuario);
	}
	
	public void rejeitaAmizade(String email) throws LogicaException {
		this.controller.rejeitaAmizade(email);
	}
		
	public void aceitaAmizade(String usuario) throws LogicaException {
		this.controller.aceitaAmizade(usuario);
	}
	
	public void removeAmigo(String usuario) throws UsuarioNaoCadastradoException  {
		this.controller.removeAmigo(usuario);
	}
	
	public void removeUsuario(String usuario) {
		this.controller.removeUsuario(usuario);
	}
	
	public void curtirPost(String amigo, int post) throws LogicaException {
		this.controller.curtirPost(amigo, post);
	}
	
	public void criaPost(String mensagem, String data) throws PostException {
		this.controller.criaPost(mensagem, data);
	}
	
	public String getPost(int post) {
		return this.controller.getPost(post);
	}
	
	public String getPost(String atributo, int post) {
		return this.controller.getPost(atributo, post);
	}	
	
	public String getConteudoPost(int indice, int post) throws LogicaException, PostException {
		return this.controller.getConteudoPost(indice, post);
	}
	
	public String getInfoUsuario(String atributo, String usuario) throws LogicaException, EntradaException {
		return this.controller.getInfoUsuario(atributo, usuario);
	}
	
	public String getInfoUsuario(String atributo) throws SenhaProtegidaException, EntradaException {
		return this.controller.getInfoUsuario(atributo);
	}
	
	public String getNextNotificacao() throws NaoHaNotificacoesException {
		return this.controller.getNextNotificacao();
	}
	 
	public int getNotificacoes() {
		return this.controller.getNotificacoes();
	}
	
	public int getQtdAmigos() {
		return this.controller.getQtdAmigos();
	}
	
	public void atualizaRanking() {
		this.controller.atualizaRanking();
		
	}
	
	public void atualizaFeed() {
		controller.atualizaFeed();
	}
	
	public void ordenaFeedPorData(){
		
	}
	
	public void ordenaFeedPorPopularidade(){
		
	}
	
	public static void main(String[] args) {
 	    args = new String[] {"logica.Facade", "lib/ScriptsTeste/usecase_1.txt", "lib/ScriptsTeste/usecase_2.txt", "lib/ScriptsTeste/usecase_3.txt", "lib/ScriptsTeste/usecase_4.txt"};
	    EasyAccept.main(args);
	}

}
