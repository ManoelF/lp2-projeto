package testes;

import java.text.ParseException;

import logica.FactoryUsuario;
import logica.Usuario;

import org.junit.Assert;
import org.junit.Test;

import exceptions.*;

public class TesteFactoryUsuario {

	@Test
	public void testeCriaUsuario() throws CadastroInvalidoException, ParseException {
		try {
		
			FactoryUsuario factory = new FactoryUsuario();
			Usuario usuario;
		
			usuario = factory.criaUsuario("kerilin", "kerilinchang@gmail.com", "teste123",
				"23/03/1994", "imagem/avatar");
					
			Assert.assertEquals("kerilin", usuario.getNome());
			Assert.assertEquals("kerilinchang@gmail.com", usuario.getEmail());
			Assert.assertEquals("teste123", usuario.getSenha());
			Assert.assertEquals("1994-03-23", usuario.getNascimento());
			Assert.assertEquals("imagem/avatar", usuario.getImagem());
		} catch (CadastroInvalidoException e) {
			Assert.fail();
		}
		
	} // fecha o teste CriaUsuario

}
