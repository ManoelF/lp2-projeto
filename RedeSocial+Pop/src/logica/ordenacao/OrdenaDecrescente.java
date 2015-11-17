package logica.ordenacao;

import java.io.Serializable;
import java.util.Comparator;

import logica.Usuario;

public class OrdenaDecrescente implements Comparator<Usuario>, Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5659334905906427448L;

	public OrdenaDecrescente() {
		
	}
	
	@Override
	public int compare(Usuario usuario, Usuario outroUsuario) {
		if (usuario.getPops() > outroUsuario.getPops()) { 
			return -1;
		} else if (usuario.getPops() < outroUsuario.getPops()) { 
			return 1;
		} else {
			return usuario.getEmail().compareToIgnoreCase(outroUsuario.getEmail()) * -1;
		}
	}

}
