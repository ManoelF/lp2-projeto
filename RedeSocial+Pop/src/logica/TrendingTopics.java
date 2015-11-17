/**
 * Classe <code>TendingTopics</code> responsavel por gerencias todas as hashtags do sistema
 * oferecendo assim, um ranking da treis mais frequentes.
 */
package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrendingTopics implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2284722535015707028L;

	private final int TRENDING = 3;
		
	private List<Tag> trending;
	private List<Tag> tagsNaRede;
	
	/**
	 * Contrutor Trending.
	 */
	public TrendingTopics() {
		this.trending = new ArrayList<>();
		this.tagsNaRede = new ArrayList<>();
	}
	
	/**
	 * Busca na lista de Tags do sistema as 3 mais frequentes.
	 */
	private void buscaTagsMaisPopulares() {		
		Collections.sort(this.tagsNaRede);
		
		for (int i = 0; i < tagsNaRede.size() && i < TRENDING ; i++) {
			this.trending.add(this.tagsNaRede.get(i));
		}
	}
	
	
	/**
	 * Atualiza as tres Tags mais populares e imprime 
	 * as mesmas.
	 * 
	 * @return String
	 * 		Tres Tags mais populares na rede.
	 */
	public String atualizaTrendingTopic() {
		this.trending = new ArrayList<>();
		buscaTagsMaisPopulares();
		String impressao = "Trending Topics: ";
		
		for(int i = 0; i < trending.size(); i++) {
			impressao +=  " (" + (i+1) + ") " + trending.get(i).toString();
		}
		return impressao;
	}
	/**
	 * Adiciona uma Tag na lista de Tags do Sistema.
	 * 
	 * @param tags
	 * 			Tag a ser adicionada.
	 */
	public void adicionaTag(List<Tag> tags) {
		
		for (Tag tag : tags) {
			if (this.tagsNaRede.contains(tag)) {
				int indice = this.tagsNaRede.indexOf(tag);
				this.tagsNaRede.get(indice).addFrequencia();
			} else {
				this.tagsNaRede.add(tag);
			}
		}
	}
	
	/**
	 * Adiciona as Tags epicWin e epicFail ao sistema
	 * 
	 * @param hashtag
	 * 			Tag a ser adicionada.
	 */
	public void adicionaTag(String hashtag) {
		Tag tag = new Tag(hashtag);
		
		if (this.tagsNaRede.contains(tag)) {
			int indice = this.tagsNaRede.indexOf(tag);
			this.tagsNaRede.get(indice).addFrequencia();
		} else {
			this.tagsNaRede.add(tag);
		}
	}
	
	
	
	
}