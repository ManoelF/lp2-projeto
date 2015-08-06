package exceptions;

public class PostItemInexistenteException extends PostException {

	public PostItemInexistenteException(int item) {
		super("Item #"+ item +" nao existe nesse post, ele possui apenas 3 itens distintos.");		
	}
	
}
