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
 * <code>Interface</code> que define os diferentes tipos de <code>Usuario</code>.
 */
package logica.tipopopularidade;

import java.io.Serializable;

import logica.Post;

public interface TipoPopularidade extends Serializable {
	
	/**
	 * Curtir Post de um Usuario.
	 * 
	 * @param post
	 * 			Post a ser curtido.
	 */
	public void curtir(Post post);
	
	/**
	 * Descurtir Post de um Usuario.
	 * 
	 * @param post
	 * 			Post a ser descurtido.
	 */
	public void descurtir(Post post);
	
	/*
	 * Quantidade de Posts que o Usuario disponibliza
	 * para popular o Feed de seus amigos.
	 */
	public int qntPostFeed();
	
	/**
	 * Popularidade do Usuario.
	 * 
	 * @return	String
	 * 			Popularidade do Usuario.
	 */
	public String popularidade();
	
}
