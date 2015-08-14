package exceptions;

public class UsuarioNaoCadastradoException extends LogicaException {

	public UsuarioNaoCadastradoException(String email) {
		super("Um usuarix com email "+ email +" nao esta cadastradx.");
		   // "O usuario zeninguem@email.com.br nao esta cadastrado no +pop."
	}
	
}
