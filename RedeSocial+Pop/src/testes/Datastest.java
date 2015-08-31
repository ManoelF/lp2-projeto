package testes;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import logica.Controller;
import logica.Facade;
import logica.Usuario;

import org.junit.Test;

import exceptions.CadastroInvalidoException;
import exceptions.EntradaException;
import exceptions.LogicaException;

public class Datastest {

	
	@Test
	public void test() throws EntradaException, LogicaException {

		try {
			Facade facade = new Facade();
			Controller ctr = new Controller();
			Usuario user = new Usuario("ze", "ze@email.com", "1213", "10/05/1990", "imagem");
			String jo = facade.cadastraUsuario("Jo Soares", "jo@uol.com.br", "!soares!", "16/01/1938");
			System.out.println(user.getImagem());
			System.out.println(facade.getInfoUsuario("Foto", jo));
			
			
		} catch (CadastroInvalidoException e) {
			System.out.println(e.getMessage());
		} 

	}

}
