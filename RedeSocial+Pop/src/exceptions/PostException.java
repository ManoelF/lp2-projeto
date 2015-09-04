package exceptions;

public class PostException extends EntradaException {

	public PostException() {
		super("Nao eh possivel criar o post.");
	}
	

	public PostException(String msg) {
		super(msg);
	}
	
	/*PostTamException: " O limite maximo da mensagem sao 200 caracteres."
	 *PostHashtagException: " As hashtags devem comecar com '#'.")
	 *PostItemInexistenteException(int item): "Item #"+ item +" nao existe nesse post, ele possui apenas 3 itens distintos."
	 *PostItemInvalidoException: "Requisicao invalida. O indice deve ser maior ou igual a zero."
	 */
}
