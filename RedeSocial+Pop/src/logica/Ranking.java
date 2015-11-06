package logica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import logica.Usuario;
import logica.ordenacao.OrdenaDecrescente;

public class Ranking {

	private List<Usuario> comMaisPop;
	private List<Usuario> comMenosPop;
	private Comparator<Usuario> comparador;
	private final int TAMANHO_MAXIMO = 3;

	public Ranking() {

		this.comparador = new OrdenaDecrescente();
		
	} // fecha o construtor

	public void ordenaCrescente(List<Usuario> usuarios) {
		Collections.sort(usuarios);
	}

	public void ordenaDecrescente(List<Usuario> usuarios) {
		Collections.sort(usuarios, comparador);
	}

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