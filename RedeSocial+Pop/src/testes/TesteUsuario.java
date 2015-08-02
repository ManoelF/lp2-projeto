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
			Assert.assertEquals("Nome inserida/o nao eh valida/o",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "", "123", "10/10/1990",
					"imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Email inserida/o nao eh valida/o",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "", "10/10/1990",
					 "imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Senha inserida/o nao eh valida/o",
					erro.getMessage());
		}

		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "",
					 "imagem/joao.jpg");
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Nascimento inserida/o nao eh valida/o",
					erro.getMessage());
		}


		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "10/10/1990", null);
		} catch (CadastroInvalidoException erro) {
			Assert.assertEquals("Imagem inserida/o nao eh valida/o",
					erro.getMessage());
		}
	}

	@Test
	public void testUsuario() {
		try {

			maria = new Usuario("Maria", "maria@email.com", "321",
					"20/01/1995", "");

			Assert.assertEquals("Maria", maria.getNome());
			Assert.assertEquals("maria@email.com", maria.getEmail());
			Assert.assertEquals("321", maria.getSenha());
			Assert.assertEquals("1995-01-20", maria.getNascimento());
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
			fred = new Usuario("Fred", "fred@email.com", "0101", "25/12/1989",
					 "resources/fred.jpg");

			fred.alterarNome("Fred Silva");
			Assert.assertEquals("Fred Silva", fred.getNome());

			fred.alterarEmail("fredsilva@email.com");
			Assert.assertEquals("fredsilva@email.com", fred.getEmail());

			fred.alterarNascimento("22/12/1989");
			Assert.assertEquals("1989-12-22", fred.getNascimento());


			fred.alterarImagem("");
			Assert.assertEquals("resources/avatarDefaul.jpg", fred.getImagem());
			
		} catch(CadastroInvalidoException erro) {
			Assert.fail();
		} catch(LogicaException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}

	}

	@Test
	public void testInformacoesAtualizadasException() {
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					 "resources/bruna.jpg");
			bruna.alterarNome("");
			Assert.assertEquals("Bruna", bruna.getNome());

		} catch (CadastroInvalidoException erro) {
			Assert.fail();
		} catch(AtualizaInformacaoExcpetion erro) {
			Assert.assertEquals("A atualizacao do/a Nome nao eh valida", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.alterarEmail("");
			Assert.assertEquals("bruna@email.com", bruna.getEmail());

		} catch(CadastroInvalidoException erro) {
			Assert.fail();
		} catch(LogicaException erro) {
			Assert.assertEquals("A atualizacao do/a Email nao eh valida", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					 "resources/bruna.jpg");
			bruna.alterarNascimento("");		
			Assert.assertEquals("12/11/00", bruna.getNascimento());

		} catch(CadastroInvalidoException erro) {
			Assert.fail();
		} catch(LogicaException erro) {
			Assert.assertEquals("A atualizacao do/a Data de nascimento nao eh valida", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.alterarImagem(null);			
			Assert.assertEquals("resources/bruna.jpg", bruna.getImagem());

		} catch(CadastroInvalidoException erro) {
			Assert.fail();
		} catch(LogicaException erro) {
			Assert.assertEquals("A atualizacao do/a Imagem nao eh valida", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}

	}

	@Test
	public void testLiksEDeslikes() throws ParseException  {
		try {
			jailsa = new Usuario("Jailsa", "jailsa@email.com", "3a1b",
					"29/02/2000", "resources/jailsa.jpg");
			
			Post meuPost = new Post("Fim de greve #PartiuAula #help", "25/09/2015 06:30:00");
			
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