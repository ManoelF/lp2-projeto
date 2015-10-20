package testes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import logica.*;
import exceptions.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteController {

	private Controller controller;
	
	@Before
	public void setUp() {
		controller = new Controller();
	}
	
	@Test
	public void testCadastraUsuario() {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			Assert.assertEquals("day.trindade@email.com", controller.getUsuariosCadastrados().get(0).getEmail());
			Assert.assertEquals("Day", controller.getInfoUsuario("Nome", controller.getUsuariosCadastrados().get(0).getEmail()));
		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		} 
	}
	
	@Test
	public void testCadastraUsuarioException() {
		try {
			controller.cadastraUsuario("", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.", erro.getMessage());
		} 
		
		try {
			controller.cadastraUsuario("Maria Lucia Oliveira", "maria.oliveira@hotmail.com", "feiradefruta", "07/03/1976", "imagens/maria_perfil");
			controller.cadastraUsuario("Maria Oliveira", "maria.oliveira@hotmail.com", "feiradefruta", "07/03/1976", "imagens/maria_perfil");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Ja exite um usuario cadastrado com esse e-mail! Por favor insira um outro.", erro.getMessage());
		}
	}
	
	@Test
	public void testLogin() {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.login("day.trindade@email.com","poxaquecoxa");
		
			Assert.assertEquals("Day", controller.getUsuarioLogado().getNome());
		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		} 
	}
	
	@Test
	public void testLoginException() {
		
		//testa o caso de realizar login quando um outro usuario ja esta logado
		try { 
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");

			controller.login("day.trindade@email.com","poxaquecoxa");
			controller.login("stive.anderson@email.com", "indiegente");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Nao foi possivel realizar login. Um usuarix ja esta logadx: Day.", erro.getMessage());
		} 
		
		
		//testa email inserido incorreto
		try {
			controller.logout();
			controller.login("stive.andrs@email.com", "indiegente");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Nao foi possivel realizar login. Um usuarix com email stive.andrs@email.com nao esta cadastradx.", erro.getMessage());
		}
		
		//testa senha inserida incorreta
		try {
			controller.login("stive.anderson@email.com", "indies");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Nao foi possivel realizar login. Senha invalida.", erro.getMessage());
		}
	}

	@Test
	public void testLogout() {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.login("day.trindade@email.com","poxaquecoxa");
			controller.logout();
			
			Assert.assertEquals(null, controller.getUsuarioLogado());
		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		} 
	}
	
	@Test
	public void testLogoutException() throws LogicaException {
		try {
			controller.logout();
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.", erro.getMessage());
		}
	}
	
	@Test
	public void testAddAmigo() {
		
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");
			
			controller.login("day.trindade@email.com", "poxaquecoxa");
			controller.adicionaAmigo("stive.anderson@email.com");
			
			controller.logout();
			controller.login("stive.anderson@email.com", "indiegente");
			
			Assert.assertEquals(1, controller.getNotificacoes());
			Assert.assertEquals("Day quer sua amizade.", controller.getNextNotificacao());
			Assert.assertEquals("day.trindade@email.com", controller.getUsuarioLogado().getSolicitacaoAmizade().get(0));
						
		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		} 		
	}
	
	@Test
	public void testAddAmigiException() throws EntradaException, ParseException, LogicaException {
		// testa adicionar usuario nao cadastrado 
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			
			controller.login("day.trindade@email.com", "poxaquecoxa");
			controller.adicionaAmigo("stive.anderson@email.com");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Um usuarix com email stive.anderson@email.com nao esta cadastradx.", erro.getMessage());
		}
		
		// testar solicitar notificacoes quando ja nao ha 
		try {
			controller.logout();
			
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");
			
			controller.login("day.trindade@email.com", "poxaquecoxa");
			controller.adicionaAmigo("stive.anderson@email.com");
			
			controller.logout();
			controller.login("stive.anderson@email.com", "indiegente");

			Assert.assertEquals("Day quer sua amizade.", controller.getNextNotificacao());
			controller.getNextNotificacao();
			
		} catch (NaoHaNotificacoesException erro) {
			Assert.assertEquals("Nao ha mais notificacoes.", erro.getMessage());
		}
		
	}

	@Test
	public void testRejeitaAmizade() {
	
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");
			
			controller.login("day.trindade@email.com", "poxaquecoxa");
			controller.adicionaAmigo("stive.anderson@email.com");
			controller.logout();
			
			controller.login("stive.anderson@email.com", "indiegente");
			
			controller.rejeitaAmizade( controller.getUsuarioLogado().getSolicitacaoAmizade().get(0) );
			controller.logout();
			
			controller.login("day.trindade@email.com", "poxaquecoxa");
			Assert.assertEquals("Stive Andrs rejeitou sua amizade.", controller.getNextNotificacao());
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		} 
		
	}
	
	@Test
	public void testRejeitaAmizadeException() {
		// testar erro de rejeicao de amizade nao solicitada
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");
						
			controller.login("stive.anderson@email.com", "indiegente");
			controller.rejeitaAmizade("day.trindade@email.com");
			controller.logout();
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Day nao lhe enviou solicitacoes de amizade.", erro.getMessage());
		} 
	
		// testar erro de rejeitar email n cadastrado
		try {			
			controller.logout();
			controller.login("stive.anderson@email.com", "indiegente");
			controller.rejeitaAmizade("italo.batista@email.com");
			controller.logout();
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Um usuarix com email italo.batista@email.com nao esta cadastradx.", erro.getMessage());
		}
		
	}

	@Test
	public void testAceitaAmizade() {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");
			
			controller.login("day.trindade@email.com", "poxaquecoxa");
			controller.adicionaAmigo("stive.anderson@email.com");
			controller.logout();
			
			controller.login("stive.anderson@email.com", "indiegente");
			controller.aceitaAmizade( controller.getUsuarioLogado().getSolicitacaoAmizade().get(0) );
			
			Assert.assertEquals(1, controller.getQtdAmigos());
			controller.logout();
			
			controller.login("day.trindade@email.com", "poxaquecoxa");
			Assert.assertEquals(1, controller.getQtdAmigos());
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		}
		
	}

	@Test
	public void testAceitaAmizadeException() {
		// testar erro de rejeicao de amizade nao solicitada
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");
						
			controller.login("stive.anderson@email.com", "indiegente");
			controller.aceitaAmizade("day.trindade@email.com");
			controller.logout();
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Day nao lhe enviou solicitacoes de amizade.", erro.getMessage());
		} 
	
		// testar erro de rejeitar email n cadastrado
		try {			
			controller.logout();
			controller.login("stive.anderson@email.com", "indiegente");
			controller.aceitaAmizade("italo.batista@email.com");
			controller.logout();
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Um usuarix com email italo.batista@email.com nao esta cadastradx.", erro.getMessage());
		}
		
	}
	
	@Test
	public void testGetInfoUsuario() {
		try {
			controller.cadastraUsuario("Lana Del Rey", "lizzygrant@email.com", "wishiwasdead", "21/06/1985", "imagem/lana_perfil");
			controller.login("lizzygrant@email.com", "wishiwasdead");
						
			Assert.assertEquals("Lana Del Rey", controller.getInfoUsuario("Nome"));
			Assert.assertEquals("imagem/lana_perfil", controller.getInfoUsuario("Foto"));
			
			controller.getInfoUsuario("Senha");
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("A senha dx usuarix eh protegida.", erro.getMessage());
		}
		
		try {
			controller.logout();
			
			controller.cadastraUsuario("Cat Power", "catpower@email.com", "sapatomica", "21/02/1972", "imagem/cat_perfil");
			
			Assert.assertEquals("Cat Power", controller.getInfoUsuario("Nome", controller.getUsuariosCadastrados().get(1).getEmail()));
			Assert.assertEquals("imagem/cat_perfil", controller.getInfoUsuario("Foto", controller.getUsuariosCadastrados().get(1).getEmail()));
			
			controller.getInfoUsuario("Senha", controller.getUsuariosCadastrados().get(1).getEmail());
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("A senha dx usuarix eh protegida.", erro.getMessage());
		}
		
		try {
			controller.cadastraUsuario("CeU", "ceumusic@email.com", "Fffrree", "17/04/1980", "imagem/ceU");
			
			Assert.assertEquals("CeU", controller.getInfoUsuario("Nome", "ceumusic@email.com" ));
			Assert.assertEquals("imagem/ceU", controller.getInfoUsuario("Foto", "ceumusic@email.com" ));

			controller.getInfoUsuario("Senha", "ceumusic@email.com");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("A senha dx usuarix eh protegida.", erro.getMessage());
		}
		
		
		
	}

	@Test
	public void testGetInfoUsuarioException() {
		try {
			controller.getInfoUsuario("Nome", "alguem@email.com");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Um usuarix com email alguem@email.com nao esta cadastradx.", erro.getMessage());
		}
		
	}
	
	@Test
	public void testRemoveAmigo() {
		try {
			controller.cadastraUsuario("Lana Del Rey", "lizzygrant@email.com", "wishiwasdead", "21/06/1985", "imagem/lana_perfil");
			controller.cadastraUsuario("Cat Power", "catpower@email.com", "sapatomica", "21/02/1972", "imagem/cat_perfil");
			controller.cadastraUsuario("CeU", "ceumusic@email.com", "Fffrree", "17/04/1980", "imagem/ceU");
			
			controller.login("catpower@email.com", "sapatomica");
			controller.adicionaAmigo("lizzygrant@email.com");
			controller.adicionaAmigo("ceumusic@email.com");
			controller.logout();
			
			controller.login("lizzygrant@email.com", "wishiwasdead");
			controller.aceitaAmizade("catpower@email.com");
			controller.logout();
			
			controller.login("ceumusic@email.com", "Fffrree");
			controller.aceitaAmizade("catpower@email.com");
			controller.logout();
			
			controller.login("catpower@email.com", "sapatomica");
			Assert.assertEquals(2, controller.getQtdAmigos());
			
			controller.removeAmigo("ceumusic@email.com");
			Assert.assertEquals(1, controller.getQtdAmigos());
			
			controller.logout();
			
			controller.login("ceumusic@email.com", "Fffrree");
			Assert.assertEquals(0, controller.getQtdAmigos());
			
		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRemoveUsuario() {
		try{
			controller.cadastraUsuario("Lana Del Rey", "lizzygrant@email.com", "wishiwasdead", "21/06/1985", "imagem/lana_perfil");
			controller.cadastraUsuario("Cat Power", "catpower@email.com", "sapatomica", "21/02/1972", "imagem/cat_perfil");
			controller.cadastraUsuario("CeU", "ceumusic@email.com", "Fffrree", "17/04/1980", "imagem/ceU");
			
			controller.login("catpower@email.com", "sapatomica");
			controller.adicionaAmigo("lizzygrant@email.com");
			controller.adicionaAmigo("ceumusic@email.com");
			controller.logout();
			
			controller.login("lizzygrant@email.com", "wishiwasdead");
			controller.aceitaAmizade("catpower@email.com");
			controller.logout();
			
			controller.login("ceumusic@email.com", "Fffrree");
			controller.aceitaAmizade("catpower@email.com");
			controller.logout();
			
			Assert.assertEquals(3, controller.getUsuariosCadastrados().size());
			
			controller.removeUsuario( controller.getUsuariosCadastrados().get(2).getEmail() );
			Assert.assertEquals(2, controller.getUsuariosCadastrados().size());
			
			controller.login("catpower@email.com", "sapatomica");
			Assert.assertEquals(1, controller.getQtdAmigos());

		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void atualizaInformacoes() {
		try {
			controller.cadastraUsuario("Fatima", "fatima@email.com.br", "will_S2", "21/04/1980", "resources/fatima.jpg");
			controller.login("fatima@email.com.br", "will_S2");
			controller.atualizaPerfil("Senha", "fafa_S2", "will_S2");
			controller.atualizaPerfil("Senha", "fafa123", "fafa_S2");
			
			Assert.assertEquals("fatima@email.com.br", controller.getUsuariosCadastrados().get(0).getEmail());
			Assert.assertEquals("fafa123",  controller.getsenha());
			
			controller.atualizaPerfil("Nome", "Fati Ma");
			controller.atualizaPerfil("E-mail", "fatxi@globo.com");
			
			Assert.assertEquals("Fati Ma", controller.getUsuarioLogado().getNome());
			Assert.assertEquals("fatxi@globo.com", controller.getUsuarioLogado().getEmail());
			controller.logout();
			controller.atualizaPerfil("Nome", "Ftm Bern");
			
		} catch(RedeSocialMaisPopException erro) {
			Assert.assertEquals("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.", erro.getMessage());
		}
	}

	@Test
	public void notificacoes() {
		
		try {
			controller.cadastraUsuario("Cat Power", "catpower@email.com", "sapatomica", "21/02/1972", "imagem/cat_perfil");
			controller.cadastraUsuario("Lana Del Rey", "lizzygrant@email.com", "wishiwasdead", "21/06/1985", "imagem/lana_perfil");
			
			controller.login("catpower@email.com", "sapatomica");
			controller.adicionaAmigo("lizzygrant@email.com");
			controller.criaPost("Bom dia.", "21/05/2015 12:00:11");
			controller.logout();
			
			controller.login("lizzygrant@email.com", "wishiwasdead");
			controller.aceitaAmizade("catpower@email.com");
			controller.curtirPost("catpower@email.com", 0);
			controller.descurtirPost("catpower@email.com", 0);
			controller.logout();
			
			controller.login("catpower@email.com", "sapatomica");
//			Assert.assertTrue(controller.getNotificacoes() == 2);
//			Assert.assertEquals("Cat Power descurtiu seu post de 2015-05-21 12:00:11.", controller.getNextNotificacao());
//			Assert.assertEquals("Cat Power curtiu seu post de 2015-05-21 12:00:11.", controller.getNextNotificacao());
		

		} catch (EntradaException | LogicaException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testTipoUsuario() { 
		try {
			controller.cadastraUsuario("Jesse Pinkman", "jesse@email.com.br", "cristal","17/09/1989", "resources/jesse.jpg");
			controller.cadastraUsuario("Walter White", "walt@email.com.br", "Heisenberg", "10/01/1959", "resources/walt.jpg");
			

			controller.login("jesse@email.com.br", "cristal");
			controller.adicionaAmigo("walt@email.com.br");
			controller.criaPost("Ou voce foge das coisas ou as enfrenta. Toda a questao eh aceitar, realmente, o que voce eh. #souQuemSou", "10/08/2015 22:00:01");
			controller.criaPost("Qual e a vantagem de ser um fora da lei quando se tem responsabilidades? #foraDaLei", "11/08/2015 02:40:21");
			Assert.assertTrue(0 == controller.getPops());
			controller.logout();
		

			controller.login("walt@email.com.br", "Heisenberg");
			
			Assert.assertEquals("Jesse Pinkman quer sua amizade.", controller.getNextNotificacao());
			controller.aceitaAmizade("jesse@email.com.br");
			Assert.assertTrue(0 == controller.getPops());
			Assert.assertEquals("Normal Pop", controller.getPopularidade());
			controller.curtirPost("jesse@email.com.br", 0);
			controller.curtirPost("jesse@email.com.br", 1);
			controller.curtirPost("jesse@email.com.br", 1);
			controller.setPops(950);
			Assert.assertTrue(950 == controller.getPops());
			Assert.assertEquals("Celebridade Pop", controller.getPopularidade());
			controller.criaPost("Voce precisa parar de pensar na escuridão que o precede. O que passou, passou. #passado", "05/06/2015 21:50:55");
			controller.criaPost("Eu não estou em perigo, eu sou o perigo. #heisenberg", "06/06/15 01:30:02");
			controller.logout();


			controller.login("jesse@email.com.br", "cristal");
			controller.setPops(510);
			Assert.assertTrue(510 == controller.getPops());
			Assert.assertEquals("Celebridade Pop", controller.getPopularidade());
			controller.criaPost("Eu sou o cara mau. <imagem>imagens/bad.jpg</imagem> #pinkman", "23/06/15 01:30:02");
			controller.curtirPost("walt@email.com.br", 0);
			controller.curtirPost("walt@email.com.br", 1);
			
			controller.logout();
		
		
		} catch(RedeSocialMaisPopException erro) {
			Assert.fail();
		}
	}

}