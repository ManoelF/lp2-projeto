package testes;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;

import logica.Usuario;
import logica.Util;

import org.junit.Test;

import exceptions.CadastroInvalidoException;

public class Datastest {

	
	@Test
	public void test() {
		try {
			Usuario user = new Usuario("ze", "ze@email.com", "1213", "10/05/1990");
		} catch (CadastroInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}
}
