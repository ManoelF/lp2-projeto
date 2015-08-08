package exceptions;

public class AtualizaSenhaIncorretaException extends AtualizaPerfilException {

	public AtualizaSenhaIncorretaException() {
		super(" A senha fornecida esta incorreta.");
	}
	
}
