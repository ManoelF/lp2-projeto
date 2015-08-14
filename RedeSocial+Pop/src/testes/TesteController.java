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
	public void setUp() {
		controller = new Controller();
	}
	
	@Test
	public void testCadastraUsuario() throws EntradaException, LogicaException {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			Assert.assertEquals("day.trindade@email.com", controller.getUsuariosCadastrados().get(0).getEmail());
			Assert.assertEquals("Day", controller.getInfoUsuario("Nome", controller.getUsuariosCadastrados().get(0).getEmail()));
		} catch (CadastroInvalidoException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void testCadastraUsuarioException() throws EntradaException, LogicaException, ParseException {
		try {
			controller.cadastraUsuario("", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}
		
		try {
			controller.cadastraUsuario("Maria Lucia Oliveira", "maria.oliveira@hotmail.com", "feiradefruta", "07/03/1976", "imagens/maria_perfil");
			controller.cadastraUsuario("Maria Oliveira", "maria.oliveira@hotmail.com", "feiradefruta", "07/03/1976", "imagens/maria_perfil");
		} catch (CadastroEmailJaExistenteException erro) {
			Assert.assertEquals("Ja exite um usuario cadastrado com esse e-mail! Por favor insira um outro.", erro.getMessage());
		}
	}
	
	@Test
	public void testLogin() throws LoginException, EntradaException {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.login("day.trindade@email.com","poxaquecoxa");
		
			Assert.assertEquals("Day", controller.getUsuarioLogado().getNome());
		} catch (LogicaException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void testLoginException() throws EntradaException, LogicaException {
		
		//testa o caso de realizar login quando um outro usuario ja esta logado
		try { 
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");

			controller.login("day.trindade@email.com","poxaquecoxa");
			controller.login("stive.anderson@email.com", "indiegente");
		} catch (LogicaException erro) {
			Assert.assertEquals("Nao foi possivel realizar login. Um usuarix ja esta logadx: Day.", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}
		
		controller.logout();
		
		//testa email inserido incorreto
		try {
			controller.login("stive.andrs@email.com", "indiegente");
		} catch (LogicaException erro) {
			Assert.assertEquals("Nao foi possivel realizar login. Um usuarix com email stive.andrs@email.com nao esta cadastradx.", erro.getMessage());
		}
		
		//testa senha inserida incorreta
		try {
			controller.login("stive.anderson@email.com", "indies");
		} catch (SenhaIncorretaException erro) {
			Assert.assertEquals("Nao foi possivel realizar login. Senha invalida.", erro.getMessage());
		}
	}

	@Test
	public void testLogout() throws EntradaException, LogicaException {
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.login("day.trindade@email.com","poxaquecoxa");
			controller.logout();
			
			Assert.assertEquals(null, controller.getUsuarioLogado());
		} catch (LoginException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void testLogoutException() throws UsuarioDeslogadoException {
		try {
			controller.logout();
		} catch (UsuarioDeslogadoException erro) {
			Assert.assertEquals("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.", erro.getMessage());
		}
	}
	
	@Test
	public void testAddAmigo() throws LogicaException, EntradaException, ParseException {
		
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
						
		} catch (LogicaException erro) {
			Assert.fail();
		} catch (EntradaException erro) {
			Assert.fail();
		} catch (ParseException erro) {
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
		} catch (LogicaException erro) {
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
	public void testRejeitaAmizade() throws EntradaException, ParseException, LogicaException {
	
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
			
		} catch (LogicaException erro) {
			Assert.fail();
		} 
		
	}
	
	@Test
	public void testRejeitaAmizadeException() throws EntradaException, ParseException, LogicaException {
		// testar erro de rejeicao de amizade nao solicitada
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");
						
			controller.login("stive.anderson@email.com", "indiegente");
			controller.rejeitaAmizade("day.trindade@email.com");
			controller.logout();
			
		} catch (NaoSolicitouAmizadeException erro) {
			Assert.assertEquals("Day nao lhe enviou solicitacoes de amizade.", erro.getMessage());
		} 
	
		// testar erro de rejeitar email n cadastrado
		try {			
			controller.logout();
			controller.login("stive.anderson@email.com", "indiegente");
			controller.rejeitaAmizade("italo.batista@email.com");
			controller.logout();
			
		} catch (UsuarioNaoCadastradoException erro) {
			Assert.assertEquals("Um usuarix com email italo.batista@email.com nao esta cadastradx.", erro.getMessage());
		}
		
	}

	@Test
	public void testAceitaAmizade() throws EntradaException, ParseException, LogicaException {
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
			
		} catch (LogicaException erro) {
			Assert.fail();
		}
		
	}

	@Test
	public void testAceitaAmizadeException() throws EntradaException, ParseException, LogicaException {
		// testar erro de rejeicao de amizade nao solicitada
		try {
			controller.cadastraUsuario("Day", "day.trindade@email.com", "poxaquecoxa", "10/10/1998", "imagens/day_perfil");
			controller.cadastraUsuario("Stive Andrs", "stive.anderson@email.com", "indiegente", "01/01/1990", "imagens/stive_perfil");
						
			controller.login("stive.anderson@email.com", "indiegente");
			controller.aceitaAmizade("day.trindade@email.com");
			controller.logout();
			
		} catch (NaoSolicitouAmizadeException erro) {
			Assert.assertEquals("Day nao lhe enviou solicitacoes de amizade.", erro.getMessage());
		} 
	
		// testar erro de rejeitar email n cadastrado
		try {			
			controller.logout();
			controller.login("stive.anderson@email.com", "indiegente");
			controller.aceitaAmizade("italo.batista@email.com");
			controller.logout();
			
		} catch (UsuarioNaoCadastradoException erro) {
			Assert.assertEquals("Um usuarix com email italo.batista@email.com nao esta cadastradx.", erro.getMessage());
		}
		
	}
	
	@Test
	public void testGetInfoUsuario() throws EntradaException, ParseException, LogicaException {
		try {
			controller.cadastraUsuario("Lana Del Rey", "lizzygrant@email.com", "wishiwasdead", "21/06/1985", "imagem/lana_perfil");
			controller.login("lizzygrant@email.com", "wishiwasdead");
						
			Assert.assertEquals("Lana Del Rey", controller.getInfoUsuario("Nome"));
			Assert.assertEquals("imagem/lana_perfil", controller.getInfoUsuario("Foto"));
			
			controller.getInfoUsuario("Senha");
			
		} catch (SenhaProtegidaException erro) {
			Assert.assertEquals("A senha dx usuarix eh protegida.", erro.getMessage());
		}
		
		try {
			controller.logout();
			
			controller.cadastraUsuario("Cat Power", "catpower@email.com", "sapatomica", "21/02/1972", "imagem/cat_perfil");
			
			Assert.assertEquals("Cat Power", controller.getInfoUsuario("Nome", controller.getUsuariosCadastrados().get(1).getEmail()));
			Assert.assertEquals("imagem/cat_perfil", controller.getInfoUsuario("Foto", controller.getUsuariosCadastrados().get(1).getEmail()));
			
			controller.getInfoUsuario("Senha", controller.getUsuariosCadastrados().get(1).getEmail());
			
		} catch (SenhaProtegidaException erro) {
			Assert.assertEquals("A senha dx usuarix eh protegida.", erro.getMessage());
		}
		
		try {
			controller.cadastraUsuario("CeU", "ceumusic@email.com", "Fffrree", "17/04/1980", "imagem/ceU");
			
			Assert.assertEquals("CeU", controller.getInfoUsuario("Nome", "ceumusic@email.com" ));
			Assert.assertEquals("imagem/ceU", controller.getInfoUsuario("Foto", "ceumusic@email.com" ));

			controller.getInfoUsuario("Senha", "ceumusic@email.com");
		} catch (SenhaProtegidaException erro) {
			Assert.assertEquals("A senha dx usuarix eh protegida.", erro.getMessage());
		}
		
		
		
	}

	@Test
	public void testGetInfoUsuarioException() throws LogicaException {
		try {
			controller.getInfoUsuario("Nome", "alguem@email.com");
		} catch (UsuarioNaoCadastradoException erro) {
			Assert.assertEquals("Um usuarix com email alguem@email.com nao esta cadastradx.", erro.getMessage());
		}
		
	}
	
	@Test
	public void testRemoveAmigo() throws EntradaException, ParseException {
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
			
		} catch (LogicaException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void testRemoveUsuario() throws EntradaException, ParseException, LogicaException {
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
						
		} catch (LogicaException erro) {
			Assert.fail();
		}
	}
	
	@Test
	public void atualizaInformacoes() {
		try {
			controller.cadastraUsuario("Fatima", "fatima@email.com.br", "will_S2", "21/04/1980");
			controller.login("fatima@email.com.br", "will_S2");
			controller.atualizaPerfil("Senha", "fafa_S2", "will_S2");
			controller.atualizaPerfil("Senha", "fafa123", "fafa_S2");
			//login email="fatima@email.com.br" senha="will_S2"
			//atualizaPerfil atributo="Senha" valor="fafa_S2" velhaSenha="will_S2"
			//atualizaPerfil atributo="Senha" valor="fafa123" velhaSenha="fafa_S2"
			
			
		} catch(LogicaException erro) {
			System.out.println(erro.getMessage());
		} catch (EntradaException erro) {
			System.out.println(erro.getMessage());
		} catch (ParseException erro) {
			System.out.println(erro.getMessage());
		}
		
		
		
		
	}
		


}
