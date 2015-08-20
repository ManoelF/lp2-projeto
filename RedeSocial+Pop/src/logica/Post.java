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
	private Date data;
	private String conteudo; 
	private int like;
	private int deslike;
	private int popularidade;
	private List<String> hashtags;
	private List<Midia> midias;

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
		this.midias = new ArrayList<>();
		buscaConteudo(texto);
		converteData(data);
		buscaMidia(texto);
		encontraHashtag(texto);
		buscaMidia(texto);
				
		if (getConteudo().length() > 200) {
			throw new PostTamException();
		}
	}

	private void verificaTam(String conteudo) throws PostTamException {
		if (conteudo.length() > 200) {
			throw new PostTamException();
		} 
	}

	
	public Date getData() {
		return this.data;
	}
	
	public String getMidias(int indice) {
		return this.midias.get(indice).getCaminho();
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
	
	public String getConteudo() {
		return this.conteudo;
	}
	
	private void buscaConteudo(String conteudo) {
		String novoConteudo = "";
		char[] novaMsg = this.texto.toCharArray();
		for (char caracter: novaMsg) {
			if (caracter == '#'  ) {
				break;
			}
			conteudo += caracter;
			
		}
		this.conteudo = novoConteudo;
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
	

	
	// buscando as hashtag do texto
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
	public int compareTo(Post outroPost) {
 		return this.data.compareTo(outroPost.getData());
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
			return "Aqui temM que lancar exception getPost(Atributo)";
		}
	}
	
	public String getPost() {
		return this.texto + " (" + this.dataAtual + ")";
	}
	
	
	private void buscaMidia(String mensagem) {
		FactoryMidia factoryMidia = new FactoryMidia();
		List<String> listMidias = Util.INSTANCIA.getMidia(mensagem);
		for (String arquivo: listMidias) {
			this.midias.add(factoryMidia.obtemMidias(arquivo));
		}
	}
	
}