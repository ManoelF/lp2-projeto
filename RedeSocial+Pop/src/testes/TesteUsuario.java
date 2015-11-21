package testes;

import logica.FactoryUsuario;
import logica.Post;
import logica.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.RedeSocialMaisPopException;


public class TesteUsuario {
	Usuario joao;
	Usuario maria;
	Usuario fred;
	Usuario bruna;
	Usuario jailsa;
	Post poster;
	FactoryUsuario fabricauser;

	@Before
	public void setUp() {
		fabricauser = new FactoryUsuario();
	}

	@Test
	public void testUsuarioException() {
		
		// EXCECAO NOME
		try {
			joao = new Usuario("", "joao@email.com", "123", "10/10/1990",
					 "imagem/joao.jpg");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.",
					erro.getMessage());
		}

		// EXCECAO EMAIL
		try {
			joao = new Usuario("Joao", "", "123", "10/10/1990",
					"imagem/joao.jpg");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.",
					erro.getMessage());
		}

		// EXCECAO SENHA
		try {
			joao = new Usuario("Joao", "joao@email.com", "", "10/10/1990",
					 "imagem/joao.jpg");
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Senha dx usuarix nao pode ser vazio.",
					erro.getMessage());
		}

		// EXCECAO IMAGEM
		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "10/10/1990", null);
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro no cadastro de Usuarios. Imagem inserida esta invalida.",
					erro.getMessage());
		}
	}

	@Test
	public void testUsuario() {
		
		// CRIACAO DE USUARIOS
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
			
		// ATUALIZACAO DE USUARIOS
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
		// SET NOME INVALIDO
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					 "resources/bruna.jpg");
			bruna.setNome("");
			Assert.assertEquals("Bruna", bruna.getNome());

		} catch(RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro na atualizacao de perfil.", erro.getMessage());
		}
		
		// SET EMAIL INVALIDO
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.setEmail("");
			Assert.assertEquals("bruna@email.com", bruna.getEmail());
			
		} catch(RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro na atualizacao de perfil.", erro.getMessage());
		}
		
		// SET NASCIMENTO INVALIDO
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					 "resources/bruna.jpg");
			bruna.setNascimento("");		
			Assert.assertEquals("12/11/00", bruna.getNascimento());
	
		} catch(RedeSocialMaisPopException erro) {
			//alterar msg
			Assert.assertEquals("Erro na atualizacao de perfil.", erro.getMessage());
		}

		// SET NASCIMENTO IVALIDO
			try {
				bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
						"resources/bruna.jpg");
				bruna.setNascimento("12/11/00");
			} catch (RedeSocialMaisPopException erro) {
				Assert.assertEquals("Erro na atualizacao de perfil.", erro.getMessage());
			}
			
		// SET IMAGEM INVALIDA
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.setImagem(null);			
			Assert.assertEquals("resources/bruna.jpg", bruna.getImagem());
	
		} catch(RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro na atualizacao de perfil.", erro.getMessage());
		}
		
		// SET SENHA INVALIDA
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.setSenha("1221", "lalala");			
		} catch (RedeSocialMaisPopException erro) {
			Assert.assertEquals("Erro na atualizacao de perfil. A senha fornecida esta incorreta.", erro.getMessage());
		}
		
		

	}
	
	@Test
	public void testFuncionalidades() {
		try {
			
			// CRIANDO USUARIOS
			joao = new Usuario("Joao", "joao@email.com", "123", "10/10/1990",
					 "imagem/joao.jpg");
			fred = new Usuario("Fred", "fred@email.com", "0101", "25/12/1989",
					 "resources/fred.jpg");
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			maria = new Usuario("Maria", "maria@email.com", "321",
					"20/01/1995", "resources/maria.jpg");
			
			
			// ADICIONAR AMIGOS
			joao.adicionaAmigo(fred);
			joao.adicionaAmigo(bruna);
			joao.adicionaAmigo(maria);

			// ACEITA AMIZADE
			fred.aceitaAmizade(joao);
			bruna.aceitaAmizade(joao);
			Assert.assertTrue(fred.getQtdAmigos() == 1);
			Assert.assertTrue(bruna.getQtdAmigos() == 1);
			
			// REJEITA AMIZADE
			maria.rejeitaAmizade(joao);
			//Assert.assertTrue(joao.getQtdAmigos() == 2); ajustar o metodo que adiciona amigos
			
			// VERIFICA NOTIFICACOES
			Assert.assertTrue(fred.getNotificacoes() == 1);
			Assert.assertTrue(bruna.getNotificacoes() == 1);
			Assert.assertTrue(maria.getNotificacoes() == 1);
			Assert.assertTrue(joao.getNotificacoes() == 1); // Referente ao pedido de amizade rejeitado
			
			Assert.assertEquals("Joao quer sua amizade.", fred.getNextNotificacao());
			Assert.assertEquals("Maria rejeitou sua amizade.", joao.getNextNotificacao());
			
			// CRIA POST
			String post = "O fraco nunca perdoa. O perdão é a característica do forte. "
					+ "<audio>musicas/perdao.mp3</audio> #Gandhi #Forte #Perdao";
			String data = "09/08/2015 00:12:32";
			
			joao.criaPost(post, data);
			
			Assert.assertTrue(joao.getQtdPost() == 1);
			Assert.assertEquals("O fraco nunca perdoa. O perdão é a característica do forte. <audio>musicas/perdao.mp3</audio>", joao.getConteudo("Conteudo",  0));
			Assert.assertEquals("#Gandhi,#Forte,#Perdao", joao.getConteudo("Hashtags", 0));
			Assert.assertEquals("2015-08-09 00:12:32", joao.getConteudo("Data", 0));

			// CURTIR POST / POPULARIDADE DO POST
			poster = joao.getPost(0);
			
			fred.curtir(poster);
			maria.curtir(poster);
			
			Assert.assertTrue(joao.getPost(0).getPopularidade() == 20);
			
			// DESCURTIR POST / POPULARIDADE DO POST
			bruna.descurtir(poster);
			
			Assert.assertTrue(joao.getPost(0).getPopularidade() == 10);
			
		} catch(RedeSocialMaisPopException erro) {
			Assert.fail();
		}
		
	}
	

}