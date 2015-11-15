package logica;

import exceptions.CadastroInvalidoException;

public class FactoryUsuario {

	private Usuario usuario;
	private Util util;

	public FactoryUsuario() {
	}

	public Usuario criaUsuario(String nome, String email, String senha,
			String nascimento, String imagem)
			throws CadastroInvalidoException{

		
 		this.util = Util.getInstancia();

		if (nome == null || !util.verificaAtributo(nome)){
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
			
		} else if (email == null || !util.verificaEmail(email)) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
			
		} else if (senha == null || !util.verificaSenha(senha)) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Senha dx usuarix nao pode ser vazio.");
			
		} else if (nascimento == null || nascimento.trim().length() == 0) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		} else if (util.verificaFormatoData(nascimento) == false) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		} else if (util.verificaDataValida(nascimento) == false) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Data nao existe.");
			
		} else if (imagem == null || imagem.trim().length() == 0) {
			throw new CadastroInvalidoException("Erro no cadastro de Usuarios. Imagem inserida esta invalida.");
			
		} else {			
			usuario = new Usuario(nome, email, senha, nascimento, imagem);
			return usuario;
		}
		
	}
	
	

}