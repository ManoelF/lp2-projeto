package exceptions;

public class FechaSistemaException extends LogicaException {

	public FechaSistemaException() {
		super("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
	}
	
}
