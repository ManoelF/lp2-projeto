/**
 * <code>FactoryUsuario</code>, tem a responsablidade de gerencias as instancias de <code>Usuarios</code> da Rede.
 */
package logica;

import java.io.Serializable;

import exceptions.CadastroInvalidoException;

public class FactoryUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5216069431632496757L;
	private Usuario usuario;
	private Util util;
	
	/**
	 * Construtor de <code><FactoryUsuario</code>.
	 */
	public FactoryUsuario() {
	}
	
	/**
	  * @param nome
	 * 		Nome do Usuario.
	 * 
	 * @param email
	 * 		Email do Usuario.
	 * 
	 * @param senha
	 * 		Senha de acesso do Usuario.
	 * 
	 * @param nascimento
	 * 		Data de nascimento so Usuario.
	 * 
	 * @param imagem
	 * 		Imagem do Usuario.
	 * 
	 * @throws CadastroInvalidoException
	 * 		Se algum dos parametro requerido para criacao do Usuario nao estiver
	 * 		de acordo com as especificacoes eh lancado a excecao <code>CadastroInvalidoException</code>.		
	 */
	public Usuario criaUsuario(String nome, String email, String senha,
			String nascimento, String imagem)
			throws CadastroInvalidoException{

		
 		this.util = Util.getInstancia();

		if (nome == null || !util.verificaAtributo(nome)){
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
			
		} else if (email == null || !util.verificaEmail(email)) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
			
		} else if (senha == null || !util.verificaSenha(senha)) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Senha dx usuarix nao pode ser vazio.");
			
		} else if (nascimento == null || nascimento.trim().length() == 0) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		} else if (util.verificaFormatoData(nascimento) == false) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		} else if (util.verificaDataValida(nascimento) == false) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Data nao existe.");
			
		} else if (imagem == null || imagem.trim().length() == 0) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Imagem inserida esta invalida.");
			
		} else {			
			usuario = new Usuario(nome, email, senha, nascimento, imagem);
			return usuario;
		}
		
	}
	
	

}