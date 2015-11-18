/**
 * <code>Audio</code> um tipo de <b>Midia</b>, usada para encapsular os audios contidos
 * nos posts.
 */
package logica.midia;

import java.io.Serializable;

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
