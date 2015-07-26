package exceptions;

public class UsuarioLogadoException extends LoginException {

	public UsuarioLogadoException() {
		super("Usuario ja esta logado.");
	}
	
}
