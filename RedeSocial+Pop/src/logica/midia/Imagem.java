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
 * <code>Imagem</code> um tipo de <b>Midia</b>, usada para encapsular as imagens contidas
 * nos posts.
 */

package logica.midia;

import java.io.Serializable;

public class Imagem extends Midia implements Serializable {
	
	private static final long serialVersionUID = 4587770255715335604L;

	/**
	 * Construtor de Imagem
	 * 
	 * @param caminho
	 * 			Diretorio da imagem.
	 */
	public Imagem(String caminho) {
		super.valor = caminho;
	}
}
