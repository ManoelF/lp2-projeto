package logica;

import java.text.ParseException;

import easyaccept.EasyAccept;
import exceptions.*;

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

	public void atualizaRanking() {
		
	}
	
	public String cadastraUsuario(String nome, String email, String senha, String nascimento, String imagem) throws EntradaException, ParseException, LogicaException {
		return this.controller.cadastraUsuario(nome, email, senha, nascimento, imagem);
	}

	public String cadastraUsuario(String nome, String email, String senha, String nascimento) throws EntradaException, ParseException, LogicaException {
		return cadastraUsuario(nome, email, senha, nascimento, "");
	}
	
	public void login(String email, String senha) throws LogicaException, EntradaException {
		this.controller.login(email, senha);
	}
	
	public void logout() throws UsuarioDeslogadoException {
		this.controller.logout();	
	}
	
	public String getInfoUsuario(String atributo, String usuario) throws LogicaException {
		return this.controller.getInfoUsuario(atributo, usuario);
	}
	
	public String getInfoUsuario(String atributo) throws SenhaProtegidaException {
		return this.controller.getInfoUsuario(atributo);
	}
	
	public void atualizaPerfil(String atributo, String novoValor) throws LogicaException, ParseException, EntradaException {
		this.controller.atualizaPerfil(atributo, novoValor);
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws AtualizaPerfilException, LogicaException {
		this.controller.atualizaPerfil(atributo, valor, velhaSenha);
	}
	
	public void criaPost(String mensagem, String data) throws PostException, ParseException {
		this.controller.criaPost(mensagem, data);
	}
	
	public String getPost(int post) {
		return this.controller.getPost(post);
	}
	
	public String getPost(String atributo, int post) {
		return this.getPost(atributo, post);
	}	
	
	public String getConteudoPost(int indice, int post) {
		return null;
	}
	
	public String getNextNotificacao() throws NaoHaNotificacoesException {
		return this.controller.getNextNotificacao();
	}
	 
	public int getNotificacoes() {
		return this.controller.getNotificacoes();
	}
	
	public void rejeitaAmizade(String email) throws LogicaException {
		this.controller.rejeitaAmizade(email);
	}
	
	public void adicionaAmigo(String usuario) throws LogicaException  {
		this.controller.adicionaAmigo(usuario);
	}
	
	public int getQtdAmigos() {
		return this.controller.getQtdAmigos();
	}
	
	public void aceitaAmizade(String usuario) throws LogicaException {
		this.controller.aceitaAmizade(usuario);
	}
	
	public void curtirPost(String amigo, int post) throws UsuarioNaoCadastradoException {
		this.controller.curtirPost(amigo, post);
	}
	
	public void removeAmigo(String usuario) throws UsuarioNaoCadastradoException  {
		this.controller.removeAmigo(usuario);
	}
	
	public void removeUsuario(String usuario) {
		this.controller.removeUsuario(usuario);
	}

	public static void main(String[] args) {
	    args = new String[] {"logica.Facade", "lib/ScriptsTeste/usecase_1.txt", "lib/ScriptsTeste/usecase_2.txt", "lib/ScriptsTeste/usecase_3.txt", "lib/ScriptsTeste/usecase_4.txt"};
//	    args = new String[] {"logica.Facade", "lib/ScriptsTeste/usecase.txt"};

	    EasyAccept.main(args);
	}
/*
 * erros
 * inciaSistema --> iniciaSistema// usecase_4.txt
 * fecharSistema --> fechaSistema// usecase_2.txt
 * At line 58: gal_costa@email.com --> gal_costa@email.com.br // usecase_1.txt
 * At line 54: "madonna@email.com" --> "madona@email.com"
 * At line 26: Nao deveria dar erro pq a senha esta correta por ter sido alterada anteriomente (mudado para will_S2) // usecase_2.txt
 */
}
