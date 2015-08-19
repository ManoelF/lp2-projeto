package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.junit.internal.runners.statements.Fail;

import exceptions.*;

public class Post implements Comparable<Post>, Comparator<Post> {

	private String texto;
	private String dataAtual;
	private Date data;
	private int like;
	private int deslike;
	private int popularidade;
	private List<String> hashtags;
	private List<String> arquivos;

	// data e hora

	public Post(String texto, String data) throws PostException, ParseException {
		if (texto == null || texto.trim().length() == 0) {
			// lancar Exception
		}
		//verificaTam(texto);
		this.texto = texto;
		this.popularidade = 0;
		this.like = 0;
		this.deslike = 0;
		this.hashtags = new ArrayList<>();
		this.arquivos = new ArrayList<>();
		converteData(data);
		encontraMidia(texto);
		encontraHashtag(texto);
		
		if (getConteudo().length() > 200) {
			throw new PostException(" O limite maximo da mensagem sao 200 caracteres.");
		}
	}

	private void verificaTam(String texto) throws PostException {
		if (texto.length() > 200) {
			throw new PostException(" O limite maximo da mensagem sao 200 caracteres.");
		} else {
			this.texto = texto;
		}
	}

	
	public Date getData() {
		return this.data;
	}
	
	public String getArquivo(int indice) {
		return this.arquivos.get(indice);
	}
	
	public String getDataAtual() {
		return this.dataAtual;
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
	
	private String getConteudo() {
		String conteudo = "";
		char[] novaMsg = this.texto.toCharArray();
		for (char caracter: novaMsg) {
			if (caracter == '#' || caracter == '<' ) {
				break;
			}
			conteudo += caracter;
			
		}
		return conteudo;
	}
	
	
	private String getHashtagsStr() {
		String hastags = "";
		int  cont = 0;
		for(String hash: this.hashtags) {
			cont += 1;
			hastags += hash;
			if (cont < this.hashtags.size()) {
				hastags += ",";
			}
		}
		return hastags;
	}
	
	
	// tratando a data
	private void converteData(String novaData) throws ParseException {
		try {
		Date data1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(novaData);  // transforma o aquivo recebido para Date()
		
		this.dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data1);  // deixa no formato esperado nos arq de teses
		this.data = data1;
		} catch(ParseException e) {
			throw new ParseException("Erro aqui em", 0);
		}
	}
	
	// buscando arquivos de audio ou midia
	private void encontraMidia(String mensagem) {
		String tipoMidia = "$arquivo_";  			// variavel para concatenar o arquivo
		char[] novaMsg = mensagem.toCharArray();	// transformando a mensagem em lista de char para poder iterar
		boolean inicia = false; 					// variavel para controlar o momento de pegar os caraceres que interecam
		int cont = 0;								// contador para saber o momento de pegar o proximo arquivo
			
		for(char caracter: novaMsg) {				// onde inicia o arquivo
			if (caracter == '<') {
				inicia = true;
				cont += 1;							// um arquivo esta entre 2 '<'
				if (cont == 2) {					// deposi de dois '<' acaba o arquivo
					if ((tipoMidia.contains("audio") || tipoMidia.contains("imagem"))) {
						this.arquivos.add(tipoMidia);	// adiciona o arquivo ja encontrado na lista de arquivo
						inicia = false;					// espera o proximo '<' para poder comecar os passos para encotrar o proximo arquivo
					}
					tipoMidia = "$arquivo_";		// reinicia a variavel para adicionar o proximo arquivo
					cont = 1;
				}
			} else if (inicia) {
				if (caracter == '>') {				// no momento que encontra o '>' 
					tipoMidia += ":";				// adiciona ':' para o arquivo ficar no formato pedido
				} else {
					tipoMidia += caracter;			// forma o arquivo
				}
			}
		}
	}
	
	// buscando as hashtag do testo
	// logica semelhante a usada na busca de arquivos
	private void encontraHashtag(String mensagem) throws PostException {
		String novaHash = "";
		char[] novaMsg = mensagem.toCharArray();
		boolean espaco = false;
		boolean iniciaVerificacao = false;
		
		for(char caracter: novaMsg) {
			if (caracter == '#') {
				iniciaVerificacao = true;
				novaHash += caracter;
			} 
			
			if (iniciaVerificacao) {
				if (caracter == '#'){
					espaco = false;
				} else if (espaco) {
					throw new PostException("Hashtag invalida");
				} else if (caracter != ' ') {
					novaHash += caracter;
					
				} else if (caracter == ' ') {
					this.hashtags.add(novaHash);
					novaHash = "";
					espaco = true;
				}
			}
		}
		if (!novaHash.equals("")){
			this.hashtags.add(novaHash);
		}
	}
	
	public void curtir(int pontos) {
		this.like += 1;
		this.popularidade += pontos;
	}
	
	public void descurtir(int pontos) {
		this.deslike += 1;
		this.popularidade -= pontos;
	}

 	@Override
	public int compareTo(Post o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compare(Post o1, Post o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getPost(String atributo) {
		if (atributo.equals("Data")) {
			return this.dataAtual;
		} else if (atributo.equals("Conteudo")) {
			return getConteudo();
		} else if (atributo.equals("Hashtags")) {
			return getHashtagsStr();
		} else {
			return "Aqui tem que lancar exception getPost(Atributo)";
		}
	}
	
	public String getPost() {
		return this.texto + " (" + this.dataAtual + ")";
	}
}