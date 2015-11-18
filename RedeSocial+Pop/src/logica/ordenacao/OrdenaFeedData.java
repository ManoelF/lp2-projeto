/**
 * <code>OrdenaFeedData</code> define um objeto comparavel, ou seja, implementa a interface
 * <code>Comparator</code>, proporcionando uma comparacao dos <b>Feed</b> por a data de 
 * postagens dos Posts. 
 */

package logica.ordenacao;

import java.io.Serializable;
import java.util.Comparator;

import logica.Post;

public class OrdenaFeedData implements Comparator<Post>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2007398138192614198L;

	/**
	 * Usado o comparador default de <code>String</code>
	 * implementado por o proprio <b>Java</b>.
	 */
	@Override
	public int compare(Post postI, Post postII) {
 		return postI.getData().compareTo(postII.getData());
	}
}
