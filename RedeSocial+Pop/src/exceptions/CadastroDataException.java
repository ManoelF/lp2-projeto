package exceptions;

public class CadastroDataException extends CadastroInvalidoException{
	
	public CadastroDataException(){
		super(" Formato de data esta invalida.");
	}
	
}