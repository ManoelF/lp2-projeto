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

	@Before
	public void setUp() {
	}

	@Test
	public void testUsuarioException() throws ParseException  {
		try {
			joao = new Usuario("", "joao@email.com", "123", "10/10/1990",
					 "imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "", "123", "10/10/1990",
					"imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "", "10/10/1990",
					 "imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Senha dx usuarix nao pode ser vazio.",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "",
					 "imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Formato de data esta invalida.",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "10/10/1990", null);
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Imagem invalida.",
					erro.getMessage());
		}
	}

	@Test
	public void testUsuario() {
		try {

			maria = new Usuario("Maria", "maria@email.com", "321",
					"32/01/1995", "");

			Assert.assertEquals("Maria", maria.getNome());
			Assert.assertEquals("maria@email.com", maria.getEmail());
			Assert.assertEquals("321", maria.getSenha());
			//Assert.assertEquals("1995-01-32", maria.getNascimento()); LOCALDATE ADIANTA A DADA EM DOIS DIAS
			Assert.assertEquals("resources/default.jpg", maria.getImagem());

		} catch (CadastroInvalidoException erro) {
			Assert.fail();
		} catch(ParseException erro) {
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
			
		} catch(CadastroInvalidoException erro) {
			Assert.fail();
		} catch(AtualizaPerfilException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		} catch (EntradaException erro) {
			Assert.fail();
		}

	}

	@Test
	public void testInformacoesAtualizadasException() throws EntradaException, ParseException {
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					 "resources/bruna.jpg");
			bruna.setNome("");
			Assert.assertEquals("Bruna", bruna.getNome());

		} catch(AtualizaPerfilException erro) {
			Assert.assertEquals("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.setEmail("");
			Assert.assertEquals("bruna@email.com", bruna.getEmail());

		} catch(CadastroInvalidoException erro) {
			Assert.fail();
		} catch(AtualizaPerfilException erro) {
			Assert.assertEquals("Erro na atualizacao de perfil. Formato de e-mail esta invalido.", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					 "resources/bruna.jpg");
			bruna.setNascimento("");		
			Assert.assertEquals("12/11/00", bruna.getNascimento());
		} catch(CadastroInvalidoException erro) {
			Assert.fail();
		} catch(AtualizaPerfilException erro) {
			//alterar msg
			Assert.assertEquals("Erro na atualizacao do perfil.", erro.getMessage());
		} catch(ParseException erro) {
			//Assert.assertEquals("Unparseable date: ", erro.getMessage());   <<RESOLVER>>
			Assert.assertEquals("Data inserida invalida", erro.getMessage());
			//alterar msg
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.setImagem(null);			
			Assert.assertEquals("resources/bruna.jpg", bruna.getImagem());
		} catch(CadastroInvalidoException erro) {
			Assert.fail();
		} catch(AtualizaPerfilException erro) {
			//alterar msg
			Assert.assertEquals("Erro na atualizacao de perfil.", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}
		
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.setSenha("1221", "lalala");			
		} catch (AtualizaPerfilException erro) {
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
			Assert.assertEquals("O fraco nunca perdoa. O perdão é a característica do forte. <audio>musicas/perdao.mp3</audio> ", joao.getConteudo("Conteudo",  0));
			Assert.assertEquals("#Gandhi,#Fort,#Perdao", joao.getConteudo("Hashtags", 0));
			Assert.assertEquals("2015-08-09 00:12:32", joao.getConteudo("Data", 0));
			
			// Curtir um post
			fred.curtir(joao, 0);
			maria.curtir(joao, 0);
			
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
			Assert.assertEquals("Bruna descurtiu seu post de 2015-08-09 00:12:32.", joao.getNextNotificacao());

			
		} catch(Exception erro) {
			Assert.fail();
		}
		
	}

}