package logica;

import java.util.ArrayList;
import java.util.Collections;
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
	
	private List<Tag> trending;
	private List<Tag> tagsNaRede;
	
	public TrendingTopics() {
		this.trending = new ArrayList<>();
		this.tagsNaRede = new ArrayList<>();
	}
	
	
	private void buscaTagsMaisPopulares() {		
		Collections.sort(this.tagsNaRede);
		for (int i = 0; i < this.tagsNaRede.size(); i++) {
			System.out.println(this.tagsNaRede.get(i).toString() + "--" + this.tagsNaRede.get(i).getFrequencia());
		}
		System.out.println();
		
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
				//System.out.println("nova hash"+ tag.getHashtag()+ "::::"+ tag.getFrequencia());
				this.tagsNaRede.add(tag);
			}
		}
	}
	
	public void adicionaTag(String hashtag) {
		Tag tag = new Tag(hashtag);
		
		if (this.tagsNaRede.contains(tag)) {
			int indice = this.tagsNaRede.indexOf(tag);
			this.tagsNaRede.get(indice).addFrequencia();
			//System.out.println("nova hash"+ tag.getHashtag()+ "::::"+ this.tagsNaRede.get(indice).getFrequencia());
		} else {
			this.tagsNaRede.add(tag);
			//System.out.println("nova hash"+ tag.getHashtag()+ "::::"+ tag.getFrequencia());
		}
	}
	
	
	
	
}