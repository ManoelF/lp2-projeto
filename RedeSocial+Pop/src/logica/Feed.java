/* =========================== Rede Social +Pop ================================= #
 *                                                                                *
 * Projeto obrigatorio para cumprimento de nota da disciplina Programação II      * 	  
 * e Laboratorio de Programacao II.                                               *
 *                                                                                *
 * Departamento de Informatica e Engenharia Eletrica                              *
 * Curso Ciência da Computação (UFCG - 2015.1)                                    *
 * Laboratorio de Programação II                                                  *
 *                                                                                *
 * Discentes envolvidos:                                                          *
 *          Italo Batista                                                         *
 *          Jose Manoel Ferreira                                                  *
 *          Kerilin Chang.                                                        *
 *                                                                                *
 * Orientador:                                                                    *
 *          Francisco Neto.                                                       *
 *                                                                                *
 * ============================================================================== #
 */

package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import logica.ordenacao.OrdenaFeedData;
import logica.ordenacao.OrdenaFeedPopularidade;


/**
 * Classe <code>Feed</code>, representado todas as postagens disponiveis dos amigos do Usuario.
 */
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
	 * 
	 * @return List
	 * 		Lista de post para o feed.
	 */
	public List<Post> getFeed() {
		return this.feed;
	}


}
