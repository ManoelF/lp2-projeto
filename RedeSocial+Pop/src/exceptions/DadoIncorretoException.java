package exceptions;

public class DadoIncorretoException extends LoginException {

	public DadoIncorretoException() {
		super("Dado inserido nao esta correto.");
	}
	
	public DadoIncorretoException(String msg) {
		super(msg);
	}
}
