package logica.midia;

import java.io.Serializable;

public class Imagem extends Midia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4587770255715335604L;

	public Imagem(String caminho) {
		super.valor = caminho;
	}
}
