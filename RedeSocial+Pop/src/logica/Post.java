package logica;

import java.time.LocalDateTime;
import java.util.List;

import exceptions.LogicaException;
import exceptions.PostException;
import logica.midia.Midia;

public class Post implements Comparable<Post> {

	private String texto;
	private LocalDateTime data;
	private int like;
	private int deslike;
	private int popularidade;
	private List<String> hashtags;
	private List<Midia> midias;
	private String autor;
	

	public Post(String texto, LocalDateTime data, List<String> hashtags, List<Midia> midias) throws PostException {

		this.texto = texto;
		this.popularidade = 0;
		this.like = 0;
		this.deslike = 0;
		this.hashtags = hashtags;
		this.midias = midias;
		this.data = data;
		this.autor = null;
		
	}

	public void curtir(int pontos) {
		this.like += 1;
		this.popularidade += pontos;
	}
	
	public void descurtir(int pontos) {
		this.deslike += 1;
		this.popularidade -= pontos;
	}
	
		
	public LocalDateTime getData() {
		return this.data;
	}
		
	public boolean comparaData(LocalDateTime outroData) {
		if (this.data.getMonth() != outroData.getMonth() &&
			this.data.getYear() != outroData.getYear() &&
			this.data.getDayOfMonth() != outroData.getDayOfMonth() ) {
				return false;
			}
		return true;		
	}
	
	public List<String> getHashtags() {
		return hashtags;
	}
	
	public void adicionaHashtag(String hashtag) {
		if (!hashtags.contains(hashtag)) {
			this.hashtags.add(hashtag);
			this.texto += " " + hashtag;
		}
	}
	 			
	public String getAutor(){
		return this.autor;
	}
	
	public void setAutor(String autorNome){
		this.autor = autorNome;
	}

	//Lançar excecao
	public String getPost(String atributo) {
		if (atributo.equals("Data")) {
			return getDataString();
		} else if (atributo.equals("Conteudo")) {
			return getConteudo();
		} else if (atributo.equals("Hashtags")) {
			return getHashtagsStr();
		} else if (atributo.equals("Mensagem")) {
			return getConteudo();
		} else {
			return "Aqui tem que lancar exception getPost(Atributo)";
		}
	}
	
	public String getPost() {
		return this.texto + " (" + getDataString() + ")";
	}
	
	private String getConteudo() {
		String novoConteudo = "";
		char[] novaMsg = this.texto.toCharArray();
		for (char caracter: novaMsg) {
			if (caracter == '#' ) {
				novoConteudo = novoConteudo.substring(0, novoConteudo.length() -1);
				break;
			}
			novoConteudo += caracter;
			
		}
		return novoConteudo;
	} 
	
	public String getConteudoPost(int indice) throws LogicaException, PostException {
		if (indice > this.midias.size() - 1) {
			throw new LogicaException("Item #" + indice + " nao existe nesse post, ele possui apenas " + this.midias.size() + " itens distintos.");
		} else if (indice < 0) {
			throw new PostException("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		} else {
			return this.midias.get(indice).toString();
		}
		
	}		
	
	public List<Midia> getMidias() {
		return this.midias;
	}
	
	public String getMidias(int indice) {
		return this.midias.get(indice).toString();
	}

	public String getDataString() {
		if (this.data.getSecond() == 0) {
			return this.data.toString().replace("T", " ") + ":00";
		}
		return this.data.toString().replace("T", " ");
	}
		
	private String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getLike() {
		return like;
	}

	private void setLike(int like) {
		this.like = like;
	}

	public int getDeslike() {
		return deslike;
	}

	private void setDeslike(int deslike) {
		this.deslike = deslike;
	}
	
	private void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}
		
	private String getHashtagsStr() {
		String hastags = "";
		int  cont = 0;
		for(String hash: this.hashtags) {
			cont += 1;
			hastags += hash;
			if (cont < this.hashtags.size()) {
				if (!hastags.equals("")) {
					hastags += ",";
				}
			}
		}
		return hastags;
	}
	
	public int getPopularidade() {
		return popularidade;
	}

	private void setPopularidade(int popularidade) {
		this.popularidade = popularidade;
	}
	
	@Override
	public int compareTo(Post outroPost) {
		return this.data.compareTo(outroPost.getData());
	}

}