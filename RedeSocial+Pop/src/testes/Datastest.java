package testes;

import static org.junit.Assert.*;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.Test;

public class Datastest {

	
	@Test
	public void test() {

		String data = "29/02/2001" ;
		String texto = "Nao sei porque tanto recalque, o que eh bonito eh pra se mostrar.";
		System.out.println(encontraTexto(texto));

	}

	
	
	public boolean verificaFormatoData(String data) {
			
		String[] dataS = data.split("/");
		if (dataS.length != 3) {
			return false;
		}
		if (!(dataS[0].matches("^[0-9]*$")) ||
		    !(dataS[1].matches("^[0-9]*$")) ||
			!(dataS[2].matches("^[0-9]*$")) ) {
				return false;
		    }
				
		return true;
	}
	
	public boolean verificaDataValida(String data) {
		try {	
			String[] dataS = data.split("/");
			int dia = Integer.parseInt(dataS[0]);
			int mes = Integer.parseInt(dataS[1]);
			int ano = Integer.parseInt(dataS[2]);
			LocalDate dat = LocalDate.of(ano, mes, dia);
			System.out.println(dat.toString());
			return true;
		} catch (DateTimeException erro) {
			System.out.println(erro.getMessage());
			return false;
		}
	
	}
	
	public String encontraTexto(String texto) {
		char[] novoTexto = texto.toCharArray();
		String conteudo = "";
		String chave = "";
		boolean inicia = false;
		int pos = 0;
		int cont = 0;
		for (char caracter: novoTexto) {
			cont += 1;
			if (caracter == '<') {
				pos = cont;
				inicia = true;
			}else if (caracter == '>' ) { 
				if (chave.contains("audio") || chave.contains("imagem")) {
					
					return conteudo.substring(0, pos - 2);
				}
				
			} else if (inicia) {
				chave += caracter;
			} else if (caracter == '#') {
				pos = cont;
				return conteudo.substring(0, pos - 2);
			} else {
				conteudo += caracter;
			}
			
		}
		return conteudo;
	}
}
