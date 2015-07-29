package logica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.*;

public class Post {

	private String texto;
	private String dataAtual;
	private int like;
	private int deslike;
	private int popularidade;
	private List<String> hashtags;

	// data e hora

	public Post(String texto) throws PostException {
		inicializaData();
		this.popularidade = 0;
		this.like = 0;
		this.deslike = 0;
		verificaTam(texto);
		this.hashtags = new ArrayList<>();
		guardaHashtag();
	}

	public void verificaTam(String texto) throws PostTamException {
		if (texto.length() > 400) {
			throw new PostTamException();
		} else {
			this.texto = texto;
		}
	}

	public void insereImagem(String imagem) {
		this.texto = this.texto + " " + imagem;
	}

	public void insereAudio(String audio) {
		this.texto = this.texto + " " + audio;
	}

	public void ganhaLike() {
		this.like = this.like + 1;
	}

	public void ganhaDeslike() {
		this.deslike = this.deslike + 1;
	}

	private void guardaHashtag() {
		
		String novaHashtag = "";
		
		for (int i = 0; i < (this.texto.length()); i++) {
			char c = texto.charAt(i); 
			String charToString = Character.toString(c); 
			
			if (charToString.equals(" ") ) {	// verifica se o item da iteracao eh uma string vazia
				if (!novaHashtag.equals("")) {  // se a hashtag atual nao for vazia (evita add strings vazias na lista de hash)
					this.hashtags.add(novaHashtag); // adiciona a hashtag na lista de hashtag 
					novaHashtag = "";				// e prepara uma nova hashtag
				}
			} else if (charToString.equals("#") || novaHashtag.length() >= 1 ) {  
				novaHashtag = novaHashtag + charToString;  // concatena itens para formar uma hashtag 
			}
		} // encerra loop
	}

	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getDeslike() {
		return deslike;
	}

	public void setDeslike(int deslike) {
		this.deslike = deslike;
	}

	public int getPopularidade() {
		return popularidade;
	}

	public void setPopularidade(int popularidade) {
		this.popularidade = popularidade;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}
	
	// Pegando a data no momento do post
	public void inicializaData() {
		Date date = new Date();  // Pega a data no instante atual
		SimpleDateFormat out = new SimpleDateFormat("dd/MM"); // Transforma no formato especificado no post dia/mes
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm"); // Transforma no formato especificado no post hora;minutos

		String result = out.format(date);   // Converte para String
		String novaHora = hora.format(date);// Converte para String

		this.dataAtual = result + " " + novaHora; // Deixa no formato especificado no post "dia/mes hora:minutos"
	}

}