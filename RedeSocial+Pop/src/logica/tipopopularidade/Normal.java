/**
 * Classe <code>Normal</code> define um tipo de popularidade para um <code>Usuairo</code>.
 * Sendo assim, esta classe impementa a interface {@link TipoPopularidade}.
 */

package logica.tipopopularidade;

import logica.Post;


public class Normal implements TipoPopularidade  {
	private final int POPS = 10;
	private final int qntPostFeed = 2;
	
	/**
	 * Curtir o Post de um Usuario.
	 * Passando 25 POPS correspondente a valor de pops
	 * ofertado por um Usuario do tipo <code>Normal</code>
	 * curtir o Post.
	 * 
	 * @param Post
	 * 			Post a ser curtido.
	 */
	@Override
	public void curtir(Post post) {
		post.curtir(POPS);
	}
	
	/**
	 * Descurtir o Post de um Usuario.
	 * Passando -25 POPS correspondente a valor de pops
	 * ofertado por um Usuario do tipo <code>Normal</code>
	 * descurtir o Post.
	 * 
	 * @param Post
	 * 			Post a ser descurtido.
	 */
	@Override
	public void descurtir(Post post) {
		post.descurtir(POPS);
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
		return "Normal Pop";
	}

}
