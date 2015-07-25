package exceptions;

public class LoginException extends LogicaException {

	public LoginException() {
		super("Ja existe um usuario logado.");
	}
	
	public LoginException(String msg) {
		super(msg);
	}	
}
