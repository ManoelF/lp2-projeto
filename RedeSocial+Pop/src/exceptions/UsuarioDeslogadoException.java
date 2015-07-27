package exceptions;

public class UsuarioDeslogadoException extends LoginException {

	public UsuarioDeslogadoException() {
		super("Nao eh possivel realizar logout. Nenhum usuario esta logado no +pop.");
	}
	
}
