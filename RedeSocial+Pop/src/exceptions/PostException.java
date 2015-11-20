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
 * <b>PostException</b> encapsulamento de erro referente ao Post.
 */
package exceptions;

public class PostException extends EntradaException {

	private static final long serialVersionUID = 1L;


	/**
	 * Construtor <b>PostException</b>.
	 */
	public PostException() {
		super("Nao eh possivel criar o post.");
	}
	

	/**
	 * Construtor <b>PostException</b>.
	 * 
	 * @param msg
	 * 		Mensagem de excecao relacionada ao Post.
	 */
	public PostException(String msg) {
		super(msg);
	}
	
	/*PostTamException: " O limite maximo da mensagem sao 200 caracteres."
	 *PostHashtagException: " As hashtags devem comecar com '#'.")
	 *PostItemInexistenteException(int item): "Item #"+ item +" nao existe nesse post, ele possui apenas 3 itens distintos."
	 *PostItemInvalidoException: "Requisicao invalida. O indice deve ser maior ou igual a zero."
	 */
}
