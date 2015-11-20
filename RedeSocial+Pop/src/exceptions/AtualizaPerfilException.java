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
 * <b>AtualizaPerfilException</b> encapsulamento de erro referente a atualizacao do perfil de usuarios.
 */

package exceptions;

public class AtualizaPerfilException extends EntradaException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor <b>AtualizaPerfilException</b>.
	 */
	public AtualizaPerfilException() {
		super("Erro na atualizacao de perfil. Nenhum usuarix esta logadx no +pop.");
	}

	/**
	 * Construtor <b>AtualizaPerfilException</b>.
	 * 
	 * @param msg
	 * 			Mensagem de excecao ao atualizar dados do perfil do Usuario.
	 */
	public AtualizaPerfilException(String msg) {
		super(msg);
	}
	
	/*AtualizaDataInexistenteException: " Data nao existe."
	 *AtualizaDataInvalidaException: " Formato de data esta invalida."
	 *AtualizaEmailException: " Formato de e-mail esta invalido."
	 *AtualizaNomeException: " Nome dx usuarix nao pode ser vazio."
	 *AtualizaSenhaIncorretaException: " A senha fornecida esta incorreta."
	 *AtualizaSenhaInvalidaException: " Senha invalida."
	 * 
	 * 
	 */
	
}
