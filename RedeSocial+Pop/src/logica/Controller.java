package logica;

import java.util.ArrayList;
import java.util.List;
import exceptions.*;

public class Controller {

	private FactoryUsuario fabricaUsuario;
	private List<Usuario> usuariosCadastrados;
	
	public Controller() {
		this.fabricaUsuario = new FactoryUsuario();
		usuariosCadastrados = new ArrayList<Usuario>();
	}
	
	public void cadastraUsuario(String nome, String email, String senha, 
			String nascimento, String telefone, String imagem) throws StringException {
		Usuario novoUsuario;
		
		novoUsuario = fabricaUsuario.criaUsuario(nome, email, senha, nascimento, telefone, imagem);
		usuariosCadastrados.add(novoUsuario);
	}
		
}
