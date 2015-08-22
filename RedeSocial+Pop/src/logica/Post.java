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
		this.hashtags = Util.getInstancia().encontraHashtag(texto);
		this.midias = new ArrayList<>();
		buscaConteudo(texto);
		converteData(data);
		buscaMidia(texto);
		buscaMidia(texto);
		verificaTam(texto);
				
		
	}

	private void verificaTam(String texto) throws PostException {
		String novoTexto = Util.getInstancia().encontraTexto(texto);
		if (novoTexto.length() >= 200) {
			throw new PostException(" O limite maximo da mensagem sao 200 caracteres.");
		} else {
			this.texto = texto;
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
			if (caracter == '#' ) {
				novoConteudo = novoConteudo.substring(0, novoConteudo.length() -1);
				break;
			}
			novoConteudo += caracter;
			
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
			return "Aqui tem que lancar exception getPost(Atributo)";
		}
	}
	
	public String getPost() {
		return this.texto + " (" + this.dataAtual + ")";
	}
	
	
	private void buscaMidia(String mensagem) {
		FactoryMidia factoryMidia = new FactoryMidia();
		List<String> listMidias = Util.getInstancia().getMidia(mensagem);
		System.out.println(Util.getInstancia().encontraTexto(mensagem) + "-->");
		Midia mensagem2 = new Mensagem(Util.getInstancia().encontraTexto(mensagem));
		this.midias.add(mensagem2);
		for (String arquivo: listMidias) {
			this.midias.add(factoryMidia.obtemMidias(arquivo));
		}
	}
	public String getMidias() {
		return this.midias.toString();
	}
}