package exceptions;

public class AtualizaDataInexistenteException extends AtualizaPerfilException {

	public AtualizaDataInexistenteException() {
		super(" Data nao existe.");
	}
	
}
