package testes;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class Datastest {

	@Test
	public void test() {
		data("10/05/1994");
	
	}

	
	
	public void data(String data) {
		String[] dataS = data.split("/");
		if (dataS.length < 3) {
			// Exception de entrada invalida
		}
		if (!(dataS[0].matches("^[0-9]*$")) ||
		    !(dataS[0].matches("^[0-9]*$")) ||
			!(dataS[0].matches("^[0-9]*$")) ) {
		    // Exception de formatacao (ha caracteres nos numeros)	
		    }
		int dia = Integer.parseInt(dataS[0]);
		int mes = Integer.parseInt(dataS[1]);
		int ano = Integer.parseInt(dataS[2]);
		
		if (dia < 1 || dia > 31) {
			// Exception de dia invalido
			// Será necessario tratar meses de 31 dias, 30 dias e 28/29 dias?
			//
		}
		
		if (mes < 1 || mes > 12) {
			// Exception de mes invalido
		}
		
		if (ano < 1900 || ano > 2015) {
			// Exception de ano invalido (fora do periodo. .. )
		}
		System.out.println(dia + " " + mes + " " + ano);
		LocalDate dat = LocalDate.of(ano, mes, dia);
		System.out.println(dat);
	
	}
}
