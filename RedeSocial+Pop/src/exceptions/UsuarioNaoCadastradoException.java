package exceptions;

public class UsuarioNaoCadastradoException extends LogicaException {

	public UsuarioNaoCadastradoException(String email) {
		super("O usuario "+ email +" nao esta cadastrado no +pop.");
	}
	
}
