/**
 * <code>OrdenaDecrescente</code> representa um objeto do tipo <code>Comparator</code>, pois implementa
 * essa interface. Essa classe é usada para definir uma ordenacao especificas dos Usuarios, sendo uma ordenacao 
 * decrescente por a quantidade de <b>Pops</b>. 
 */
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
	/**
	 * Metodo sobrescrito com alteracao do retorno
	 * para que possa resultar em uma ordenacao invertida.
	 * 
	 */
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
