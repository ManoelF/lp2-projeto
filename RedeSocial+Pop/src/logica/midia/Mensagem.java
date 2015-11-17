package logica.midia;

import java.io.Serializable;

public class Mensagem extends Midia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5356710372415930186L;

	public Mensagem(String texto) {
		super.valor = texto;
	}
	
}

