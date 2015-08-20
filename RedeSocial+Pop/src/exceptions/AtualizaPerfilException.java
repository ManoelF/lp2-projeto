package exceptions;

public class AtualizaPerfilException extends EntradaException {

	public AtualizaPerfilException() {
		super("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
	}

	public AtualizaPerfilException(String msg) {
		super("Erro na atualizacao de perfil." + msg);
	}
	
	/*AtualizaDataInexistenteException: " Data nao existe."
	 *AtualizaDataInvalidaException: " Formato de data esta invalida."
	 *AtualizaEmailException: " Formato de e-mail esta invalido."
	 *AtualizaNomeException: " Nome dx usuarix nao pode ser vazio."
	 *AtualizaSenhaIncorretaException: " A senha fornecida esta incorreta."
	 *AtualizaSenhaInvalidaException: " Senha invalida."
	 * 
	 * 
	 */
	
}
