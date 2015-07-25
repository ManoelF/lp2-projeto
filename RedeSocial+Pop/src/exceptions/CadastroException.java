package exceptions;

public class CadastroException extends EntradaException  {

	public CadastroException(){
		super();
	}
	
	public CadastroException(String msg){
		super(msg +" inserida/o nao eh valida/o");
	}
	
}
