package logica.ordenacao;

import java.util.Comparator;

import logica.Usuario;

public class OrdenaDecrescente implements Comparator<Usuario> {
	

	
	public OrdenaDecrescente() {
	}
	
	@Override
	public int compare(Usuario usuario, Usuario outroUsuario) {
		if (usuario.getPop() > outroUsuario.getPop()) { 
			return -1;
		} else if (usuario.getPop() < outroUsuario.getPop()) { 
			return 1;
		} else {
			return 0;
		}
	}

}
