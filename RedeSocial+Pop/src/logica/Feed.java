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
		
		List<Post> listaNormal = new ArrayList<Post>();
		List<Post> listaCelebridade = new ArrayList<Post>();
		List<Post> listaIcone = new ArrayList<Post>();
		int conta;
		
		for (Usuario amigo : amigos) {
			
			Collections.sort(amigo.getPosts());
			Iterator iterator = amigo.getPosts().iterator();
			
			if (amigo.getPopularidade() instanceof Normal) {				
				conta = 0; 
				while (iterator.hasNext() && conta < 2) {
					listaNormal.add( amigo.getPosts().get(conta) );
					conta++;
				}		
			} else if (amigo.getPopularidade() instanceof CelebridadePOP) {
				conta = 0; 
				while (iterator.hasNext() && conta < 4) {
					listaCelebridade.add( amigo.getPosts().get(conta) );
					conta++;
				}
			} else { //(amigo.getPopularidade() instanceof IconePOP) {
				conta = 0; 
				while (iterator.hasNext() && conta < 6) {
					listaIcone.add( amigo.getPosts().get(conta) );
					conta++;
				}
			} // encerra bloco de if
		} // encerra for

		List<Post> novoFeed =  concatenaListas(listaNormal, listaCelebridade, listaIcone);
		return novoFeed;
	}
	
	public List<Post> concatenaListas(List<Post> listaNormal, List<Post> listaCelebridade, List<Post> listaIcone) {
	
		Collections.sort(listaNormal);
		Collections.sort(listaCelebridade);
		Collections.sort(listaIcone);
		
		List<Post> novoFeed = new ArrayList<Post>();
		novoFeed.addAll(listaIcone);
		novoFeed.addAll(listaCelebridade);
		novoFeed.addAll(listaNormal);
		return novoFeed;
	}

}
