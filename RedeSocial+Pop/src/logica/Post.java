package logica;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.sound.sampled.Line;

import exceptions.LogicaException;
import exceptions.PostException;
import logica.midia.Midia;

public class Post implements Comparable<Post>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5112986287987768989L;
	private String texto;
	private LocalDateTime data;
	private int like;
	private int deslike;
	private int popularidade;
	private List<String> hashtags;
	private List<Midia> midias;
	private String autor;
	
	public Post(String texto, LocalDateTime data, List<String> hashtags, List<Midia> midias) {

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
	public String getPost(String atributo) throws LogicaException {
		if (atributo.equals("Data")) {
			return getDataString();
		} else if (atributo.equals("Conteudo")) {
			return getConteudo();
		} else if (atributo.equals("Hashtags")) {
			return getHashtagsStr();
		} else if (atributo.equals("Mensagem")) {
			return getConteudo();
		} else {
			throw new LogicaException("Informacao indisponivel");
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
		

	public int getLike() {
		return this.like;
	}
 
	public int getDeslike() {
		return this.deslike;
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
		return this.popularidade;
	}

	@Override
	public int compareTo(Post outroPost) {
		return this.data.compareTo(outroPost.getData());
	}
	
	
	public String toString(int indice) {
		StringBuffer postFormatado = new StringBuffer();
		String novaLinha = System.lineSeparator();
		postFormatado.append("Post #" + indice +  " " + getDataString() + novaLinha);
		postFormatado.append("Conteudo:" + novaLinha);
		
		for (int i = 0; i < this.midias.size(); i++) {
			postFormatado.append(midias.get(i).toString() + novaLinha);
		}
		
		postFormatado.append(getHashtagsStr() + novaLinha);
		postFormatado.append("+Pop: " + this.popularidade);
		postFormatado.append(novaLinha);
		postFormatado.append(novaLinha);
		
		return postFormatado.toString();
		
	}

}