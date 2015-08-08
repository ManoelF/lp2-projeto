package exceptions;

public class PostTamException extends PostException {

	public PostTamException() {
		super(" O limite maximo da mensagem sao 200 caracteres.");
	}
	
}
