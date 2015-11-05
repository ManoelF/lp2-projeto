package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TrendingTopics {
	
	/*
	 * Aqui será implementado a classe com a função de criar o trending topic, ou seja, o ranking das tres hashtags mais usadas
	 * 
	 * Inicialmente pensei em um contrutor que receberia a lista de usuários cadastrados e apartir dela fazer toda a buscas das
	 * hashtags usadas nos posts dos usuarios.
	 * 
	 * Estrutura para armazenar os dados, HashMap onde irá mapear hashtag-quantidade, que vai pegar todos os posts
	 * do usuario e adicionar a hashtag no mapa, e a medida que for iterando fará a verificação se essa hashtag está no
	 * dicionari se sim incrementa mais um na quantidade, se não acrescenta a hashtage e um na quantidade.
	 * 
	 * Por fim, fara uma busca procurando as hashtags que tem a maior quantidade. Tendo essa informação, usaremos o string Buffer
	 * para fazer a concatenação no formato desejado.
	 * 
	 */
	private HashMap<String, Integer> tagsUsadas;
	private List<String> trending;
	
	public TrendingTopics() {
		this.tagsUsadas = new HashMap<>();
		this.trending = new ArrayList<>();
	}
	
	
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
		this.trending = new ArrayList<>();
		buscaTagsMaisPopulares();
		String impressao = "Trending Topics:";
		
		for(int i = 0; i < trending.size(); i++) {
			impressao +=  " (" + (i+1) + ") " + trending.get(i);
		}
		return impressao;
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
}