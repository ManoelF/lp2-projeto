package logica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import logica.Usuario;
import logica.Controller;


public class Ranking{
	
	private List<Usuario> comMaisPop;
	private List<Usuario> comMenosPop;
	private Comparator<Usuario> comparador;
	private List<Usuario> usuarios;
	
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
	
		if(usuarios.size() == 1){
			for(int i = 0; i < usuarios.size(); i++){	
				comMaisPop.add(usuarios.get(i));
			}
		} // fecha o if
		else if(usuarios.size() == 2){
			ordenaCrescente(usuarios);
			for(int i = 0; i < usuarios.size(); i++){	
				comMaisPop.add(usuarios.get(i));
			}
		} // fecha o if else
		else if(usuarios.size() == 3){
			ordenaCrescente(usuarios);
			for(int i = 0; i < usuarios.size(); i++){	
				comMaisPop.add(usuarios.get(i));
			}
		} // fecha o if else
		else{
			ordenaCrescente(usuarios);
			for(int i = 0; i < usuarios.size(); i++){	
				comMaisPop.add(usuarios.get(i));
			}
			
			
		}			
	}
} // fecha ranking