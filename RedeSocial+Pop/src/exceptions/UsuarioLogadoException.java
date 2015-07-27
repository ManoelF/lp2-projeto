package exceptions;

public class UsuarioLogadoException extends LoginException {

	public UsuarioLogadoException(String nomeUSuario) {
		super("Nao foi possivel realizar login. Um usuario ja esta logado: "+ nomeUSuario +".");
	}
}
