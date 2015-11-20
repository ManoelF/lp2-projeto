/* =========================== Rede Social +Pop ================================= #
 * 																				  *
 * Projeto obrigatorio para cumprimento de nota da disciplina Programação II      * 	  
 * e Laboratorio de Programacao II.                                               *
 *                                                                                *
 * Departamento de Informatica e Engenharia Eletrica							  *
 * Curso Ciência da Computação (UFCG - 2015.1). 								  *
 * Laboratorio de Programação II                                                  *
 * 																				  *
 * Discentes envolvidos: 														  *
 *   		Italo Batista														  *
 *   		Jose Manoel Ferreira												  *
 *   		Kerilin Chang. 														  *
 *																				  *
 * Orientador: 																	  *
 * 			Francisco Neto.		                                                  *
 * 												                                  *
 * ============================================================================== #
 */

/**
 * <b>LoginException</b> encapsulamento de erro referente a logica do sistema, em metodos que 
 * envolvam a atividade de logar um usuario.
 * 
 */
package exceptions;

public class LoginException extends LogicaException {


	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor <b>LoginException</b>.
	 */
	public LoginException() {
		super("Nao foi possivel realizar login.");
	}
	
	/**
	 * Construtor <b>LoginException</b>.
	 * 
	 * @param msg
	 * 			Mensagem de excecao.
	 */
	public LoginException(String msg) {
		super(msg);
	}
	
	/*UsuarioLogadoException(String nomeUsuario): " Um usuarix ja esta logadx: " + nomeUsuario + "."
	 *LoginEmailException(String email): " Um usuarix com email " + email + " nao esta cadastradx."
	 * 	     // "O usuario zeninguem@email.com.br nao esta cadastrado no +pop."
	 */
	
}