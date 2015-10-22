package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class TrendingTopics {
	
	private HashMap<String, Integer> tagsUsadas;
	private List<String> trending;
	private TreeMap<Integer, List<String>> hashes;
	
	public TrendingTopics() {
		this.tagsUsadas = new HashMap<>();
		this.trending = new ArrayList<>();
		this.hashes = new TreeMap<>();
	}
	
/*	private void buscaTagsMaisPopulares() {
		
		for (int i = qntVezesMax(); i > 0; i--) {
			
			List<String> tagsMesmaQuant = new ArrayList<>();
			
			for (String tag : tagsUsadas.keySet()) {
				if (this.tagsUsadas.get(tag) == i) {
					tagsMesmaQuant.add(tag);
				}
			Collections.sort(tagsMesmaQuant);
			hashes.put(i, tagsMesmaQuant);
			} // fecha for interno
			
		} // fecha for externo

		
	}
	
	private int qntVezesMax() {
		int valorMax = 0;
		Set<String> hashtags = this.tagsUsadas.keySet();
		
		for (String tag: hashtags) {
			
			int quant = tagsUsadas.get(tag);
			if (quant > valorMax) {
				valorMax = quant;
			}
		}
		return valorMax;
	}

	private void pegaTresTags() {
		this.trending = new ArrayList<>();
						
		for (Integer value : this.hashes.keySet()) {
			if(this.trending.size() >= 3) {
				break;
			} else {
				this.trending.addAll(this.hashes.get(value));
			}
		}
		
	}*/
	
	private void buscaTagsMaisPopulares() {
		Set<String> hashtags = this.tagsUsadas.keySet();
		int cont = 0;
		
		while (cont < 3) {
			String maisUsada = "";
			int quantidade = 0;
			for (String tag: hashtags) {
				if  (this.tagsUsadas.get(tag) >= quantidade) {
					if (!trending.contains(tag)) {
						quantidade = this.tagsUsadas.get(tag);
						maisUsada = tag;
					}
				}
			}
			cont += 1;
			this.trending.add(maisUsada);
		}
	}
	
	public String atualizaTrendingTopic() {
		buscaTagsMaisPopulares();
		//pegaTresTags();
	
		return toString();
	}
	
	public void adicionaHashtag(List<String> hashtags) {
		for (String tag: hashtags) {
			if (this.tagsUsadas.containsKey(tag)) {
				tagsUsadas.put(tag, tagsUsadas.get(tag) + 1 );
			} else {
				tagsUsadas.put(tag, 1);
			}
		}
	}
	
	public String toString() {
		String impressao = "Trending Topics:";
		
		for(int i = 0; i < trending.size() || i < 3; i++) {
			impressao +=  " (" + (i+1) + ") " + trending.get(i);
		}
		return impressao;
	}
}
