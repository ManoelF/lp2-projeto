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
 * <b>IniciaSistemaException</b> encapsulamento de erro referente a restauracao do estado do sistema.
 */

package exceptions;

public class IniciaSistemaException extends LogicaException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor <b>IniciaSistemaException</b>.
	 */
	public IniciaSistemaException() {
		super("Nao foi possivel iniciar o sistema");
	}
	
	/**
	 * Construtor <b>IniciaSistemaException</b>.
	 * 
	 * @param msg
	 * 			Mensagem referente a excecao ocorrida.
	 */
	public IniciaSistemaException(String msg) {
		super(msg);
	}
	
	
	
}
