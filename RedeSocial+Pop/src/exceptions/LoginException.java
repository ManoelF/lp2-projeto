package exceptions;

public class LoginException extends LogicaException {

	public LoginException() {
		super("Nao foi possivel realizar login.");
	}
	
	public LoginException(String msg) {
		super("Nao foi possivel realizar login." + msg);
	}
	
	/*UsuarioLogadoException(String nomeUsuario): " Um usuarix ja esta logadx: " + nomeUsuario + "."
	 *LoginEmailException(String email): " Um usuarix com email " + email + " nao esta cadastradx."
	 * 	     // "O usuario zeninguem@email.com.br nao esta cadastrado no +pop."
	 */
	
}