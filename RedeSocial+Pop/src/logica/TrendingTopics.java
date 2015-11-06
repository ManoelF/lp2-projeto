package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TrendingTopics {
		
	private List<Tag> trending;
	private List<Tag> tagsNaRede;
	
	public TrendingTopics() {
		this.trending = new ArrayList<>();
		this.tagsNaRede = new ArrayList<>();
	}
	
	
	private void buscaTagsMaisPopulares() {		
		Collections.sort(this.tagsNaRede);
		
		for (int i = 0; i < 3; i++) {
			this.trending.add(this.tagsNaRede.get(i));
		}
	}
	
	
	
	public String atualizaTrendingTopic() {
		this.trending = new ArrayList<>();
		buscaTagsMaisPopulares();
		String impressao = "Trending Topics: ";
		
		for(int i = 0; i < trending.size(); i++) {
			impressao +=  " (" + (i+1) + ") " + trending.get(i).toString();
		}
		return impressao;
	}
		
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