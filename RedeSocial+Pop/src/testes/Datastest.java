package testes;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import logica.Usuario;
import logica.Util;

import org.junit.Test;

import exceptions.CadastroInvalidoException;

public class Datastest {

	
	@Test
	public void test() {
		try {
			Usuario user = new Usuario("ze", "ze@email.com", "1213", "10/05/1990");
			Deque<Usuario> dequeA = new LinkedList<>();
			Deque<String> dequeB = new ArrayDeque<>();
			
			/*dequeB.add("Bola");
			dequeB.add("Faca");
			dequeB.add("bila");
			dequeB.add("barra");
			dequeB.add("marte");
			dequeB.add("java");
			dequeB.add("python");*/
			System.out.println(dequeB.pollLast());
			System.out.println(dequeB);
			
		} catch (CadastroInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}
}
