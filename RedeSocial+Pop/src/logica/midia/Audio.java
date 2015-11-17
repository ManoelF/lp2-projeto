package logica.midia;

import java.io.Serializable;

public class Audio extends Midia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8046803128908154082L;

	public Audio(String caminho) {
		super.valor = caminho;
	}
}
