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

package logica.midia;

import java.io.Serializable;

/**
 * <code>Audio</code> um tipo de <b>Midia</b>, usada para encapsular os audios contidos
 * nos posts.
 */
public class Audio extends Midia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8046803128908154082L;
	
	/**
	 * Construtor.
	 * 
	 * @param caminho
	 * 			Diretorio do Audio.
	 */
	public Audio(String caminho) {
		super.valor = caminho;
	}
}
