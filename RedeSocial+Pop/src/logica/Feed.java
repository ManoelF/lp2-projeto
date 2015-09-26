package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import logica.ordenacao.OrdenaFeedData;
import logica.ordenacao.OrdenaFeedPopularidade;
import logica.tipopopularidade.CelebridadePOP;
import logica.tipopopularidade.Normal;

public class Feed  {

	private List<Post> feed;
	private Comparator<Post> tipoOrdenacao; 
	private Util util;
	private List<Usuario> amigos;
	//private List<Usuario> amigos;
	
	public Feed(List<Usuario> amigos) {
		this.feed = new ArrayList<>();
		this.tipoOrdenacao = new OrdenaFeedData();
 		this.util = Util.getInstancia();
 		this.amigos = amigos;
	}

	public List<Post> atualizaFeed() {
		
		int conta;
		
		for (Usuario amigo : this.amigos) {
			
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
	
	public List<Post> getFeed() {
		return this.feed;
	}


}
