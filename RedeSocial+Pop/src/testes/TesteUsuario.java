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
			Assert.assertEquals("1995-01-32", maria.getNascimento());
			Assert.assertEquals("resources/avatarDefaul.jpg", maria.getImagem());

		} catch (CadastroInvalidoException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}

	}
	
	// reorganizar os proximos teste para testarem as novas alterações
	
		@Test
	public void testInformacoesAtualizadas() throws AtualizaPerfilException {
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
		} catch(AtualizaPerfilException erro) {
			Assert.fail();
		} catch(ParseException erro) {
			Assert.fail();
		}

	}

	@Test
	public void testInformacoesAtualizadasException() throws AtualizaPerfilException {
		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					 "resources/bruna.jpg");
			bruna.alterarNome("");
			Assert.assertEquals("Bruna", bruna.getNome());

		} catch (CadastroInvalidoException erro) {
			Assert.fail();
		} catch(AtualizaNomeException erro) {
			Assert.assertEquals("Erro na atualizacao do perfil. Nome dx usuarix nao pode ser vazio.", erro.getMessage());
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
		} catch(AtualizaEmailException erro) {
			Assert.assertEquals("Erro na atualizacao do perfil. Formato de e-mail esta invalido.", erro.getMessage());
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
		} catch(AtualizaPerfilException erro) {
			//alterar msg
			Assert.assertEquals("Erro na atualizacao do perfil.", erro.getMessage());
		} catch(ParseException erro) {
			//Assert.assertEquals("Unparseable date: ", erro.getMessage());   <<RESOLVER>>
		}

		try {
			bruna = new Usuario("Bruna", "bruna@email.com", "1221", "12/11/2000",
					"resources/bruna.jpg");
			bruna.alterarImagem(null);			
			Assert.assertEquals("resources/bruna.jpg", bruna.getImagem());
		} catch(CadastroInvalidoException erro) {
			Assert.fail();
		} catch(AtualizaPerfilException erro) {
			//alterar msg
			Assert.assertEquals("Erro na atualizacao do perfil.", erro.getMessage());
		} catch(ParseException erro) {
			Assert.fail();
		}
		
		// testar atualizacao de senha e de data de nascimento

	}

}