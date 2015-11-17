package logica.ordenacao;

import java.io.Serializable;
import java.util.Comparator;

import logica.Post;

public class OrdenaFeedPopularidade implements Comparator<Post>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3219440233991658239L;

	@Override
	public int compare(Post postI, Post postII) {
		if (postI.getPopularidade() > postII.getPopularidade()) {
			return -1;
		} else if (postI.getPopularidade() < postII.getPopularidade()) {
			return 1;
		} else {
			return 0;
		}
	}
}
