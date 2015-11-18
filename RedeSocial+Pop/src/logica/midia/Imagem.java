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
