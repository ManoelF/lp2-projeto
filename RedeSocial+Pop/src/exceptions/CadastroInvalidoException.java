package exceptions;

public class CadastroInvalidoException extends EntradaException  {

	public CadastroInvalidoException(){
		super("Erro no cadastro de Usuarios.");
	}
	
	public CadastroInvalidoException(String msg){
		super("Erro no cadastro de Usuarios. " + msg);
	}
	
}
