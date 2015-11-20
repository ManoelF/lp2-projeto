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
 * <b>SenhaProtegidaException</b> encapsulamento de erro referente a manter mais seguranca para as senhas do usuario.
 */
public class SenhaProtegidaException extends LogicaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constutor <b>SenhaProtegidaException</b>.
	 */
	public SenhaProtegidaException() {
		super("A senha dx usuarix eh protegida.");
	}
	
}
