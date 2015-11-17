package logica.ordenacao;

import java.io.Serializable;
import java.util.Comparator;

import logica.Post;

public class OrdenaFeedData implements Comparator<Post>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2007398138192614198L;

	@Override
	public int compare(Post postI, Post postII) {
 		return postI.getData().compareTo(postII.getData());
	}
}
