package exceptions;

public class UsuarioDeslogadoException extends LoginException {

	public UsuarioDeslogadoException() {
		super("Usuario ja esta deslogado.");
	}
	
}
