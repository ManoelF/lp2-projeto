package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import exceptions.*;

public class Post implements Comparable<Post>, Comparator<Post> {

	private String texto;
	private String dataAtual;
	private int like;
	private int deslike;
	private int popularidade;
	private List<String> hashtags;
	private List<String> arquivos;

	// data e hora

	public Post(String texto, String data) throws PostException, ParseException {
		verificaTam(texto);
		this.popularidade = 0;
		this.like = 0;
		this.deslike = 0;
		this.hashtags = new ArrayList<>();
		this.arquivos = new ArrayList<>();
		converteData(data);
		encontraMidia(texto);
		encontraHashtag(texto);
	}

	public void verificaTam(String texto) throws PostTamException {
		if (texto.length() > 200) {
			throw new PostTamException();
		} else {
			this.texto = texto;
		}
	}

	public void ganhaLike() {
		this.like = this.like + 1;
	}

	public void ganhaDeslike() {
		this.deslike = this.deslike + 1;
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
	
	// tratando a data
	public void converteData(String novaData) throws ParseException {
		
		Date data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(novaData);  // transforma o aquivo recebido para Date()
		this.dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);  // deixa no formato esperado nos arq de teses
		System.out.println(dataAtual+ "\n");
	}
	
	// buscando arquivos de audio ou midia
	public void encontraMidia(String mensagem) {
		String tipoMidia = "$arquivo_";  			// variavel para concatenar o arquivo
		char[] novaMsg = mensagem.toCharArray();	// transformando a mensagem em lista de char para poder iterar
		boolean inicia = false; 					// variavel para controlar o momento de pegar os caraceres que interecam
		int cont = 0;								// contador para saber o momento de pegar o proximo arquivo
			
		for(char caracter: novaMsg) {				// onde inicia o arquivo
			if (caracter == '<') {
				cont += 1;							// um arquivo esta entre 2 '<'
				if (cont == 2) {					// deposi de dois '<' acaba o arquivo
					this.arquivos.add(tipoMidia);	// adiciona o arquivo ja encontrado na lista de arquivo
					tipoMidia = "$arquivo_";		// reinicia a variavel para adicionar o proximo arquivo
					inicia = false;					// espera o proximo '<' para poder comecar os passos para encotrar o proximo arquivo
					cont = 0;
				} else {
					inicia = true;
				}
			} else if (inicia) {
				if (caracter == '>') {				// no momento que encontra o '>' 
					tipoMidia += ":";				// adiciona ':' para o arquivo ficar no formato pedido
				} else {
					tipoMidia += caracter;			// forma o arquivo
				}
			}
		}
		if (tipoMidia.equals("")){	
			this.arquivos.add(tipoMidia); 			// apos o fim do loo, pode ser que haja um aquivo formado
		}											// e eh necessario adiciona-lo a lista de arquivos
	}
	
	// buscando as hashtag do testo
	// logica semelhante a usada na busca de arquivos
	public void encontraHashtag(String mensagem) {
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
					System.out.println("parou");
					break;
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

}