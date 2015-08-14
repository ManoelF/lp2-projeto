package exceptions;

public class NaoSolicitouAmizadeException extends LogicaException {

	public NaoSolicitouAmizadeException(String nomeUsuario) {
		super(nomeUsuario + " nao lhe enviou solicitacoes de amizade.");
	}
	
}