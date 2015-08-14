package exceptions;

public class CadastroNomeException extends CadastroInvalidoException{
	
	public CadastroNomeException(){
		super(" Nome dx usuarix nao pode ser vazio.");
	}
}