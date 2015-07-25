package testes;

import static org.junit.Assert.*;
import logica.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;

public class TesteUsuario {
	Usuario joao;
	Usuario maria;
	
	@Before
	public void setUp() {	
	}
	
	
	@Test
	public void testUsuarioException()  {
		try {
		joao = new Usuario("", "joao@email.com", "123", "10/10/90", "9999-8888", "imagem/joao.jpg");
		} catch(StringException erro) {
			Assert.assertEquals(erro.getMessage(),"Nome inserida/o nao eh valida/o");
		}
		
		try {
			joao = new Usuario("Joao", "", "123", "10/10/90", "9999-8888", "imagem/joao.jpg");
		} catch(StringException erro) {
			Assert.assertEquals(erro.getMessage(),"Email inserida/o nao eh valida/o");
		}
		
		try {
			joao = new Usuario("Joao", "joao@email.com", "", "10/10/90", "9999-8888", "imagem/joao.jpg");
		} catch(StringException erro) {
			Assert.assertEquals(erro.getMessage(),"Senha inserida/o nao eh valida/o");
		}
		
		try {
			joao = new Usuario("Joao", "joao@email.com", "123", "", "9999-8888", "imagem/joao.jpg");
		} catch(StringException erro) {
			Assert.assertEquals(erro.getMessage(),"Nascimento inserida/o nao eh valida/o");
		}		
		
		try {
			joao = new Usuario("joao", "joao@email.com", "123", "10/10/90", "", "imagem/joao.jpg");
		} catch(StringException erro) {
			Assert.assertEquals(erro.getMessage(),"Telefone inserida/o nao eh valida/o");
		}		
		
		try {
		joao = new Usuario("Joao", "joao@email.com", "123", "10/10/90", "9999-8888", null);
		} catch(StringException erro) {
			Assert.assertEquals(erro.getMessage(),"Imagem inserida/o nao eh valida/o");
		}			
	}
	
	@Test
	public void testUsuario() {
		try{
		
			maria = new Usuario("Maria", "maria@email.com", "321", "20/01/1995", "2111-1222", "");
			
			Assert.assertEquals(maria.getNome(), "Maria");
			Assert.assertEquals(maria.getEmail(), "maria@email.com");
			Assert.assertEquals(maria.getSenha(), "321");
			Assert.assertEquals(maria.getNascimento(), "20/01/1995");
			Assert.assertEquals(maria.getTelefone(), "2111-1222");
			Assert.assertEquals(maria.getImagem(), "resources/avatarDefaul.jpg");
			
		} catch(StringException erro) {
			Assert.fail();
		}
		
	}

}
