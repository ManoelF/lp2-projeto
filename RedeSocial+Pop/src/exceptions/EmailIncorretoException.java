package exceptions;

public class EmailIncorretoException extends EmailException {

	public EmailIncorretoException(String emailInserido) {
		super("Nao foi possivel realizar login. O usuario com email "+ emailInserido +" nao esta cadastrado.");
	}
	
}
