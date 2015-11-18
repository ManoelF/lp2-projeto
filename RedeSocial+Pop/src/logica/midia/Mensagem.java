/**
 * <code>Mensagem</code> um tipo de <b>Midia</b>, usada para encapsular as mensagem dos posts. 
 * 
 */
package logica.midia;

import java.io.Serializable;

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

