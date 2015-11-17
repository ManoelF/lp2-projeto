package logica.midia;

import java.io.Serializable;

public abstract class Midia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8746460556487106839L;
	protected String valor;
	
	public String toString() {
		return this.valor;
	}
	
}
