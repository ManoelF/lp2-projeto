package exceptions;

public class LogoutException extends LogicaException{

	public LogoutException(){
		super("Nao eh possivel realizar logout.");
	}
	
	public LogoutException(String msg){
		super(msg);
	}
	
	/*UsuarioDeslogadoException: " Nenhum usuarix esta logadx no +pop."
	 * 
	 * 
	 * 
	 */
}