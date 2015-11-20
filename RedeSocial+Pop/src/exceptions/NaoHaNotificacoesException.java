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
 * <b>NaoHaNotificacoesException</b> encapsulamento de erro referente a logica de cadastro de usuarios,
 * mais especificamente, suas excecoes.
 * .
 */
package exceptions;

public class NaoHaNotificacoesException extends LogicaException{
	

	private static final long serialVersionUID = 937678498710457554L;

	/**
	 * Construtor <b>NaoHaNotificacoesException</b>.
	 */
	public NaoHaNotificacoesException(){
		super("Nao ha mais notificacoes.");
	}
}
