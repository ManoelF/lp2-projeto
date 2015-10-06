package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.LogicaException;
import exceptions.PostException;
import logica.midia.Mensagem;
import logica.midia.Midia;

public class Post implements Comparable<Post> {

	private String texto;
	private LocalDateTime data;
	private int like;
	private int deslike;
	private int popularidade;
	private List<String> hashtags;
	private List<Midia> midias;
	private Util util;
	FactoryMidia fabricaMidia;

	public Post(String texto, String data) throws PostException {
		this.util = Util.getInstancia();
		this.fabricaMidia = new FactoryMidia();

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

	public void curtir(int pontos) {
		this.like += 1;
		this.popularidade += pontos;
	}
	
	public void descurtir(int pontos) {
		this.deslike += 1;
		this.popularidade -= pontos;
	}
	
	private void verificaTam(String texto) throws PostException {
		String novoTexto = Util.getInstancia().encontraTexto(texto);
		if (novoTexto.length() >= 200) {
			throw new PostException("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		} else {
			this.texto = texto;
		}
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
		this.hashtags.add(hashtag);
	}
	 			
	private void buscaMidia(String mensagem) {
		this.fabricaMidia = new FactoryMidia();
		List<String> listMidias = util.getMidia(mensagem);
		
		Midia mensagem2 = new Mensagem(util.encontraTexto(mensagem));
		if (!mensagem2.toString().equals("")) {
			this.midias.add(mensagem2);
		}
		
		for (String arquivo: listMidias) {
			this.midias.add(this.fabricaMidia.obtemMidias(arquivo));
		}
	}
	
	public String getMidias() {
		return this.midias.toString();
	}
	
	public String getMidias(int indice) {
		return this.midias.get(indice).toString();
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
				hastags += ",";
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