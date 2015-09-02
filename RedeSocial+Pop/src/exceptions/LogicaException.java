package exceptions;

public class LogicaException extends RedeSocialMaisPopException {

	public LogicaException() {
		super("Erro de execucao");
	}
	
	public LogicaException(String msg) {
		super(msg);
	}
	
}
