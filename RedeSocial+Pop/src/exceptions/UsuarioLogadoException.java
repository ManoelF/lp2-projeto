package exceptions;

public class UsuarioLogadoException extends LoginException {

	public UsuarioLogadoException(String nomeUsuario) {
		super(" Um usuarix ja esta logadx: " + nomeUsuario + ".");
	}
}
