package exceptions;

public class AtualizaSenhaException extends AtualizaPerfilException {

	public AtualizaSenhaException() {
		super("A senha fornecida esta incorreta.");
	}
	
}
