package testes;

import static org.junit.Assert.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Deque;

import logica.Util;

import org.junit.Test;

public class Datastest {

	
	@Test
	public void test() {

		//String data = "2001/02/22 11:40:13";
		//String texto = "Nao sei porque tanto recalque, o que eh bonito eh pra se mostrar.";
		//System.out.println(encontraTexto(texto));
		datas();
		
		Deque deque = new ArrayDeque();
		}
	
	public void datas() {
		LocalDate data = LocalDate.now();
		LocalDateTime novaData = LocalDateTime.of(2015, 8, 29, 10, 20,30);
		System.out.println(data);
		System.out.println(novaData.toString().contains(data.toString()));
	}

}
