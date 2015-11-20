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
 * Classe <code>Ranking</code>, uma abstracao de um ranking de <code>Usuario</code>, sendo os mais populares e menos 
 * populares em toda a Rede.
 */
package logica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import logica.Usuario;
import logica.ordenacao.OrdenaDecrescente;

public class Ranking implements Serializable {

	private static final long serialVersionUID = 2224815380849481101L;
	private List<Usuario> comMaisPop;
	private List<Usuario> comMenosPop;
	private Comparator<Usuario> comparador;
	private final int TAMANHO_MAXIMO = 3;

	/**
	 * Contrutor de Ranking.
	 */
	public Ranking() {

		this.comparador = new OrdenaDecrescente();
		
	} // fecha o construtor

	/**
	 * Ordenacao Crescente da lista de <b>Usuario</b>.	
	 * 
	 * @param usuarios
	 * 			Lista de <b>Usuarios</b> a ser ordenada. 
	 */
	public void ordenaCrescente(List<Usuario> usuarios) {
		Collections.sort(usuarios);
	}

	/**
	 * Ordenacao Decrescente da lista de <b>Usuario</b>.	
	 * 
	 * @param usuarios
	 * 			Lista de <b>Usuarios</b> a ser ordenada. 
	 */
	public void ordenaDecrescente(List<Usuario> usuarios) {
		Collections.sort(usuarios, comparador);
	}

	/**
	 * Atualizacao do ranking de usuarios, definindo os tres mais e menos populares.
	 * 
	 * @param listaDeUsuarios
	 * 			Lista de <b>Usuarios</b> para atualizacao do <b>Ranking</b>.
	 * 
	 * @return String
	 * 			Informacoes em formatacao especifica dos usuarios mais e menos 
	 * 			populares da rede. 
	 */
	public String atualizaRanking(List<Usuario> listaDeUsuarios) {
		
		comMaisPop = new ArrayList<Usuario>();
		comMenosPop = new ArrayList<Usuario>();
		
		ordenaDecrescente(listaDeUsuarios);

		int cont = 0;
		for (int i = 0; i < listaDeUsuarios.size(); i++) {
			comMaisPop.add(listaDeUsuarios.get(i));
			cont++;
			if (cont == 3) {
				break;
			}
		}

		if (listaDeUsuarios.size() > TAMANHO_MAXIMO) {
			ordenaCrescente(listaDeUsuarios);

			int contador = 0;
			for (int i = 0; i < listaDeUsuarios.size() - TAMANHO_MAXIMO; i++) {
				comMenosPop.add(listaDeUsuarios.get(i));
				contador++;
				if (contador == 3) {
					break;
				}
			}
		} 
		
		String chamaMetodo;
		chamaMetodo = toString();
		
		return chamaMetodo;
	}
	
	/**
	 * Formatacao dos Usuarios mais e menos populares na rede.
	 */
	@Override
	public String toString() {
		String imprime = "Mais Populares:";

		for (int i = 0; i < comMaisPop.size(); i++) {
			imprime += " (" + (i + 1) + ") " + comMaisPop.get(i).getNome() + " " + comMaisPop.get(i).getPops() + ";";
		}
		
		if(comMenosPop.size() != 0){
			imprime += " | Menos Populares:";
			for (int i = 0; i < comMenosPop.size(); i++) {
				imprime +=  " (" + (i + 1) + ") " + comMenosPop.get(i).getNome() + " " + comMenosPop.get(i).getPops() + ";" ;
			}
		}
		
		return imprime;
	}

} // fecha a classe ranking