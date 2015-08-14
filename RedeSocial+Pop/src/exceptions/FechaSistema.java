package exceptions;

public class FechaSistema extends LogicaException{
	
	public FechaSistema(){
		super("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
	}
}