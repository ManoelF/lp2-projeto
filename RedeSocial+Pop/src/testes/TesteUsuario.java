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
			joao = new Usuario("", "joao@email.com", "123", "10/10/90",
					"9999-8888", "imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Nome inserida/o nao eh valida/o",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "", "123", "10/10/90", "9999-8888",
					"imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Email inserida/o nao eh valida/o",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "", "10/10/90",
					"9999-8888", "imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Senha inserida/o nao eh valida/o",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "",
					"9999-8888", "imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Nascimento inserida/o nao eh valida/o",
					erro.getMessage());
		}

		try {
			joao = new Usuario("joao", "joao@email.com", "123", "10/10/90", "",
					"imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Telefone inserida/o nao eh valida/o",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "10/10/90",
					"9999-8888", null);
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Imagem inserida/o nao eh valida/o",
					erro.getMessage());
		}
	}

	@Test
	public void testUsuario() {
		try {

			maria = new Usuario("Maria", "maria@email.com", "321",
					"20/01/1995", "2111-1222", "");

			Assert.assertEquals("Maria", maria.getNome());
			Assert.assertEquals("maria@email.com", maria.getEmail());
			Assert.assertEquals("321", maria.getSenha());
			Assert.assertEquals("20/01/95", maria.getNascimento());
			Assert.assertEquals("2111-1222", maria.getTelefone());
			Assert.assertEquals("resources/avatarDefaul.jpg", maria.getImagem());

		} catch (CadastroInvalidoException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}

	}

	@Test
	public void testInformacoesAtualizadas() {
		try {
			fred = new Usuario("Fred", "fred@email.com", "0101", "25/12/89",
					"2131-4334", "resources/fred.jpg");

			fred.alterarNome("Fred Silva");
			Assert.assertEquals("Fred Silva", fred.getNome());

			fred.alterarEmail("fredsilva@email.com");
			Assert.assertEquals("fredsilva@email.com", fred.getEmail());

			fred.alterarNascimento("22/12/89");
			Assert.assertEquals("22/12/89", fred.getNascimento());

			fred.alterarTelefone("1212-3443");
			Assert.assertEquals("1212-3443", fred.getTelefone());

			fred.alterarImagem("");
			Assert.assertEquals("resources/avatarDefaul.jpg", fred.getImagem());
			
		} catch(CadastroInvalidoException erro) {
			System.out.println(erro.getMessage());
		} catch(LogicaException erro) {
			System.out.println(erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}

	}

	@Test
	public void testInformacoesAtualizadasException() {
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/00",
					"9113-4215", "resources/bruna.jpg");
			bruna.alterarNome("");

		} catch (CadastroInvalidoException erro) {
			System.out.println(erro.getMessage());
		} catch(LogicaException erro) {
			Assert.assertEquals("Bruna", bruna.getNome());
		} catch(ParseException erro) {
			Assert.fail();
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/00",
					"9113-4215", "resources/bruna.jpg");
			bruna.alterarEmail("");
		} catch(CadastroInvalidoException erro) {
			System.out.println(erro.getMessage());
		} catch(LogicaException erro) {
			Assert.assertEquals("bruna@email.com", bruna.getEmail());
		} catch(ParseException erro) {
			Assert.fail();
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/00",
					"9113-4215", "resources/bruna.jpg");
			bruna.alterarNascimento("");		
		} catch(CadastroInvalidoException erro) {
		 	System.out.println(erro.getMessage());
		} catch(LogicaException erro) {
			Assert.assertEquals("12/11/00", bruna.getNascimento());
		} catch(ParseException erro) {
			Assert.fail();
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/00",
					"9113-4215", "resources/bruna.jpg");
			bruna.alterarTelefone("");			
		} catch(CadastroInvalidoException erro) {
			System.out.println(erro.getMessage());
		} catch(LogicaException erro) {
			Assert.assertEquals("9113-4215", bruna.getTelefone());
		} catch(ParseException erro) {
			Assert.fail();
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/00",
					"9113-4215", "resources/bruna.jpg");
			bruna.alterarImagem("");			
		} catch(CadastroInvalidoException erro) {
			System.out.println(erro.getMessage());
		} catch(LogicaException erro) {
			Assert.assertEquals("resources/bruna.jpg", bruna.getImagem());
		} catch(ParseException erro) {
			Assert.fail();
		}

	}

	@Test
	public void testLiksEDeslikes() throws ParseException  {
		try {
			jailsa = new Usuario("Jailsa", "jailsa@email.com", "3a1b",
					"29/02/2000", "91114-0898", "resources/jailsa.jpg");
			
			Post meuPost = new Post("Fim de greve #PartiuAula #help");
			
			jailsa.like(meuPost);
			Assert.assertTrue(meuPost.getLike() == 1);
			
			jailsa.deslike(meuPost);
			Assert.assertTrue(meuPost.getDeslike() == 1);
			
		} catch (CadastroInvalidoException erro) {
			Assert.fail();
		} catch (PostException erro) {
			Assert.fail();
		}
	}

}