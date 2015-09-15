package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Feed  {

	private List<Post> feed;
	
	public Feed() {
		this.feed = new ArrayList<>();
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
						addOrdenado( amigo.getPosts().get(conta) );
						conta++;
					}
				}		
			} else if (amigo.getPopularidade() instanceof CelebridadePOP) {
				conta = 0; 
				while (iterator.hasNext() && conta < 4) {
					if (!this.feed.contains(amigo.getPosts().get(conta))) {
						addOrdenado( amigo.getPosts().get(conta) );
						conta++;
					}
				}
			} else { //(amigo.getPopularidade() instanceof IconePOP) {
				conta = 0; 
				while (iterator.hasNext() && conta < 6) {
					if (!this.feed.contains(amigo.getPosts().get(conta))) {
						addOrdenado( amigo.getPosts().get(conta) );
						conta++;
					}
				}
			} // encerra bloco de if
			
		} // encerra for


		return feed;
	}
	
	private void addOrdenado(Post post) {
		// por hora este metodo apenas add. mas futuramente sera implementado para add ordenado
		this.feed.add(post);
	}
	


}
