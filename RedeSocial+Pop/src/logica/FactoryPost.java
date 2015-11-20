/* =========================== Rede Social +Pop ================================= #
 * 																				  *
 * Projeto obrigatorio para cumprimento de nota da disciplina Programação II      * 	  
 * e Laboratorio de Programacao II.                                               *
 *                                                                                *
 * Departamento de Informatica e Engenharia Eletrica							  *
 * Curso Ciência da Computação (UFCG - 2015.1). 								  *
 * Laboratorio de Programação II                                                  *
 * 																				  *
 * Discentes envolvidos: 														  *
 *   		Italo Batista														  *
 *   		Jose Manoel Ferreira												  *
 *   		Kerilin Chang. 														  *
 *																				  *
 * Orientador: 																	  *
 * 			Francisco Neto.		                                                  *
 * 												                                  *
 * ============================================================================== #
 */

/**
 * Classe <code>FactoryPost</code> tem a responsabilidade de criar um <code>Post</code> com todoas 
 * as informaçoes integras, tratando todas as excecoes.
 */
package logica;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import logica.midia.Mensagem;
import logica.midia.Midia;
import exceptions.LogicaException;
import exceptions.PostException;

public class FactoryPost implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484066119644680597L;
	private Post post;
	private Util util;
	private FactoryMidia fabricaMidia;
	
	public FactoryPost() {
		this.util = Util.getInstancia();
		this.fabricaMidia = new FactoryMidia();
	}
	/**
	 * Criao do Post.
	 * 
	 * @param texto
	 * 			Texto referente a entrada do Usuario para criacao 
	 * 			de seu Post.
	 * 
	 * @param data
	 * 			Data em que o Post foi criado.
	 * 
	 * @return Post
	 * 		
	 * @throws PostException
	 * 			{@link PostException}
	 * 
	 * @throws LogicaException
	 * 			{@link LogicaException}
	 */
	public Post criaPost(String texto, String data) throws PostException, LogicaException {
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
		LocalDateTime dataPost = util.converteParaData(data);
		List<String> hashtags = util.encontraHashtag(texto);
		List<Midia> midias = buscaMidia(texto);
		verificaTam(texto);
		
		this.post = new Post(texto, dataPost, hashtags,midias);
		
		return  this.post;
	}
	

	/**
	 * Busca das midias contidas na mensagem do Post.
	 * 
	 * @param mensagem
	 * 			Mensagem do Post.
	 * 
	 * @return List<Midia>
	 * 			Lista com as midias do Post.
	 */
	private List<Midia> buscaMidia(String mensagem) {
		this.fabricaMidia = new FactoryMidia();
		List<String> listMidias = util.getMidia(mensagem);
		List<Midia> midias = new ArrayList<>();
		
		Midia mensagem2 = new Mensagem(util.encontraTexto(mensagem));
		if (!mensagem2.toString().equals("")) {
			midias.add(mensagem2);
		}
		
		for (String arquivo: listMidias) {
			midias.add(this.fabricaMidia.obtemMidias(arquivo));
		}
		return midias;
	}
	
	/**
	 * Validacao da criacao do Post com a virificacao da 
	 * quantidade de caracteres maximo permitido.
	 * 
	 * @param texto
	 * 		Texto de criacao do Post.
	 * 
	 * @return Boolean
	 * 			Retorno sera <b>True</b> caso a quantidade de caracteres do texto
	 * 			seja menor ou igual a 200.
	 * 
	 * @throws PostException
	 * 			Lanca {@link PostException} caso o texto tenha acima de
	 * 			200 caracteres.
	 */
	private boolean verificaTam(String texto) throws PostException {
		String novoTexto = Util.getInstancia().encontraTexto(texto);
		if (novoTexto.length() >= 200) {
			throw new PostException("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		} else {
			//this.texto = texto;
			return true;
		}
	}
	
	
	
	
}
