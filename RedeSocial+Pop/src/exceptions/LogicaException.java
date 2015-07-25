package exceptions;

public class LogicaException extends Exception {

	public LogicaException() {
		super("Erro de execucao");
	}
	
	public LogicaException(String msg) {
		super(msg);
	}
	
}
