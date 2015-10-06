package logica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

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
		ordenaCrescente(usuarios);
		
		int cont = 0;
		for (int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i) != null){
				comMaisPop.add(usuarios.get(i));
				cont++;
			}
			if(cont == 3){
				break;
			}
		}
		
		if(usuarios.size() > 3){
			ordenaDecrescente(usuarios);
			
			int contador = 0;
			for (int i = 0; i < usuarios.size() - TAMANHO_MAXIMO; i++) {
				if(usuarios.get(i) != null){
					comMenosPop.add(usuarios.get(i));
					contador++;
				}
				if(contador == 3){
					break;
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
			
	
} // fecha a classe ranking