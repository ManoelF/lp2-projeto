package exceptions;

public class CadastroDataInexistenteException extends CadastroInvalidoException{
	
	public CadastroDataInexistenteException(String msg){
		super(" Data nao existe.");
	}	
}