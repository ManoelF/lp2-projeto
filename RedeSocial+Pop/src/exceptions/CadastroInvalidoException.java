package exceptions;

public class CadastroInvalidoException extends EntradaException  {

	public CadastroInvalidoException(){
		super();
	}
	
	public CadastroInvalidoException(String msg){
		super(msg +" inserida/o nao eh valida/o");
	}
	
}
