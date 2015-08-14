package exceptions;

public class NaoHaNotificacoesException extends LogicaException{
	
	public NaoHaNotificacoesException(){
		super("Nao ha mais notificacoes.");
	}
}
