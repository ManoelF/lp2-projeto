package exceptions;

public class PostItemInvalidoException extends PostException {

	public PostItemInvalidoException() {
		super("Requisicao invalida. O indice deve ser maior ou igual a zero.");
	}
	
}
