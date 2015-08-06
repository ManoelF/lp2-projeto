package exceptions;

public class PostException extends EntradaException {

	public PostException() {
		super("Nao eh possivel criar o post.");
	}
	

	public PostException(String msg) {
		super(msg);
	}
	
}
