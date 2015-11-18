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
