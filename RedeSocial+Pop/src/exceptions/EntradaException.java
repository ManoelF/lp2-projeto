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
 * <b>EntradaException</b> encapsulamento de erro referente a entrada de dados, pelo usuario, dos metodos de todo o sistema.
 */
package exceptions;

public class EntradaException extends RedeSocialMaisPopException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor <b>EntradaException</b>.
	 */
	public EntradaException () {
		super("Campo obrigatorio nao eh valido.");
	}

	/**
	 * Construtor <b>EntradaException</b>.
	 * 
	 * @param msg
	 * 			Messagem da excecao lancada ao inserir um dado incorreto.
	 */
	public EntradaException (String msg) {
		super(msg);
	}
	
}
