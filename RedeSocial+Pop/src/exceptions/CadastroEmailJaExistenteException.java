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
 * <b>CadastroEmailJaExistenteException</b> encapsulamento de erro referente ao cadastro de um usuario no sistema
 * como email ja existente.
 */
package exceptions;

public class CadastroEmailJaExistenteException extends LogicaException{

	private static final long serialVersionUID = 4542496582471908745L;

	/**
	 * Construtor <b>CadastroEmailJaExistenteException</b>.
	 */
	public CadastroEmailJaExistenteException() {
		super("Ja exite um usuario cadastrado com esse e-mail! Por favor insira um outro.");
	}
		
}
