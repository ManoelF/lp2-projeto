package exceptions;

public class SenhaIncorretaException extends LoginException {

	public SenhaIncorretaException() {
		super("Nao foi possivel realizar login. Senha invalida.");
	}
}
