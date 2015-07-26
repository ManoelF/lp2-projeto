package exceptions;

public class SenhaIncorretaException extends DadoIncorretoException {

	public SenhaIncorretaException() {
		super("Senha inserida incorreta.");
	}
	
}
