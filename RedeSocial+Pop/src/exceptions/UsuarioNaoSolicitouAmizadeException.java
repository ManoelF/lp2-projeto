package exceptions;

public class UsuarioNaoSolicitouAmizadeException extends LogicaException {

	public UsuarioNaoSolicitouAmizadeException(String nomeUsuario) {
		super(nomeUsuario +" nao lhe enviou solicitacoes de amizade.");
	}
	
}
