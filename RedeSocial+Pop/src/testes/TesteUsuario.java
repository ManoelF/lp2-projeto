package testes;

import static org.junit.Assert.*;
import logica.*;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;

public class TesteUsuario {
	Usuario joao;
	Usuario maria;
	Usuario fred;
	Usuario bruna;
	Usuario jailsa;
	Post poster;

	@Before
	public void setUp() {
	}

	@Test
	public void testUsuarioException() {
		try {
			joao = new Usuario("", "joao@email.com", "123", "10/10/1990",
					 "imagem/joao.jpg");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "", "123", "10/10/1990",
					"imagem/joao.jpg");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "", "10/10/1990",
					 "imagem/joao.jpg");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Senha dx usuarix nao pode ser vazio.",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "",
					 "imagem/joao.jpg");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Formato de data esta invalida.",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "10/10/1990", null);
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Imagem invalida.",
					erro.getMessage());
		}
	}

	@Test
	public void testUsuario() {
		try {

			maria = new Usuario("Maria", "maria@email.com", "321",
					"30/01/1995", "resources/maria.jpg");

			Assert.assertEquals("Maria", maria.getNome());
			Assert.assertEquals("maria@email.com", maria.getEmail());
			Assert.assertEquals("321", maria.getSenha());
			Assert.assertEquals("1995-01-30", maria.getNascimento()); 
			Assert.assertEquals("resources/maria.jpg", maria.getImagem());

		} catch (RedeSocialMaisPopException erro) {
			Assert.fail();
		} 

	}
	
	// reorganizar os proximos teste para testarem as novas alterações
	
		@Test
	public void testInformacoesAtualizadas()  {
		try {
			fred = new Usuario("Fred", "fred@email.com", "0101", "25/12/1989",
					 "resources/fred.jpg");

			fred.setNome("Fred Silva");
			Assert.assertEquals("Fred Silva", fred.getNome());

			fred.setEmail("fredsilva@email.com");
			Assert.assertEquals("fredsilva@email.com", fred.getEmail());

			fred.setNascimento("22/12/1989");
			Assert.assertEquals("1989-12-22", fred.getNascimento());


			fred.setImagem("");
			Assert.assertEquals("resources/avatarDefaul.jpg", fred.getImagem());
			
		} catch(RedeSocialMaisPopException erro) {
			Assert.fail();
		}
	}

	@Test
	public void testInformacoesAtualizadasException() {
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					 "resources/bruna.jpg");
			bruna.setNome("");
			Assert.assertEquals("Bruna", bruna.getNome());

		} catch(RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.", erro.getMessage());
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.setEmail("");
			Assert.assertEquals("bruna@email.com", bruna.getEmail());
			
		} catch(RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro na atualizacao de perfil. Formato de e-mail esta invalido.", erro.getMessage());
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					 "resources/bruna.jpg");
			bruna.setNascimento("");		
			Assert.assertEquals("12/11/00", bruna.getNascimento());
	
		} catch(RedeSocialMaisPopException erro) {
			//alterar msg
			Assert.assertEquals("Erro na atualizacao de perfil. Formato de data esta invalida.", erro.getMessage());
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.setImagem(null);			
			Assert.assertEquals("resources/bruna.jpg", bruna.getImagem());
	
		} catch(RedeSocialMaisPopException erro) {
			//alterar msg
			Assert.assertEquals("Erro na atualizacao de perfil.", erro.getMessage());
		}
		
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.setSenha("1221", "lalala");			
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro na atualizacao de perfil. A senha fornecida esta incorreta.", erro.getMessage());
		}

		// testar atualizacao de senha e de data de nascimento

	}
	
	@Test
	public void testFuncionalidades() {
		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "10/10/1990",
					 "imagem/joao.jpg");
			fred = new Usuario("Fred", "fred@email.com", "0101", "25/12/1989",
					 "resources/fred.jpg");
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			maria = new Usuario("Maria", "maria@email.com", "321",
					"20/01/1995", "resources/maria.jpg");
			String post = "O fraco nunca perdoa. O perdão é a característica do forte. "
					+ "<audio>musicas/perdao.mp3</audio> #Gandhi #Fort #Perdao";
			String data = "09/08/2015 00:12:32";
			
			// Criar Post
			joao.criaPost(post, data);
			Assert.assertEquals("O fraco nunca perdoa. O perdão é a característica do forte. <audio>musicas/perdao.mp3</audio>", joao.getConteudo("Conteudo",  0));
			Assert.assertEquals("#Gandhi,#Fort,#Perdao", joao.getConteudo("Hashtags", 0));
			Assert.assertEquals("2015-08-09 00:12:32", joao.getConteudo("Data", 0));
			
			/*// Curtir um post
			poster = joao.getPost(0);
			fred.curtir(poster);
			maria.curtir(poster);
			
			// Notificacoes e aquisicao de pops
			Assert.assertTrue(joao.getNotificacoes() == 2);
			Assert.assertTrue(joao.getPop() == 20);
			Assert.assertEquals("Fred curtiu seu post de 2015-08-09 00:12:32.", joao.getNextNotificacao());
			Assert.assertEquals("Maria curtiu seu post de 2015-08-09 00:12:32.", joao.getNextNotificacao());
			Assert.assertTrue(joao.getNotificacoes() == 0);
			
			// Descurtir um post
			bruna.descurtir(joao, 0);

			// Notificacoes e aquisicao de pops
			Assert.assertTrue(joao.getPop() == 10);
			Assert.assertTrue(joao.getNotificacoes() == 1);
			Assert.assertEquals("Bruna descurtiu seu post de 2015-08-09 00:12:32.", joao.getNextNotificacao());*/

			
		} catch(RedeSocialMaisPopException erro) {
			Assert.fail();
		}
		
	}

}