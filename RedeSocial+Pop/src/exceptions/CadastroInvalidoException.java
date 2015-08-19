package exceptions;

public class CadastroInvalidoException extends EntradaException  {

	public CadastroInvalidoException(){
		super("Erro no cadastro de Usuarios.");
	}
	
	public CadastroInvalidoException(String msg){
		super("Erro no cadastro de Usuarios." + msg);
	}
	
	
	/*CadastroNomeException: " Nome dx usuarix nao pode ser vazio."
	 *CadastroSenhaException: " Senha dx usuarix nao pode ser vazio."
	 *CadastroEmailException: " Formato de e-mail esta invalido."
	 *CadastroDataInexistenteException(String msg): " Data nao existe."
	 *CadastroDataException: " Formato de data esta invalida."
	 * 
	 */
}
