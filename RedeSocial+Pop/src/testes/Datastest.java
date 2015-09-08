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

		System.out.println(verificaSenha("!mario !"));
	}

	public boolean verificaSenha(String senha) {
		String validacaoNome = "^[.^\\S].+";
		return senha.matches(validacaoNome);
	}
	
}
