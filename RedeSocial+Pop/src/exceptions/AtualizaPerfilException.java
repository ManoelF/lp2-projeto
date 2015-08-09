package exceptions;

public class AtualizaPerfilException extends EntradaException {

	public AtualizaPerfilException() {
		super("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
	}

	public AtualizaPerfilException(String msg) {
		super("Erro na atualizacao de perfil." + msg);
	}
	
}
