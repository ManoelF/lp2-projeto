package testes;

import logica.Controller;
import logica.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.EntradaException;
import exceptions.LogicaException;

public class TesteRanking {

	private Controller controller = new Controller();
	private Usuario use1, use2, use3, use4, use5, use6, use7, use8, use9;
	
	
		//controller.cadastraUsuario("italo", "italo@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
		//List<Usuario> listaDeUsuarios = controller.getUsuariosCadastrados();
	
	@Before
	public void before() throws  EntradaException, LogicaException{

		try {
			use1 = new Usuario("italo", "italo@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			use2 = new Usuario("manoel", "manoel@gmail.com", "456", "20/10/1995", "resources/avatarDefaul.jpg");
			use3 = new Usuario("chang", "chang@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			use4 = new Usuario("maria", "maria@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			use5= new Usuario("jose", "jose@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			use6 = new Usuario("claudia", "claudia@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			use7 = new Usuario("felipe", "felipe@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			use8 = new Usuario("ana", "ana@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			use9 = new Usuario("francisco", "francisco@gmail.com", "1234", "20/10/1995", "resources/avatarDefaul.jpg");
			
			controller.cadastraUsuario(use1.getNome(), use1.getEmail(), use1.getSenha(), "20/10/1995", use1.getImagem());
			controller.cadastraUsuario(use2.getNome(), use2.getEmail(), use2.getSenha(), "20/10/1995", use2.getImagem());
			controller.cadastraUsuario(use3.getNome(), use3.getEmail(), use3.getSenha(), "20/10/1995", use3.getImagem());
			controller.cadastraUsuario(use4.getNome(), use4.getEmail(), use4.getSenha(), "20/10/1995", use4.getImagem());
			controller.cadastraUsuario(use5.getNome(), use5.getEmail(), use5.getSenha(), "20/10/1995", use5.getImagem());
			controller.cadastraUsuario(use6.getNome(), use6.getEmail(), use6.getSenha(), "20/10/1995", use6.getImagem());
			controller.cadastraUsuario(use7.getNome(), use7.getEmail(), use7.getSenha(), "20/10/1995", use7.getImagem());
			controller.cadastraUsuario(use8.getNome(), use8.getEmail(), use8.getSenha(), "20/10/1995", use8.getImagem());
			controller.cadastraUsuario(use9.getNome(), use9.getEmail(), use9.getSenha(), "20/10/1995", use9.getImagem());
			
			
		} catch(LogicaException erro){
			Assert.fail();
		}
		
	}
	
	
	@Test
	public void testRanking(){
				

				if(controller.getUsuariosCadastrados().size() != 0){
					
					Assert.assertEquals(controller.getUsuariosCadastrados().get(0).getNome(), "italo");
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
				}
			
		
	}// fecha test
	
	@Test
	public void testeOrdenacaoCrescente() throws EntradaException, LogicaException{
		
			controller.getUsuariosCadastrados().get(0).setPop(10);
			controller.getUsuariosCadastrados().get(1).setPop(15);
			controller.getUsuariosCadastrados().get(2).setPop(5);
			controller.getUsuariosCadastrados().get(3).setPop(3);
			controller.getUsuariosCadastrados().get(4).setPop(20);
			controller.getUsuariosCadastrados().get(5).setPop(1);
			controller.getUsuariosCadastrados().get(6).setPop(4);
			controller.getUsuariosCadastrados().get(7).setPop(8);
			controller.getUsuariosCadastrados().get(8).setPop(9);
						
			controller.atualizaRanking();

			/*for (int i = 0; i < controller.getUsuariosCadastrados().size(); i++) {
				System.out.println( controller.getUsuariosCadastrados().get(i).getNome() + " " + controller.getUsuariosCadastrados().get(i).getPop());
			}*/
					
	
	} // fecha o test
}