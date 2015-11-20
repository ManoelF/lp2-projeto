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
 * <code>Video</code> abstracao da midia postada por usuarios.
 */
package logica.midia;

import java.io.Serializable;

public class Video extends Midia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 572249660782326755L;

	/**
	 * Construtor de <code>Video</code>.
	 * 
	 * @param caminho
	 * 			Diretorio do video.
	 */
	public Video(String caminho) {
		super.valor = caminho;
	}

}
