package exceptions;

import logica.Usuario;

public class LoginException extends LogicaException {

	public LoginException(Usuario usuario) {
		super("Nao foi possivel realizar login. Um usuario ja esta logado: "+ usuario.getNome());
	}
	
	public LoginException(String msg) {
		super(msg);
	}	
}
