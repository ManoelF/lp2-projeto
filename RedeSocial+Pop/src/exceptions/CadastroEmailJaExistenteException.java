package exceptions;

public class CadastroEmailJaExistenteException extends CadastroInvalidoException{

	public CadastroEmailJaExistenteException() {
		super("Ja exite um usuario cadastrado com esse e-mail! Por favor insira um outro.");
	}
		
}
