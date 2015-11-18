/**
 * Classe <code>Feed</code>, representado todas as postagens disponiveis dos amigos do Usuario.
 */
package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import logica.ordenacao.OrdenaFeedData;
import logica.ordenacao.OrdenaFeedPopularidade;

public class Feed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7022843056221429993L;
	private List<Post> feed;
	private Comparator<Post> tipoOrdenacao; 
	private List<Usuario> amigos;
	
	/**
	 * Contrutor de <b>Feed</b>.
	 * 
	 * @param amigos
	 * 			Lista de Usarios, sendo os amigos do Usuario, para dar acesso as <b>Post</b>
	 * 			para construcao do Feed de noticias.
	 */
	public Feed(List<Usuario> amigos) {
		this.feed = new ArrayList<>();
		this.tipoOrdenacao = new OrdenaFeedData();
 		this.amigos = amigos;
	}

	/**
	 * Atualizacao do <b>Feed</b>, populando com os <b>Post</b> dos amigos.
	 */
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
	
	/**
	 * Ordenacao dos <b>Post</b> do Feed de acordo com 
	 * suas datas de criacao.
	 */
	public void ordenaPorData() {
		this.tipoOrdenacao = new OrdenaFeedData();
	}
	
	/**
	 * Ordenacao dos <b>Post</b> do Feed de acordo com 
	 * suas respectivas popularidades.
	 */
	public void ordenaPorPopularidade() {
		this.tipoOrdenacao = new OrdenaFeedPopularidade();
	}
	
	/**
	 * Retorno da lista de posts que caracteriza o feed.
	 * @return
	 */
	public List<Post> getFeed() {
		return this.feed;
	}


}
