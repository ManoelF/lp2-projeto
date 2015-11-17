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
