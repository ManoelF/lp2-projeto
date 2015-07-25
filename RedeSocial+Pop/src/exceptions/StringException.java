package exceptions;

public class StringException extends EntradaException  {

	public StringException(){
		super();
	}
	
	public StringException(String msg){
		super(msg +" inserida/o nao eh valida/o");
	}
	
}
