/**
 * Classe <code>Post</code> responsavel por conter todas as informacoes necessarias
 * para compor um Post.
 */

package logica;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
	

	/**
	 * Construtor de {@codePost}.
	 * Todas as informacoes estao garantidamente seguras para sua criacao.
	 * 
	 * @param texto
	 * 			Texto contido no Post.
	 * 
	 * @param data
	 * 			Data da criacao do Post.
	 * 
	 * @param hashtags
	 * 			Lista de hashtags do Post.
	 * 
	 * @param midias
	 * 			Lista de {@codeMida} do Post.
	 * 
	 */
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
	
	/**
	 * Curtir um Post.
	 * 
	 * @param pontos
	 * 			Pontos que o Post rebera ao ser curtido.
	 */
	public void curtir(int pontos) {
		this.like += 1;
		this.popularidade += pontos;
	}
	
	/**
	 * Descurtir um Post.
	 * 
	 * @param pontos
	 * 			Pontos que o Post rebera ao ser descurtido.
	 */
	public void descurtir(int pontos) {
		this.deslike += 1;
		this.popularidade -= pontos;
	}
	
	/**
	 * Data de criacao do Post.
	 */
	public LocalDateTime getData() {
		return this.data;
	}
	
	/**
	 * Hashtags do Post.
	 * 
	 * @return List<String>
	 * 			Listara todas as hastags do post.
	 */
	public List<String> getHashtags() {
		return hashtags;
	}
	
	/**
	 * Adiciona hashatags a lista.
	 * 
	 * @param hashtag
	 * 			Hashtag a ser adicionada ao <b>Post</b>.
	 */

	public void adicionaHashtag(String hashtag) {
		if (!hashtags.contains(hashtag)) {
			this.hashtags.add(hashtag);
			this.texto += " " + hashtag;
		}
	}
	
	/**
	 * Autor do Post.		
	 * 
	 * @return String
	 * 			Autor do Post.
	 */

	public String getAutor(){
		return this.autor;
	}
	
	/**
	 * Adicionan o nome do Autor do Post.
	 * @param autorNome
	 * 			Nome do autor.
	 */
	public void setAutor(String autorNome){
		this.autor = autorNome;
	}

	/**
	 * Busca de um conteudo especifico do Post.
	 * 
	 * @param atributo
	 * 			Informacao buscada, seja Data, Conteudo, Hashtags ou Mensagem.
	 * 
	 * @return String
	 * 			Informacao do Post.
	 * 
	 * @throws LogicaException
	 * 			Excecao de informacao inexistente.
	 */
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
	
	/**
	 * Informacoes basicas do Post.
	 * 
	 * @return String
	 * 			Informacoes.
	 */
	public String getPost() {
		return this.texto + " (" + getDataString() + ")";
	}
	
	/**
	 * Conteudo referente a mensagem do Post.
	 * 
	 * @return String
	 * 			Mensagem do Post.
	 */
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

	/**
	 * informacoes referentes as midias do Post.
	 *  
	 * @param indice
	 * 			Indice onde se encontra o Post.
	 * @return String
	 * 			Caminho da Midia.
	 * 
	 * @throws LogicaException
	 * 			Excecao caso um indice fora do alcance da lista seja requisitado.
	 * 
	 * @throws PostException
	 * 			Excecao caso um numero negativo seja requisitado.
	 */
	public String getConteudoPost(int indice) throws LogicaException, PostException {
		if (indice > this.midias.size() - 1) {
			throw new LogicaException("Item #" + indice + " nao existe nesse post, ele possui apenas " + this.midias.size() + " itens distintos.");
		} else if (indice < 0) {
			throw new PostException("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		} else {
			return this.midias.get(indice).toString();
		}
		
	}		
	
	/**
	 * Lista de midias do Post.
	 * 
	 * @return Lista<Midia>
	 * 			Midias do Post.
	 */
	public List<Midia> getMidias() {
		return this.midias;
	}
	
	/**
	 * String com a midia do Post.
	 * 
	 * @param indice
	 * 			Indice da midia na lista de midias.
	 * 
	 * @return String
	 * 			Caminho da midia.
	 */
	public String getMidias(int indice) {
		return this.midias.get(indice).toString();
	}
	
	/**
	 * Data do Post em String.
	 * 
	 * @return String
	 * 			Data do Post.
	 */
	public String getDataString() {
		if (this.data.getSecond() == 0) {
			return this.data.toString().replace("T", " ") + ":00";
		}
		return this.data.toString().replace("T", " ");
	}
		
	/**
	 * Quantidade de curtidas que o Post recebeu.
	 * 
	 * @return Int
	 * 			Curtidas do Post.
	 */
	public int getLike() {
		return this.like;
	}
	
	/**
	 * Quantidade de rejeicoes que o Post recebeu.
	 * 
	 * @return Int
	 * 			Rejeicoes do Post.
	 */
	public int getDeslike() {
		return this.deslike;
	}
	
	/**
	 * Busca todas as hashtags do post.
	 * 
	 * @return String
	 * 			Hashatags do Post.
	 */
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
 
	/**Popularidade do post, segundo a quantidade de
	 * curtidas e rejeicoes.
	 * 
	 * @return Int
	 * 			Quantidade de pops do Post.
	 */
	public int getPopularidade() {
		return this.popularidade;
	}
	/**
	 * Sobrescita do metodo para torna-lo comparavel
	 * por sua data de criacao.
	 */
	@Override
	public int compareTo(Post outroPost) {
		return this.data.compareTo(outroPost.getData());
	}
	
	/**
	 * Formatacao epecifica para salvar todas as informacoes
	 * do <b>Post</b> do <b>Usuario</b>.
	 * 
	 * @param indice
	 * 			Indice usado para informar ordem em
	 * 			que o post foi criado.
	 * @return
	 */
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