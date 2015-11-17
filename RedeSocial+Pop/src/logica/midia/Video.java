package logica.midia;

import java.io.Serializable;

public class Video extends Midia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 572249660782326755L;

	public Video(String caminho) {
		super.valor = caminho;
	}

}
