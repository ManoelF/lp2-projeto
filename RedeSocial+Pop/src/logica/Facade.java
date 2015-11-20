/* =========================== Rede Social +Pop ================================= #
 *                                                                                *
 * Projeto obrigatorio para cumprimento de nota da disciplina Programação II      * 	  
 * e Laboratorio de Programacao II.                                               *
 *                                                                                *
 * Departamento de Informatica e Engenharia Eletrica                              *
 * Curso Ciência da Computação (UFCG - 2015.1)                                    *
 * Laboratorio de Programação II                                                  *
 *                                                                                *
 * Discentes envolvidos:                                                          *
 *          Italo Batista                                                         *
 *          Jose Manoel Ferreira                                                  *
 *          Kerilin Chang.                                                        *
 *                                                                                *
 * Orientador:                                                                    *
 *          Francisco Neto.                                                       *
 *                                                                                *
 * ============================================================================== #
 */


package logica;

import java.io.Serializable;

import exceptions.FechaSistemaException;
import exceptions.LogicaException;
import exceptions.RedeSocialMaisPopException;

/**
 * <code>Facade</code> a porta de entrada do Projeto. 
 *
 */
public class Facade implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8366038152046401019L;
	private Controller controller;
	private Util util;
		
	public Facade() {
		this.util = Util.getInstancia();
	}
	
	public void iniciaSistema()  {
			this.controller = util.restauraSistema();
			if (this.controller == null) {
				this.controller = new Controller();
			}

	}
	
	public void fechaSistema() throws RedeSocialMaisPopException {
		if (controller.getUsuarioLogado() != null) {
			throw new FechaSistemaException();
		} else {
			util.salvaSistema(controller);
		}
	}
	
	public String cadastraUsuario(String nome, String email, String senha, String nascimento, String imagem) throws RedeSocialMaisPopException {
		return this.controller.cadastraUsuario(nome, email, senha, nascimento, imagem);
	}

	public String cadastraUsuario(String nome, String email, String senha, String nascimento) throws RedeSocialMaisPopException {
		return cadastraUsuario(nome, email, senha, nascimento, "resources/default.jpg");
	}
	
	public void atualizaPerfil(String atributo, String novoValor) throws RedeSocialMaisPopException {
		this.controller.atualizaPerfil(atributo, novoValor);
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws RedeSocialMaisPopException {
		this.controller.atualizaPerfil(atributo, valor, velhaSenha);
	}
	
	public String atualizaTrendingTopics() {
		return this.controller.atualizaTrendingTopics();
	}
	
	public void login(String email, String senha) throws RedeSocialMaisPopException {
		this.controller.login(email, senha);
	}
	
	public void logout() throws RedeSocialMaisPopException {
		this.controller.logout();	
	}
	
	public void adicionaAmigo(String usuario) throws RedeSocialMaisPopException  {
		this.controller.adicionaAmigo(usuario);
	}
	
	public void rejeitaAmizade(String email) throws RedeSocialMaisPopException {
		this.controller.rejeitaAmizade(email);
	}
		
	public void aceitaAmizade(String usuario) throws RedeSocialMaisPopException {
		this.controller.aceitaAmizade(usuario);
	}
	
	public void removeAmigo(String usuario) throws RedeSocialMaisPopException  {
		this.controller.removeAmigo(usuario);
	}
	
	public void removeUsuario(String usuario) {
		this.controller.removeUsuario(usuario);
	}
	
	public int getPopsUsuario() { 
		return this.controller.getPops();
	}
	
	public int getPopsUsuario(String email) throws RedeSocialMaisPopException {
		return controller.getPopsUsuario(email);
	}
	
	public String getPopularidade() {
		return this.controller.getPopularidade();
	}
	
	public void curtirPost(String amigo, int post) throws RedeSocialMaisPopException {
		this.controller.curtirPost(amigo, post);
	}
	
	public void rejeitarPost(String amigo, int post) throws RedeSocialMaisPopException {
		this.controller.descurtirPost(amigo, post);
	}
	
	public void criaPost(String mensagem, String data) throws RedeSocialMaisPopException {
		this.controller.criaPost(mensagem, data);
	}
	
	public void salvaPosts() throws RedeSocialMaisPopException {
		this.controller.salvaPosts();
	}
	
	public String getPost(int post) {
		return this.controller.getPost(post);
	}
	
	public String getPost(String atributo, int post) throws RedeSocialMaisPopException {
		return this.controller.getPost(atributo, post);
	}	
	
	public String getConteudoPost(int indice, int post) throws RedeSocialMaisPopException {
		return this.controller.getConteudoPost(indice, post);
	}
	
	public int getPopsPost(int indice){
		return controller.getPopsPost(indice);
	}
	
	public int qtdCurtidasDePost(int indice) throws RedeSocialMaisPopException {
		return this.controller.qtdCurtidasDePost(indice);
	}
	
	public int qtdRejeicoesDePost(int indice) throws RedeSocialMaisPopException {
		return this.controller.qtdRejeicoesDePost(indice);
	}
	
	public String getInfoUsuario(String atributo, String usuario) throws RedeSocialMaisPopException {
		return this.controller.getInfoUsuario(atributo, usuario);
	}
	
	public String getInfoUsuario(String atributo) throws RedeSocialMaisPopException {
		return this.controller.getInfoUsuario(atributo);
	}
	
	public String getNextNotificacao() throws RedeSocialMaisPopException {
		return this.controller.getNextNotificacao();
	}
	 
	public int getNotificacoes() {
		return this.controller.getNotificacoes();
	}
	
	public int getQtdAmigos() {
		return this.controller.getQtdAmigos();
	}
	
	public String atualizaRanking() throws LogicaException {
		return this.controller.atualizaRanking();	
	}
	
	public void atualizaFeed() throws RedeSocialMaisPopException {
		controller.atualizaFeed();		
	}
	
	public String getFeed(int post) throws RedeSocialMaisPopException {
		return controller.getFeed(post);
	}
	
	public String getPostFeedNoticiasRecentes(int post) throws RedeSocialMaisPopException {
		ordenaFeedPorData();
		return getFeed(post);
	}
	
	public String getPostFeedNoticiasMaisPopulares(int post) throws RedeSocialMaisPopException {
		ordenaFeedPorPopularidade();
		return getFeed(post);
	}
	
	public void ordenaFeedPorData(){
		controller.ordenaFeedPorData();
	}
	
	public void ordenaFeedPorPopularidade() {
		controller.ordenaFeedPorPopularidade();
	}
	
	public void adicionaPops(int pops) {
		controller.adicionaPops(pops);
	}
	
	public void setPops(int pop) {
		this.controller.setPops(pop);	
	}
	
}
