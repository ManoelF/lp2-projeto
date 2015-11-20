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

/**
 * <b>RedeSocialMaisPopException</b> encapsulamento de erro referente as excecoes lancadas em todo o sistema.
 * 
 */

package exceptions;

public class RedeSocialMaisPopException extends Exception {

	private static final long serialVersionUID = -1222897662918426177L;

	/**
	 * Construtor <b>RedeSocialMaisPopException</b>.
	 * 
	 * @param msg
	 * 			Mensagem de excecao.
	 */
	public RedeSocialMaisPopException(String msg) {
		super(msg);
	}
	
	/**
	 * Construtor <b>RedeSocialMaisPopException</b>.
	 */
	public RedeSocialMaisPopException() {
		super();
	}
		
}
