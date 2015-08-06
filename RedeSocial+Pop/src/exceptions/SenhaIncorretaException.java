package exceptions;

public class SenhaIncorretaException extends LoginException {

	public SenhaIncorretaException() {
		super(" Senha Invalida.");
	}
	
}
