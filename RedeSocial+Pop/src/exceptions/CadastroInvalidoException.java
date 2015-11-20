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
 * <b>CadastroInvalidoException</b> encapsulamento de erro referente a logica de cadastro de usuarios.
 */
public class CadastroInvalidoException extends EntradaException  {

	private static final long serialVersionUID = -7311065454334396500L;

	/**
	 * Construtor <b>CadastroInvalidoException</b>.
	 */
	public CadastroInvalidoException(){
		super("Erro no cadastro de Usuarios.");
	}
	
	/**
	 * Construtor <b>CadastroInvalidoException</b>.
	 * 
	 * @param msg
	 *  		Mensagem relacionada a erro no cadastro de usuarios.
	 * 
	 */
	public CadastroInvalidoException(String msg){
		super(msg);
	}
	
	
	/*CadastroNomeException: " Nome dx usuarix nao pode ser vazio."
	 *CadastroSenhaException: " Senha dx usuarix nao pode ser vazio."
	 *CadastroEmailException: " Formato de e-mail esta invalido."
	 *CadastroDataInexistenteException(String msg): " Data nao existe."
	 *CadastroDataException: " Formato de data esta invalida."
	 * 
	 */
}
