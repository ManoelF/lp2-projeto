package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import logica.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;

public class TesteController {

	private Controller controller;
	private Usuario usuario;
	
	@Before
	public void setUp() throws EntradaException{
		controller = new Controller();
		usuario = new Usuario("Day", "day.trindade@email.com", "poxaquecoxa", "", "XXXXX-XXXX", "imagens/day_perfil");
	}
	
	
	@Test
	public void test() throws LoginException {
		
		usuario.login();
		Assert.assertEquals(true, usuario.getEstaLogado());
		
		fail("Not yet implemented");
	}

}
