package logica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
<<<<<<< HEAD
=======

import logica.Usuario;
import logica.Controller;
import logica.ordenacao.OrdenaDecrescente;
>>>>>>> c65b2decb0436c6e39e0de435247fe4471b5f8ee

import logica.Usuario;
import logica.ordenacao.OrdenaDecrescente;

public class Ranking{
	
	private List<Usuario> comMaisPop;
	private List<Usuario> comMenosPop;
	private Comparator<Usuario> comparador;
	private List<Usuario> usuarios;
	private final int TAMANHO_MAXIMO = 3;
	
	public Ranking(List<Usuario> usuarios){
		this.comparador = new OrdenaDecrescente();
		this.usuarios = usuarios;
		comMaisPop = new ArrayList<Usuario>();
		comMenosPop = new ArrayList<Usuario>();	
	} //fecha o cosntrutor
	

	public void ordenaCrescente(List<Usuario> usuarios) {
		Collections.sort(usuarios);
	}
	
	public void ordenaDecrescente(List<Usuario> usuarios) {
		Collections.sort(usuarios, comparador);
	}
	
	public void rankingUsuario(){
<<<<<<< HEAD
		ordenaCrescente(usuarios);
=======
		
	}


	public void atualizaRanking() {
ordenaCrescente(usuarios);
>>>>>>> c65b2decb0436c6e39e0de435247fe4471b5f8ee
		
		int cont = 0;
		for (int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i) != null){
				comMaisPop.add(usuarios.get(i));
				cont++;
<<<<<<< HEAD
			}
			if(cont == 3){
				break;
			}
		}
		
		if(usuarios.size() > 3){
=======
			}
			if(cont == 3){
				break;
			}	
		}
		
		if(usuarios.size() > TAMANHO_MAXIMO){
			ordenaCrescente(usuarios);
>>>>>>> c65b2decb0436c6e39e0de435247fe4471b5f8ee
			ordenaDecrescente(usuarios);
			
			int contador = 0;
			for (int i = 0; i < usuarios.size() - TAMANHO_MAXIMO; i++) {
				if(usuarios.get(i) != null){
					comMenosPop.add(usuarios.get(i));
					contador++;
				}
				if(contador == 3){
					break;
<<<<<<< HEAD
				}
			}
		}
	}
	
	
	public String toString(){
		String imprime = "";
		
		for(int i = 0; i < comMaisPop.size(); i++){
			imprime += comMaisPop.get(i);
		}
	
		for(int i = 0; i < comMenosPop.size(); i++){
			imprime += comMenosPop.get(i);
			}
		
		return imprime;
		
	}
			
=======
				}else{
					System.out.println("o tamanho da lista eh menor do que 3");
				}
			}
			
			/*for (int j = 0; j <  comMaisPop.size(); j++) {
				System.out.println(comMaisPop.get(j).getNome() + " " + comMaisPop.get(j).getPop());
			}
			
			for (int j = 0; j <  comMenosPop.size(); j++) {
				System.out.println(comMenosPop.get(j).getNome() + " " + comMenosPop.get(j).getPop());
			}*/
			
		}
		
	}
	
	
	public String toString(){
		String imprime = "";
		
		for(int i = 0; i < comMaisPop.size(); i++){
			imprime += comMaisPop.get(i);
		}
	
		for(int i = 0; i < comMenosPop.size(); i++){
			imprime += comMenosPop.get(i);
			}
		
		return imprime;
		
	}
		
>>>>>>> c65b2decb0436c6e39e0de435247fe4471b5f8ee
	
} // fecha a classe ranking