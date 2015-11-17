package exceptions;

public class IniciaSistemaException extends LogicaException {

	public IniciaSistemaException() {
		super("Nao foi possivel iniciar o sistema");
	}
	
	public IniciaSistemaException(String msg) {
		super(msg);
	}
	
	
	
}
