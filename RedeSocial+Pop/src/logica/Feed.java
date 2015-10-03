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

	public List<Post> atualizaFeed() {
		
		int conta;
		
		for (Usuario amigo : this.amigos) {
			
			Collections.sort(amigo.getPosts());
			Iterator<Post> iterator = amigo.getPosts().iterator();
			int qnt = amigo.qntPostsFeed();
			
			conta = 0; 
			while (iterator.hasNext() && conta < qnt) {
				if (!this.feed.contains(amigo.getPosts().get(conta))) {
					this.feed.add( amigo.getPosts().get(conta) );
					conta++;
				}
			}		
		} // encerra for
		

		Collections.sort(this.feed, this.tipoOrdenacao);
		return feed;
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
