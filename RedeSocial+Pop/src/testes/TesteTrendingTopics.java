package testes;

import logica.Controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.EntradaException;
import exceptions.LogicaException;

public class TesteTrendingTopics {

	private Controller controller = new Controller();
	
	
	@Before
	public void criaUsuarios() {
		try {
			controller.cadastraUsuario("jose", "jose@gmail.com", "1234", "08/08/1980", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("maria", "maria@gmail.com", "acordaMenina", "15/06/1980", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("carlos", "carlos@gmail.com", "1345", "08/08/1980", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("angela", "angela@gmail.com", "feliz123", "08/08/1980", "resources/avatarDefaul.jpg");
		} catch (EntradaException | LogicaException e) {
				Assert.fail();
		}
	}

	
	@Test
	public void testaTrendingTopics(){
		
		try {
			String hashtag = "Trending Topics:  (1) #ufcg: 5; (2) #acordarCedo: 3; (3) #preguiça: 2;";
			Assert.assertEquals("Trending Topics: ", controller.atualizaTrendingTopics());
			
			controller.login("jose@gmail.com", "1234");
			controller.criaPost("volta as aulas #ufcg #acordarCedo" , "01/08/2015 12:00:10");
			controller.logout();
			
			Assert.assertEquals("Trending Topics:  (1) #ufcg: 1; (2) #acordarCedo: 1;", controller.atualizaTrendingTopics());
			
			controller.login("maria@gmail.com", "acordaMenina");
			controller.criaPost("hoje eu nao vou estudar #preguiça #ufcg", "05/08/2015 12:00:10");
			controller.logout();
			
			controller.login("carlos@gmail.com", "1345");
			controller.criaPost("agora eu vou fazer algo que preste #acordarCedo #ufcg #minhaVida", "03/08/2015 12:00:10");
			controller.logout();
			
			controller.login("angela@gmail.com", "feliz123");
			controller.criaPost("voltando para casa hoje eu fui assaltado #ufcg ", "01/08/2015 12:00:10");
			controller.criaPost("hoje tenho que ir para a universidade #preguiça #ufcg #acordarCedo", "02/08/2015 12:00:10");

			Assert.assertEquals(hashtag, controller.atualizaTrendingTopics());
			
		} catch (LogicaException | EntradaException e) {
			Assert.fail();
		}
	}

} // fecha a classe testeTrendingTopics