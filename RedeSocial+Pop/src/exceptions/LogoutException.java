package exceptions;

public class LogoutException extends LogicaException{

	public LogoutException(){
		super("Nao eh possivel realizar logout.");
	}
	
	public LogoutException(String msg){
		super("Nao eh possivel realizar logout." + msg);
	}
}