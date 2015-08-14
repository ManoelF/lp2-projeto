package exceptions;

public class CadastroEmailException extends CadastroInvalidoException {

	public CadastroEmailException() {
		super(" Formato de e-mail esta invalido.");
	}
	
}
