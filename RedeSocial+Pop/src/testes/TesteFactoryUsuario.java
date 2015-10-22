package testes;

import logica.FactoryUsuario;
import logica.Usuario;

import org.junit.Assert;
import org.junit.Test;

import exceptions.*;

public class TesteFactoryUsuario {

	@Test
	public void testeCriaUsuario() {
		try {
		
			FactoryUsuario factory = new FactoryUsuario();
			Usuario usuario;
		
			usuario = factory.criaUsuario("Marie", "marie@gmail.com", "teste123",
				"23/03/1994", "imagem/avatar");
					
			Assert.assertEquals("Marie", usuario.getNome());
			Assert.assertEquals("marie@gmail.com", usuario.getEmail());
			Assert.assertEquals("teste123", usuario.getSenha());
			Assert.assertEquals("1994-03-23", usuario.getNascimento());
			Assert.assertEquals("imagem/avatar", usuario.getImagem());
		} catch (RedeSocialMaisPopException e) {
			Assert.fail();
		}
		
	} // fecha o teste CriaUsuario

}
