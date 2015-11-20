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


package logica.tipopopularidade;

import logica.Post;

/**
 * Classe <code>IconePOP</code> define um tipo de popularidade para um <code>Usuairo</code>.
 * Sendo assim, esta classe impementa a interface {@link TipoPopularidade}.
 */
public class IconePOP implements TipoPopularidade {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7952544835516036447L;
	private final int POPS = 50;
	private final int qntPostFeed = 6;
	
	/**
	 * Curtir o Post de um Usuario.
	 * Passando 25 POPS correspondente a valor de pops
	 * ofertado por um Usuario do tipo <code>IconePOP</code>
	 * curtir o Post.
	 * 
	 * @param post
	 * 			Post a ser curtido.
	 */
	@Override
	public void curtir(Post post) {
		post.curtir(POPS);
		post.adicionaHashtag("#epicwin");
	}
	
	/**
	 * Descurtir o Post de um Usuario.
	 * Passando -25 POPS correspondente a valor de pops
	 * ofertado por um Usuario do tipo <code>IconePOP</code>
	 * descurtir o Post.
	 * 
	 * @param post
	 * 			Post a ser descurtido.
	 */
	@Override
	public void descurtir(Post post) {
		post.descurtir(POPS);
		post.adicionaHashtag("#epicfail");
	}
	
	/**
	 * Quantidade de Posts que o usuario disponibiliza
	 * para popular o Feed de seus amigos.
	 */
	@Override
	public int qntPostFeed() {
		return this.qntPostFeed;
	}
	
	/**
	 * Popularidade do Usuario.
	 */
	@Override
	public String popularidade() {
		return "Icone Pop";
	}
}
