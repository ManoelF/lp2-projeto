package exceptions;

public class EmailNaoCadastradoException extends EmailException {

	public EmailNaoCadastradoException(String emailUsuario) {
		super("O usuario "+ emailUsuario +" nao esta cadastrado no +pop.");
	}
	
}
