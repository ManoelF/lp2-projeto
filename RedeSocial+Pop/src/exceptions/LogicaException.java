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

package exceptions;

/**
 * <b>LogicaException</b> encapsulamento de erro referente toda a logica do sistema.
 */
public class LogicaException extends RedeSocialMaisPopException {


	private static final long serialVersionUID = -7965873739845709040L;

	/**
	 * Construtor <b>LogicaException</b>.
	 */
	public LogicaException() {
		super("Erro de execucao");
	}
	
	/**
	 *  Construtor <b>LogicaException</b>.
	 *  
	 * @param msg
	 * 			Mensagem de excecao.
	 */
	public LogicaException(String msg) {
		super(msg);
	}
	
}
