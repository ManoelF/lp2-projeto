package exceptions;

public class LoginException extends LogicaException {

	public LoginException() {
		super("Nao foi possivel realizar login.");
	}
	
	public LoginException(String msg) {
		super("Nao foi possivel realizar login." + msg);
	}	
}