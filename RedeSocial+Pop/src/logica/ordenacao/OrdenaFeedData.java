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
 * <code>OrdenaFeedData</code> define um objeto comparavel, ou seja, implementa a interface
 * <code>Comparator</code>, proporcionando uma comparacao dos <b>Feed</b> por a data de 
 * postagens dos Posts. 
 */

package logica.ordenacao;

import java.io.Serializable;
import java.util.Comparator;

import logica.Post;

public class OrdenaFeedData implements Comparator<Post>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2007398138192614198L;

	/**
	 * Usado o comparador default de <code>String</code>
	 * implementado por o proprio <b>Java</b>.
	 */
	@Override
	public int compare(Post postI, Post postII) {
 		return postI.getData().compareTo(postII.getData());
	}
}
