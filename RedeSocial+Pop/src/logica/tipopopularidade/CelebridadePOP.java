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
 * Classe <code>CelebridadePOP</code> define um tipo de popularidade para um <code>Usuairo</code>.
 * Sendo assim, esta classe impementa a interface {@link TipoPopularidade}.
 */

package logica.tipopopularidade;

import java.time.LocalDate;
import java.time.LocalDateTime;

import logica.Post;

public class CelebridadePOP implements TipoPopularidade {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1083280831921543369L;
	private final int POPS = 25;
	private final int qntPostFeed = 4;
	
	/**
	 * Curtir o Post de um Usuario.
	 * Passando 25 POPS correspondente a valor de pops
	 * ofertado por um Usuario do tipo <code>CelebridadePOP</code>
	 * curtir o Post.
	 * 
	 * @param post
	 * 			Post a ser curtido.
	 */
	@Override
	public void curtir(Post post) {
		int pontos = POPS;
		if (isActual(post.getData())) {
			pontos += 10;
		}
		post.curtir(pontos);
	}

	/**
	 * Descurtir o Post de um Usuario.
	 * Passando -25 POPS correspondente a valor de pops
	 * ofertado por um Usuario do tipo <code>CelebridadePOP</code>
	 * descurtir o Post.
	 * 
	 * @param post
	 * 			Post a ser descurtido.
	 */
	@Override
	public void descurtir(Post post) {
		int pontos = POPS;
		if (isActual(post.getData())) {
			pontos += 10;
		}
		post.descurtir(pontos);
	}
	
	/**
	 * Verificacao se o Post foi criado na mesma data 
	 * em que o sistema esta sendo executado.
	 * 
	 * @param dataPost
	 * 			Data do Post.
	 * 
	 * @return Boolean
	 * 			Caso a data do post seja a mesma em que o sistema esta sendo
	 * 			executada sera retornado <b>True</b>, caso contrario, o valor 
	 * 			correspondente ao Post sera <b>False</b>.
	 */
	private boolean isActual(LocalDateTime dataPost) {
		String data = dataPost.toString();
		String  dataAtual = LocalDate.now().toString();
		return data.contains(dataAtual);
	}
	
	/**
	 * Quantidade de Posts que o usuario disponibiliza
	 * para popular o feed de seus amigos.
	 */
	@Override
	public int qntPostFeed() {
		return this.qntPostFeed;
	}
	/**
	 * Popularidade do Usuario.
	 */
	@Override
	public String popularidade() {
		return "Celebridade Pop";
	}
}
