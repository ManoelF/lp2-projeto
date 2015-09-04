package exceptions;

public class NaoSolicitouAmizadeException extends LogicaException {

	public NaoSolicitouAmizadeException(String msg) {
		super(msg);
		// nomeUsuario + " nao lhe enviou solicitacoes de amizade."
	}
	
}