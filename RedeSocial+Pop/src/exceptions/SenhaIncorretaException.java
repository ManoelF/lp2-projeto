package exceptions;

public class SenhaIncorretaException extends DadoIncorretoException {

	public SenhaIncorretaException() {
		super("Nao foi possivel realizar login. Senha Invalida.");
	}
	
}
