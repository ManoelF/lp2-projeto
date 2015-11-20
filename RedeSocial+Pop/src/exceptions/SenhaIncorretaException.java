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
 * <b>SenhaIncorretaException</b> encapsulamento de erro referente a manipulacao de senha do usuario.
 */
public class SenhaIncorretaException extends LoginException {

	private static final long serialVersionUID = 2974901947266082919L;
	
	/** 
	 * Construtor <b>SenhaIncorretaException</b>.
	 */
	public SenhaIncorretaException() {
		super("Senha invalida.");
	}
}
