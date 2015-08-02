package exceptions;

public class AtualizaInformacaoExcpetion extends LogicaException {
	
	public  AtualizaInformacaoExcpetion() {
		super("Atualiacao nao eh valida");
	}
	
	public  AtualizaInformacaoExcpetion(String msg) {
		super("A atualizacao do/a " + msg + " nao eh valida");
	}
	

}
