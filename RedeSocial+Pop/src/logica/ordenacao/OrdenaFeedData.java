package logica.ordenacao;

import java.util.Comparator;

import logica.Post;

public class OrdenaFeedData implements Comparator<Post> {

	@Override
	public int compare(Post postI, Post postII) {
 		return postI.getData().compareTo(postII.getData());
	}
}
