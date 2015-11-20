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
 * Classe <code>Midia</code> usada para representar/encapsular um tipo especifico de mida, seja <b>Audio</b>, 
 * <b>Mensagem</b>, <b>Imagem</b> ou <b>Video</b>. Essa classe tem como objetivo facilitar a insercao de 
 * novas midias na <i>Rede Social +POP</i>.
 */
package logica.midia;

import java.io.Serializable;

public abstract class Midia implements Serializable {
	

	private static final long serialVersionUID = -8746460556487106839L;
	/**
	 * Informacao da Midia.
	 */
	protected String valor;
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.valor;
	}
	
}
