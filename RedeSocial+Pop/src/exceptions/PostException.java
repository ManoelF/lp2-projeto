package exceptions;

public class PostException extends EntradaException {

	public PostException() {
		super("As caracteristicas do post nao sao validas.");
	}
	

	public PostException(String msg) {
		super(msg);
	}
	
}
