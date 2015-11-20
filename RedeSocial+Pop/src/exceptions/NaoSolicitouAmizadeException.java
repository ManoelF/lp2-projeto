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
 * <b>NaoSolicitouAmizadeException</b> encapsulamento de erro referente a logica de acoes dos usuarios.
 */
public class NaoSolicitouAmizadeException extends LogicaException {

	private static final long serialVersionUID = -7545120070145900796L;

	/**
	 * Construtor NaoSolicitouAmizadeException.
	 * 
	 * @param msg
	 * 			Mensagem referente a excecao de solicitacao de amizade.
	 */
	public NaoSolicitouAmizadeException(String msg) {
		super(msg);
		// nomeUsuario + " nao lhe enviou solicitacoes de amizade."
	}
	
}