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
 * <b>UsuarioNaoCadastradoException</b> encapsulamento de erro referente a logica de cadastro de usuarios.
 */
package exceptions;

public class UsuarioNaoCadastradoException extends LogicaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor <b>UsuarioNaoCadastradoException</b>.
	 * 
	 * @param msg 
	 * 		Mensagem referente a excecao.
	 */
	public UsuarioNaoCadastradoException(String msg) {
		super(msg);
	}
	
}
