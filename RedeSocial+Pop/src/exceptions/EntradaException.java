package exceptions;

public class EntradaException extends Exception {

	public EntradaException () {
		super("Campo obrigatorio nao eh invalida");
	}

	public EntradaException (String msg) {
		super(msg);
	}
	
}
