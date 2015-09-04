package exceptions;

public class UsuarioNaoCadastradoException extends LogicaException {

	public UsuarioNaoCadastradoException(String msg) {
		super(msg);
		   // "O usuario zeninguem@email.com.br nao esta cadastrado no +pop."
          // "Um usuarix com email "+ email +" nao esta cadastradx."
	}
	
}
