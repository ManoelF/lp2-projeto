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
 * <b>FechaSistemaException</b> encapsulamento de erro referente ao  salvar o atual estado do sistema.
 */
public class FechaSistemaException extends LogicaException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor <b>FechaSistemaException</b>.
	 */
	public FechaSistemaException() {
		super("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
	}
	
}
