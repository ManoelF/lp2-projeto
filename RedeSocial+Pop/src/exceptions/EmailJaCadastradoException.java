package exceptions;

public class EmailJaCadastradoException extends EmailException {

	public EmailJaCadastradoException() {
		super("Ja exite um usuario cadastrado com esse e-mail! Por favor insira um outro.");
	}
	
}
