package exceptions;

public class EntradaException extends RedeSocialMaisPopException {

	public EntradaException () {
		super("Campo obrigatorio nao eh valido.");
	}

	public EntradaException (String msg) {
		super(msg);
	}
	
}
