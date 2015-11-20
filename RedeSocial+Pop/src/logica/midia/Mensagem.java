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
 * <code>Mensagem</code> um tipo de <b>Midia</b>, usada para encapsular as mensagem dos posts. 
 * 
 */
public class Mensagem extends Midia implements Serializable {
	
	
	private static final long serialVersionUID = -5356710372415930186L;

	/**
	 * Construtor.
	 * 
	 * @param texto
	 * 			Texto do post.
	 */
	public Mensagem(String texto) {
		super.valor = texto;
	}
	
}

