package testes;

import logica.FactoryUsuario;
import logica.Usuario;
import org.junit.Assert;
import org.junit.Test;
import exceptions.*;

public class TesteFactoryUsuario {

	@Test
	public void testeCriaUsuario() throws CadastroInvalidoException {
		try {
		
			FactoryUsuario factory = new FactoryUsuario();
			Usuario usuario;
		
			usuario = factory.criaUsuario("kerilin", "kerilinchang@gmail.com", "teste123",
				"23031994", "imagem/avatar");
		
			Assert.assertEquals("kerilin", usuario.getNome());
			Assert.assertEquals("kerilinchang@gmail.com", usuario.getEmail());
			Assert.assertEquals("teste123", usuario.getSenha());
			Assert.assertEquals("23031994", usuario.getNascimento());
			Assert.assertEquals("imagem/avatar", usuario.getImagem());
		} catch (Exception e) {
			Assert.fail();
		}
		
	} // fecha o teste CriaUsuario

}
