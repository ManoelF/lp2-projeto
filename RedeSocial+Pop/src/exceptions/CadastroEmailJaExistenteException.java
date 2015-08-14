package exceptions;

public class CadastroEmailJaExistenteException extends LogicaException{

	public CadastroEmailJaExistenteException() {
		super("Ja exite um usuario cadastrado com esse e-mail! Por favor insira um outro.");
	}
		
}
