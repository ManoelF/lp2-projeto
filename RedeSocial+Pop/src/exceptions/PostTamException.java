package exceptions;

public class PostTamException extends PostException {

	public PostTamException() {
		super(" O tamanho do post nao deve exceder 200 caracteres.");
	}
	
}
