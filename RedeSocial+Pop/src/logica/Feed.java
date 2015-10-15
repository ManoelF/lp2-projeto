package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import logica.ordenacao.OrdenaFeedData;
import logica.ordenacao.OrdenaFeedPopularidade;

public class Feed  {

	private List<Post> feed;
	private Comparator<Post> tipoOrdenacao; 
	private List<Usuario> amigos;
	
	public Feed(List<Usuario> amigos) {
		this.feed = new ArrayList<>();
		this.tipoOrdenacao = new OrdenaFeedData();
 		this.amigos = amigos;
	}

	public void atualizaFeed() {
		
		for (Usuario amigo : this.amigos) {
			
			Collections.sort(amigo.getPostsToFeed());
			for (Post post : amigo.getPostsToFeed()) {
				
				if (!this.feed.contains(post)) {
					this.feed.add( post );
				}
			}		
		} // encerra for
		

		Collections.sort(this.feed, this.tipoOrdenacao);
	}
	
	public void ordenaPorData() {
		this.tipoOrdenacao = new OrdenaFeedData();
	}
	
	public void ordenaPorPopularidade() {
		this.tipoOrdenacao = new OrdenaFeedPopularidade();
	}
	
	public List<Post> getFeed() {
		return this.feed;
	}


}
