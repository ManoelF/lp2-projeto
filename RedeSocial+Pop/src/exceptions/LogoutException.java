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
 * <b>LogoutException</b> encapsulamento de erro referente a logica do sistema, mais especificamente,
 * quando ha relacao com o logout de usarios do sistema.
 */
package exceptions;

public class LogoutException extends LogicaException {


	private static final long serialVersionUID = 1L;

	/**
	 * Construtor <b>LogoutException</b>.
	 */
	public LogoutException(){
		super("Nao eh possivel realizar logout.");
	}
	
	/**
	 * Construtor <b>LogoutException</b>.
	 * 
	 * @param msg
	 * 			Mensagem de excecao.
	 */
	public LogoutException(String msg){
		super(msg);
	}
	
	/*UsuarioDeslogadoException: " Nenhum usuarix esta logadx no +pop."
	 * 
	 * 
	 * 
	 */
}