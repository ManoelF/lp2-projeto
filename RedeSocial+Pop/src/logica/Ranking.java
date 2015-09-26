package logica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

import logica.Usuario;
import logica.Controller;
import logica.ordenacao.OrdenaDecrescente;


public class Ranking{

	Usuario[] comMaisX2p;
	Usuario[] comMenosX2p;
	private Comparator<Usuario> comparador;
	Controller controller = new Controller();
	
	public Ranking(){
		this.comparador = new OrdenaDecrescente();
		comMaisX2p = new Usuario[3];
		comMenosX2p = new Usuario[3];
	} //fecha o cosntrutor
	
	
	//ordem crescente
	public void ordenaLista(){
		Collections.sort(controller.getUsuariosCadastrados());
	} // fecha ordena lsita
	
	//ordem decrescente
	public void ordenaInversaLista(){
		Collections.reverse(controller.getUsuariosCadastrados());
	}

	
	public void usuarioMaisX2p(){
	//imprime a lista com os MAIS x2p de forma crescente
			ordenaLista();
			for(int i = 0; i < comMaisX2p.length; i++){	
				comMaisX2p[i] = controller.getUsuariosCadastrados().get(i);
				System.out.println(comMaisX2p[i]);
			}
	}

	
	public void usuarioMenosX2p(){
	//imprime a lista com os MAIS x2p de forma decrescente
			ordenaInversaLista();
			for(int i = 0; i < comMenosX2p.length; i++){	
				comMenosX2p[i] = controller.getUsuariosCadastrados().get(i);
				System.out.println(comMenosX2p[i]);
			}
	}
	
	
	public void ordenaCrescente(List<Usuario> usuarios) {
		Collections.sort(usuarios);
	}
	
	public void ordenaDecrescente(List<Usuario> usuarios) {
		Collections.sort(usuarios, comparador);
	}
	
	
} // fecha Ranking