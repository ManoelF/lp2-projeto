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
	private Util util;
	
	public Feed() {
		this.feed = new ArrayList<>();
		this.tipoOrdenacao = new OrdenaFeedData();
 		this.util = Util.getInstancia();
	}

	public List<Post> atualizaFeed(List<Usuario> amigos) {
		
		int conta;
		
		for (Usuario amigo : amigos) {
			
			Collections.sort(amigo.getPosts());
			Iterator iterator = amigo.getPosts().iterator();
			
			if (amigo.getPopularidade() instanceof Normal) {				
				conta = 0; 
				while (iterator.hasNext() && conta < 2) {
					if (!this.feed.contains(amigo.getPosts().get(conta))) {
						this.feed.add( amigo.getPosts().get(conta) );
						conta++;
					}
				}		
			} else if (amigo.getPopularidade() instanceof CelebridadePOP) {
				conta = 0; 
				while (iterator.hasNext() && conta < 4) {
					if (!this.feed.contains(amigo.getPosts().get(conta))) {
						this.feed.add( amigo.getPosts().get(conta) );
						conta++;
					}
				}
			} else { //(amigo.getPopularidade() instanceof IconePOP) {
				conta = 0; 
				while (iterator.hasNext() && conta < 6) {
					if (!this.feed.contains(amigo.getPosts().get(conta))) {
						this.feed.add( amigo.getPosts().get(conta) );
						conta++;
					}
				}
			} // encerra bloco de if
			
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


}
