package exceptions;

public class EntradaException extends Exception {

	public EntradaException () {
		super("Campo obrigatorio nao eh valido.");
	}

	public EntradaException (String msg) {
		super(msg);
	}
	
}
