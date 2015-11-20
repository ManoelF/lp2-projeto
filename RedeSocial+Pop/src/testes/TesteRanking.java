package testes;

import logica.Controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.EntradaException;
import exceptions.LogicaException;
import exceptions.RedeSocialMaisPopException;

public class TesteRanking {

	private Controller controller = new Controller();
	
	
	@Before
	public void before()  {

		try {
			
			controller.cadastraUsuario("italo", "italo@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("manoel", "manoel@gmail.com", "456", "20/10/1995", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("chang", "chang@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("maria", "maria@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("jose", "jose@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("claudia", "claudia@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("felipe", "felipe@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("ana", "ana@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			controller.cadastraUsuario("francisco", "francisco@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			
			
		} catch(RedeSocialMaisPopException erro){
			Assert.fail();
		}
		
	}
	
	@Test
	public void testRanking(){
		
		try {
			controller.login("italo@gmail.com", "1234");
			Assert.assertEquals("italo" , controller.getInfoUsuario("Nome"));
			
		
			controller.setPops(10);
			controller.logout();
		
		
			Assert.assertEquals(controller.getUsuariosCadastrados().get(0).getEmail(), "italo@gmail.com");
			Assert.assertEquals(controller.getUsuariosCadastrados().get(0).getSenha(), "1234");
			Assert.assertEquals(controller.getUsuariosCadastrados().get(0).getNascimento(), "1995-10-20");
			Assert.assertEquals(controller.getUsuariosCadastrados().get(0).getImagem(), "resources/avatarDefaul.jpg");

			Assert.assertEquals(controller.getUsuariosCadastrados().get(1).getNome(), "manoel");
			Assert.assertEquals(controller.getUsuariosCadastrados().get(1).getEmail(), "manoel@gmail.com");
			Assert.assertEquals(controller.getUsuariosCadastrados().get(1).getSenha(), "456");
					
			Assert.assertEquals(controller.getUsuariosCadastrados().get(2).getNome(), "chang");
			Assert.assertEquals(controller.getUsuariosCadastrados().get(2).getEmail(), "chang@gmail.com");
			Assert.assertEquals(controller.getUsuariosCadastrados().get(2).getSenha(), "1234");
			
			Assert.assertEquals(controller.getUsuariosCadastrados().size(), 9);
		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		} 
		
	}// fecha test
	
	@Test
	public void testeOrdenacaoCrescente() throws RedeSocialMaisPopException {
		
			controller.login("italo@gmail.com", "1234");
			controller.setPops(10);
			controller.logout();
			
			controller.login("manoel@gmail.com", "456");
			controller.setPops(15);
			controller.logout();

			controller.login("chang@gmail.com", "1234");
			controller.setPops(5);
			controller.logout();
			
			controller.login("maria@gmail.com", "1234");
			controller.setPops(3);
			controller.logout();
			
			controller.login("jose@gmail.com", "1234");
			controller.setPops(20);
			controller.logout();
			
			controller.login("claudia@gmail.com", "1234");
			controller.setPops(1);
			controller.logout();
			
			controller.login("felipe@gmail.com", "1234");
			controller.setPops(4);
			controller.logout();
			
			controller.login("ana@gmail.com", "1234");
			controller.setPops(8);
			controller.logout();
			
			controller.login("francisco@gmail.com", "1234");
			controller.setPops(9);
			controller.logout();
						
			Assert.assertEquals(controller.atualizaRanking() ,"Mais Populares: (1) jose 20; (2) manoel 15; (3) italo 10; "
															+ "| Menos Populares: (1) claudia 1; (2) maria 3; (3) felipe 4;");

	} // fecha o test
	

}