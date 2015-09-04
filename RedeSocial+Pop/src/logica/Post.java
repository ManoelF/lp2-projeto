package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import exceptions.*;

public class Post implements Comparable<Post>, Comparator<Post> {

	private String texto;
	private LocalDateTime data;
	private int like;
	private int deslike;
	private int popularidade;
	private List<String> hashtags;
	private List<Midia> midias;
	private Util util;
	//FactoryMidia fabricaMidia;

	// data e hora
	public Post(String texto, String data) throws PostException {
		this.util = Util.getInstancia();
	//	fabricaMidia = new FactoryMidia();

		if (texto == null || texto.trim().length() == 0) {
			// lancar Exception
		}
		
		String[] dataHorario = data.split(" ");
		String dataS = dataHorario[0];
		String horaS = dataHorario[1];
		
		if (util.verificaFormatoData(dataS) == false) {
			// lanca execao data formato invalido
		}
		if (util.verificaFormatoHora(horaS) == false) {
			// lanca execao data formato invalido
		}
		if (util.verificaDataValida(dataS) == false) {
			// lanca excecao data invalida
		}
		if (util.verificaHoraValida(horaS) == false) {
			// lanca excecao data invalida
		}

		this.texto = texto;
		this.popularidade = 0;
		this.like = 0;
		this.deslike = 0;
		this.hashtags = util.encontraHashtag(texto);
		this.midias = new ArrayList<>();
		this.data = util.converteParaData(data);
		
		buscaMidia(texto);
		verificaTam(texto);			
	}

	private void verificaTam(String texto) throws PostException {
		String novoTexto = Util.getInstancia().encontraTexto(texto);
		if (novoTexto.length() >= 200) {
			throw new PostException("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		} else {
			this.texto = texto;
		}
	}
	
	public String getDataString() {
		if (this.data.getSecond() == 0) {
			return this.data.toString().replace("T", " ") + ":00";
		}
		return this.data.toString().replace("T", " ");
	}
	
	public LocalDateTime getData() {
		return this.data;
	}
	
	public String getMidias(int indice) {
		return this.midias.get(indice).toString();
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
	
	private void buscaMidia(String mensagem) {
		FactoryMidia factoryMidia = new FactoryMidia();
		List<String> listMidias = util.getMidia(mensagem);
		
		Midia mensagem2 = new Mensagem(util.encontraTexto(mensagem));
		if (!mensagem2.toString().equals("")) {
			this.midias.add(mensagem2);
		}
		
		for (String arquivo: listMidias) {
			this.midias.add(factoryMidia.obtemMidias(arquivo));
		}
	}
		
	public String getMidias() {
		return this.midias.toString();
	}

	public boolean comparaData(LocalDateTime outroData) {
		if (this.data.getMonth() != outroData.getMonth() &&
			this.data.getYear() != outroData.getYear() &&
			this.data.getDayOfMonth() != outroData.getDayOfMonth() ) {
				return false;
			}
		return true;		
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
	
	public String getConteudoPost(int indice) throws LogicaException {
		if (indice > this.midias.size()) {
			// lancar excecao de indice fora do tam
			throw new LogicaException("Alterar essa excecao.");
		} else if (indice < 0) {
			// lancar excecao indice invalido
			throw new LogicaException("Alterar essa excecao.");
		} else {
			return this.midias.get(indice).toString();
		}
		
	}		
	
	public void adicionaHashtag(String hashtag) {
		this.hashtags.add(hashtag);
	}
}