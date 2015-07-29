package testes;

import java.text.ParseException;

import logica.*;
import exceptions.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteController {

	private Controller controller;
	
	@Before
	public void setUp() throws EntradaException{
		controller = new Controller();
	}
	
	@Test
	public void testCadastraUsuario() throws LoginException, EntradaException {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "XXXXX-XXXX", "imagens/day_perfil");
			Assert.assertEquals("day.trindade@email.com", controller.getUsuariosCadastrados().get(0).getEmail());
		} catch (CadastroInvalidoException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void testCadastraUsuarioException() throws LoginException, EntradaException {
		try {
			controller.cadastraUsuario("", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "XXXXX-XXXX", "imagens/day_perfil");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Nome inserida/o nao eh valida/o", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void testLogin() throws LoginException, EntradaException {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "XXXXX-XXXX", "imagens/day_perfil");
			controller.login("day.trindade@email.com","poxaquecoxa");
		
			Assert.assertEquals("Day", controller.getUsuarioLogado().getNome());
		} catch (LogicaException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void testLoginException() throws LoginException, EntradaException {
		
		//testa o caso de realizar login quando um outro usuario ja esta logado
		try { 
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "XXXXX-XXXX", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "XXXXX-XXXX", "imagens/stive_perfil");
			
			controller.login("day.trindade@email.com","poxaquecoxa");
			controller.login("stive.anderson@email.com", "indiegente");
		} catch (LogicaException erro) {
			Assert.assertEquals("Nao foi possivel realizar login. Um usuario ja esta logado: Day.", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}
		
		controller.logout();
		
		//testa email inserido incorreto
		try {
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "XXXXX-XXXX", "imagens/stive_perfil");
			controller.login("stive.andrs@email.com", "indiegente");
		} catch (LogicaException erro) {
			Assert.assertEquals("Nao foi possivel realizar login. O usuario com email stive.andrs@email.com nao esta cadastrado.", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}
		
		//testa senha inserida incorreta
		try {
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "XXXXX-XXXX", "imagens/stive_perfil");
			controller.login("stive.anderson@email.com", "indies");
		} catch (LogicaException erro) {
			Assert.assertEquals("Nao foi possivel realizar login. Senha Invalida.", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}
	}

	public void testLogout() throws EntradaException {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "XXXXX-XXXX", "imagens/day_perfil");
			controller.login("day.trindade@email.com","poxaquecoxa");
			controller.logout();
			
			Assert.assertEquals(null, controller.getUsuarioLogado());
		} catch (LoginException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}
	}
	
	public void testLogoutException() throws EntradaException {
		try {
			controller.logout();
		} catch (LoginException erro) {
			Assert.assertEquals("Nao eh possivel realizar logout. Nenhum usuario esta logado no +pop.", erro.getMessage());
		}
	}
	
	
	//TESTAR E-MAIL IGUAIS

}
