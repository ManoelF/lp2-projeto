package testes;

import java.util.Comparator;

import logica.*;

public class DuasOrdenacoes implements Comparator<Usuario>{

	@Override
	public int compare(Usuario u1, Usuario u2) {
		
		if (u1.getPops() > u2.getPops()) {
			return 1;
		} else if (u1.getPops() < u2.getPops()) {
			return -1;
		} else { // usuario com mesma popularidade
			return u1.getNome().compareTo(u2.getNome());
		}
	}
		

}
