/**
 * Classe <code>Midia</code> usada para representar/encapsular um tipo especifico de mida, seja <b>Audio</b>, 
 * <b>Mensagem</b>, <b>Imagem</b> ou <b>Video</b>. Essa classe tem como objetivo facilitar a insercao de 
 * novas midias na <i>Rede Social +POP</i>.
 */



package logica.midia;

import java.io.Serializable;

public abstract class Midia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8746460556487106839L;
	/**
	 * Informacao da Midia.
	 */
	protected String valor;
	
	@Override
	public String toString() {
		return this.valor;
	}
	
}
