package exceptions;

public class EmailIncorretoException extends LoginException {

	public EmailIncorretoException() {
		super("Email inserido invalido.");
	}
	
}
