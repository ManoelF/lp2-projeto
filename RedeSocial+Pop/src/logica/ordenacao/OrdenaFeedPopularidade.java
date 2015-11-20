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

package logica.ordenacao;

import java.io.Serializable;
import java.util.Comparator;

import logica.Post;

/**
 * <code>OrdenaFeedPopularidade</code> define um objeto comparavel, ou seja, implementa a interface
 * <code>Comparator</code>, proporcionando uma comparacao dos <b>Feed</b> por a popularidade  
 * dos Posts. 
 */
public class OrdenaFeedPopularidade implements Comparator<Post>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3219440233991658239L;
	/**
	 * Sobrescrita do metodo para proporcionar
	 * uma comparacao de acordo com a popularidade
	 * de cada <b>Post</b>.
	 */
	@Override
	public int compare(Post postI, Post postII) {
		if (postI.getPopularidade() > postII.getPopularidade()) {
			return -1;
		} else if (postI.getPopularidade() < postII.getPopularidade()) {
			return 1;
		} else {
			return 0;
		}
	}
}
