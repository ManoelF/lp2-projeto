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

package logica;

import java.io.Serializable;

/**
 * Classe <code>Tag</code> representa a hashtag que esta contida no Post, podendo ter uma ou mais(diferentes). 
 */
public class Tag implements Comparable<Tag>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2483278780011113971L;
	private int frequencia;
	private String hashtag; 
	
	/**
	 * Construtor <b>Tag</b>.
	 * 
	 * @param hashtag
	 * 		String que identifica a Tag.
	 */
	public Tag(String hashtag) {
		this.hashtag = hashtag;
		this.frequencia = 1;
	}
	
	/**
	 * Hashtag
	 *  
	 * @return String
	 * 			Nome da hashtag		
	 */
	public String getHashtag() {
		return this.hashtag;
	}
	
	/**
	 * Frequencia com que a <b>Tag</b> aparece no sistema.
	 * 
	 * @return Int
	 * 			Ferequencia.
	 */
	public int getFrequencia() {
		return this.frequencia;
	}
	
	/**
	 * Incrementa na variavel frequencia cada vez que uma 
	 * Tag eh postada.
	 */
	public void addFrequencia() {
		this.frequencia += 1;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		result = prime * result + ((hashtag == null) ? 0 : hashtag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tag)) {
			return false;
		} else {	
			Tag other = (Tag) obj;
			if (hashtag.equals(other.getHashtag())) {
				return true;
			} else {
				return false;
			}
		}

	}

	public int compareTo(Tag outraTag) {
		
		if (this.frequencia > outraTag.getFrequencia()) {
			return -1;
		} else if (this.frequencia == outraTag.getFrequencia()) {
			return this.hashtag.compareTo(outraTag.getHashtag()) * -1;
		} else { //this.frequencia < outraTag.getFrequencia()
			return 1;
		}		
	}
	
	public String toString() {
		return this.hashtag+ ": "+ this.frequencia+ ";";
	}
}
